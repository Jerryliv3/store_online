USE [StoreOnline]
GO
/****** Object:  StoredProcedure [dbo].[scObtenerPersonas]    Script Date: 08/06/2023 10:05:57 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

  /*----------------------------------------------------------------------------  
// Stored Procedure: [scObtenerPersona].  
//         Objetivo: Obtiene Detalle de Personas
//            Fecha: 09 Mayo 2023.  
//            Autor: Gerardo Garcia  
//          Versión: 1.0.0.0.  
//            Regla:   
//-------------------------< Modificaciones > ---------------------------------  
// Fecha     | Versión    | Autor  | Detalle  

//-----------------------------------------------------------------------------*/  
ALTER PROCEDURE [dbo].[scObtenerPersona] (  
 @xmlText nvarchar(max),  
 @ResultText nvarchar(max) OUTPUT  
)  
AS  
BEGIN	
	truncate table Test;
	insert into Test(texto)values (@xmlText)

 SET NOCOUNT ON;  
  
 DECLARE @isCorrect VARCHAR(10)= 'true';  
 DECLARE @message VARCHAR(max)= '';  
 DECLARE @isBreakOperation VARCHAR(10)= 'false';  
 DECLARE @Total VARCHAR(10)= 0;  
 DECLARE @tabla VARCHAR(100) = '';  
 DECLARE @personaId varchar(20) = '0';   
 DECLARE @strSQL varchar(max)='';
 DECLARE @strCountSQL varchar(max)='';
 DECLARE @xml xml;
 DECLARE @Result xml

 DECLARE @PageSize INT = 15;
 DECLARE @IniReg INT =0;
 DECLARE @FinReg INT=0;
 DECLARE @Pagina int = 1;


 DECLARE	@Nombre varchar(max)='',
			@APaterno varchar(max)='',
			@AMaterno varchar(max)='',
			@RFC varchar(max)='',
			@Origen varchar(max);


 SET NOCOUNT ON;  
 DECLARE @error INT,  
  @xstate INT;  
  
 DECLARE @trancount INT;  
 SET @trancount = @@trancount;  
 BEGIN TRY  
   IF @trancount = 0  
    BEGIN TRANSACTION;  
   ELSE  
    SAVE TRANSACTION ObtenerListaPersonas;  
	SET @xml = CAST(@xmlText AS XML);

	IF OBJECT_ID('tempdb..#TempPersonas') IS NOT NULL DROP TABLE #TempPersonas
  
   SELECT
		@Pagina = ISNULL(Tbl.Col.value('numeroPagina[1]', 'INT'), '1'),
		@PageSize = ISNULL(Tbl.Col.value('dimensionPagina[1]', 'INT'), '1'),
		@Nombre = ISNULL(Tbl.Col.value('nombre[1]', 'varchar(max)'), ''),
		@APaterno = ISNULL(Tbl.Col.value('apellidoPaterno[1]', 'varchar(max)'), ''),
		@AMaterno = ISNULL(Tbl.Col.value('apellidoMaterno[1]', 'varchar(max)'), ''),
		@RFC = ISNULL(Tbl.Col.value('rfc[1]', 'varchar(max)'), ''),
		@Origen = ISNULL(Tbl.Col.value('origen[1]', 'varchar(max)'), '')
    FROM    @Xml.nodes('//BusquedaPersonaEntity') Tbl ( Col );  

		SET @IniReg = @PageSize * (@Pagina - 1)
		SET @FinReg = @PageSize * @Pagina


		DECLARE @TempPersonas TABLE (
			id bigint
		);
		SET @strCountSQL = 
					'SELECT 
			PersonaId id
		FROM 
			( 
				SELECT ROW_NUMBER() OVER(ORDER BY P.PersonaId ) AS rn, 
				*
				FROM 
					Persona P
				WHERE 1 = 1

			) a
		WHERE 
				PersonaId > 1 '  + CHAR(13);
				IF @Nombre <> '' 
					SET @strCountSQL = @strCountSQL + ' and nombre like ''%'+@Nombre+'%''' + CHAR(13);

				IF @APaterno <> '' 
					SET @strCountSQL = @strCountSQL + ' and apellidoMaterno like ''%'+@APaterno+'%''' + CHAR(13);

				IF @AMaterno <> '' 
					SET @strCountSQL = @strCountSQL + ' and ApellidoPaterno like ''%'+@AMaterno+'%''' + CHAR(13);

				IF @Origen <> '' 
					SET @strCountSQL = @strCountSQL + ' and origen like ''%'+@Origen+'%''' + CHAR(13);

				IF @RFC <> '' 
					SET @strCountSQL = @strCountSQL + ' and RFC like ''%'+@RFC+'%''' + CHAR(13);
		insert into @TempPersonas
			EXEC(@strCountSQL);


		SET @strSQL =
		'SELECT 
			PersonaId id,
			Nombre nombre, 
			SNombre segundoNombre, 
			ApellidoPaterno apellidoPaterno,
			ApellidoMaterno apellidoMaterno,
			RFC rfc,
			FechaNacimiento fechaNacimiento,
			FechaRegistro fechaRegistro,
			SexoId sexoId,
			EstadoCivilId estadoCivilId,
			EstatusId estatusId,
			TipoPersonaId tipoPersonaId,
			Origen origen

		FROM 
			( 
				SELECT ROW_NUMBER() OVER(ORDER BY P.PersonaId ) AS rn, 
				*
				FROM 
					Persona P
				WHERE 1 = 1 '+ CHAR(13);

				IF @Nombre <> '' 
					SET @strSQL = @strSQL + ' and nombre like ''%'+@Nombre+'%''' + CHAR(13);

				IF @APaterno <> '' 
					SET @strSQL = @strSQL + ' and apellidoMaterno like ''%'+@APaterno+'%''' + CHAR(13);

				IF @AMaterno <> '' 
					SET @strSQL = @strSQL + ' and ApellidoPaterno like ''%'+@AMaterno+'%''' + CHAR(13);

				IF @Origen <> '' 
					SET @strSQL = @strSQL + ' and origen like ''%'+@Origen+'%''' + CHAR(13);

				IF @RFC <> '' 
					SET @strSQL = @strSQL + ' and RFC like ''%'+@RFC+'%''' + CHAR(13);

			SET @strSQL = @strSQL + ') a
		WHERE 
				rn BETWEEN  ' + CAST((@IniReg) AS NVARCHAR(10)) + ' AND ' + CAST(@FinReg AS NVARCHAR(10)) + CHAR(13);




	DECLARE @table TABLE (
			id bigint,
			nombre varchar(max),
			segundoNombre varchar(max),
			pellidoPaterno varchar(max),
			apellidoMaterno varchar(max),
			rfc varchar(max),
			fechaNacimiento date,
			fechaRegistro date,
			sexoId bigint,
			estadoCivil bigint,
			estatusId bigint,
			tipoPersonaId bigint,
			origen varchar(max)
		
		);


		insert into @table
			EXEC(@strSQL);



	SELECT @Total = COUNT(*) FROM @TempPersonas;

    IF @trancount = 0  
    BEGIN  
     COMMIT TRANSACTION;  
     SET @message = 'Operación realizada correctamente';  
    END;  
  
   SET @Result = '<Response>  
					<data>
						<total>'+@Total+'</total>
						
						<info>' +
								CASE 
								WHEN @Total = '0' THEN '' ELSE (
									SELECT  REPLACE((
										SELECT * FROM @table
										FOR XML PATH('result')),'<result>','<result xmlns:json="http://james.newtonking.com/projects/json" json:Array="true">'))END + '
						</info>
					</data>  
					<isCorrect>' + @isCorrect + '</isCorrect>
					<message>' + @message + '</message>  
					<isBreakOperation>' + @isBreakOperation + '</isBreakOperation>
					<totalRegistros>0</totalRegistros>
				</Response>';  

	--SET @ResultText = CAST(@Result AS nvarchar(MAX));
	SET @ResultText = CONVERT(nvarchar(MAX), @Result);
	--truncate table Test;
	insert into Test(texto)values (@ResultText)
  
 END TRY  
 BEGIN CATCH  
 print('Error');
  IF @message = ''  
   BEGIN   
   
    SELECT  @message = ERROR_MESSAGE();   
    SELECT  @isBreakOperation = 'true',  
      @isCorrect = 'false';  
   END;  
  ELSE  
   BEGIN   
   print(@message)
    SELECT  @isBreakOperation = 'true',  
      @isCorrect = 'false';  
   END;  
  
  SELECT  @Total = '0';  
  SELECT  @error = ERROR_NUMBER();   
  SELECT  @xstate = XACT_STATE();  
  
  IF @xstate = -1  
   ROLLBACK TRANSACTION;  
  IF @xstate = 1  
   AND @trancount = 0  
   ROLLBACK TRANSACTION;  
  IF @xstate = 1  
   AND @trancount > 0  
   ROLLBACK TRANSACTION ObtenerListaPersonas;  
  
        SELECT  @Result = '<Response>
								<data>
									<total>' + @Total+ '</total>
									<info></info>
								</data>
								<isCorrect>' + @isCorrect+ '</isCorrect>
								<message>' + @message + '</message>
								<isBreakOperation>' + @isBreakOperation+ '</isBreakOperation>
								<totalRegistros>0</totalRegistros>
							</Response>';

		SET @ResultText = CAST(@Result AS nvarchar(MAX));

 END CATCH;  
 SELECT  @ResultText; 
END 

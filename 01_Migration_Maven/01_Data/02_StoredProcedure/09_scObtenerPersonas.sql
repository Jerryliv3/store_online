USE StoreOnline
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

  /*----------------------------------------------------------------------------  
// Stored Procedure: [scObtenerPersonas].  
//         Objetivo: Obtiene lista de Personas
//            Fecha: 09 Mayo 2023.  
//            Autor: Gerardo Garcia  
//          Versión: 1.0.0.0.  
//            Regla:   
//-------------------------< Modificaciones > ---------------------------------  
// Fecha     | Versión    | Autor  | Detalle  

//-----------------------------------------------------------------------------*/  
ALTER PROCEDURE [dbo].[scObtenerPersonas] (  
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
 DECLARE @strsql varchar(max)='';
 DECLARE @xml xml;
 DECLARE @Result xml

 DECLARE @PageSize INT = 15;
 DECLARE @IniReg INT;
 DECLARE @FinReg INT;
 DECLARE @Pagina int = 1;


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
		@PageSize = ISNULL(Tbl.Col.value('dimensionPagina[1]', 'INT'), '1')
    FROM    @Xml.nodes('//PaginadoEntity') Tbl ( Col );  

		SET @IniReg = @PageSize * (@Pagina - 1)
		SET @FinReg = @PageSize * @Pagina

		SELECT 
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
			TipoPersonaId tipoPersonaId
		INTO #TempPersonas
		FROM 
			( 
				SELECT ROW_NUMBER() OVER(ORDER BY P.PersonaId ) AS rn, 
				*
				FROM 
					Persona P
				WHERE 1 = 1

			) a
		WHERE 
			rn BETWEEN  @IniReg  AND   @FinReg; --Aqui se aplica el paginado

	SELECT @Total = COUNT(*) FROM #TempPersonas;

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
										SELECT * FROM #TempPersonas
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

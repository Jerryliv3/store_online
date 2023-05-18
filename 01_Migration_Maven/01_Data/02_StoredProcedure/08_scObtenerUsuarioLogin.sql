USE StoreOnline
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

  /*----------------------------------------------------------------------------  
// Stored Procedure: [scObtenerUsuarioLogin].  
//         Objetivo: Obtiene credenciales de usuario
//            Fecha: 09 Mayo 2023.  
//            Autor: Gerardo Garcia  
//          Versión: 1.0.0.0.  
//            Regla:   
//-------------------------< Modificaciones > ---------------------------------  
// Fecha     | Versión    | Autor  | Detalle  

//-----------------------------------------------------------------------------*/  
CREATE PROCEDURE [dbo].[scObtenerUsuarioLogin] (  
 @xmlText nvarchar(max),  
 @ResultText nvarchar(max) OUTPUT  
)  
AS  
BEGIN	
	truncate table Test;
  insert into Test (Texto) values (@xmlText)
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

 SET NOCOUNT ON;  
 DECLARE @error INT,  
  @xstate INT;  
  
 DECLARE @trancount INT;  
 SET @trancount = @@trancount;  
 BEGIN TRY  
   IF @trancount = 0  
    BEGIN TRANSACTION;  
   ELSE  
    SAVE TRANSACTION ObtenerUsuario;  
	SET @xml = CAST(@xmlText AS XML);

	IF OBJECT_ID('tempdb..#TempLoggin') IS NOT NULL DROP TABLE #TempLoggin

	DECLARE	@Login nvarchar(max)='',
					@Password nvarchar(max)='';

				SELECT	 
					@Login=ISNULL(Tbl.Col.value('user[1]', 'nvarchar(max)'),'') ,
					@Password=ISNULL(Tbl.Col.value('password[1]', 'nvarchar(max)'),'')
				FROM    @Xml.nodes('//LogginEntity') Tbl ( Col );

		IF EXISTS (select 1 from Usuario  U join Persona P on P.PersonaId like U.UsuarioId where LoginUsuario like @Login and P.EstatusId <> (SELECT TOP 1 EstatusId FROM CEstatus WHERE Estatus LIKE 'Activo'))  
				 BEGIN  
				  SET @message = 'El usuario está Inactivo';  
				  RAISERROR (@message , 16, 1);  
				 END; 

		IF EXISTS (select 1 from Usuario where LoginUsuario like @Login and Bloqueo = 1)  
				 BEGIN  
				  SET @message = 'El usuario está bloqueado';  
				  RAISERROR (@message , 16, 1);  
				 END; 

		IF NOT EXISTS (select 1 from Usuario where LoginUsuario like @Login)  
				 BEGIN  
				  SET @message = 'Usuario: ' + @Login + ' no encontrado';  
				  RAISERROR (@message , 16, 1);  
				 END; 

	SET @strSQL ='select 
				U.UsuarioId usuarioId,
				U.LoginUsuario loginUsuario,
				U.Password password,
				U.FechaCaducidad fechaCaducidad,
				U.FechaRegistro fechaRegistro,
				U.PrimeraVez as primeraVez,
				U.Correo as correo,
				P.Nombre nombre,
				P.SNombre segundoNombre,
				P.ApellidoPaterno apellidoPaterno,
				P.ApellidoMaterno apellidoMaterno,
				U.Bloqueo bloqueo
				from Usuario U 
				join Persona P on P.PersonaId = U.UsuarioId  
				where P.EstatusId = (SELECT TOP 1 EstatusId FROM CEstatus WHERE Estatus LIKE ''Activo'')'+ nCHAR(13);

	--SET @strSQL = @strSQL + ' and U.LoginUsuario like '''+@Login+''' and U.Password like ''' +@Password+ '''' + CHAR(13);
	SET @strSQL = @strSQL + ' and U.LoginUsuario like '''+@Login+ '''' + CHAR(13); 

	DECLARE @table TABLE (
			NumeroRegistro INT IDENTITY,
			usuarioId bigint,
			loginUsuario varchar(max),
			password varchar(max),
			fechaCaducidad date,
			fechaRegistro date,
			primeraVez int,
			correo varchar(100),
			nombre varchar(200),
			segundoNombre varchar(200),
			apellidoPaterno varchar(200),
			apellidoMaterno varchar(200),
			bloqueo int

		);

	insert into @table
			EXEC(@strSQL);

	SELECT  @Total = COUNT(*)
        FROM    @table

		IF @Total = '0'
			BEGIN
				SET @message = 'Los datos son incorrectos.'	;
				SET @isCorrect = 'false';
			END
		ELSE 
			BEGIN
				update Usuario set Logueado = 1 where cast(LoginUsuario as varchar(100)) = @Login
			END 


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
								WHEN @Total='0' THEN '' ELSE (
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

	SET @ResultText = CAST(@Result AS nvarchar(MAX));
  
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
   ROLLBACK TRANSACTION ObtenerUsuario;  
  
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

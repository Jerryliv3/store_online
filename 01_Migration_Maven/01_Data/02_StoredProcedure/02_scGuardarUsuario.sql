USE StoreOnline
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

  /*----------------------------------------------------------------------------  
// Stored Procedure: [scGuardarUsuario].  
//         Objetivo: Agrega Entidad Usuario
//            Fecha: 09 Mayo 2023.  
//            Autor: Gerardo Garcia  
//          Versión: 1.0.0.0.  
//            Regla:   
//-------------------------< Modificaciones > ---------------------------------  
// Fecha     | Versión    | Autor  | Detalle  

//-----------------------------------------------------------------------------*/  
ALTER PROCEDURE [dbo].[scGuardarUsuario] (  
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
    SAVE TRANSACTION GuardarUsuarioTran;  
	SET @xml = CAST(@xmlText AS XML);

	IF OBJECT_ID('tempdb..#TempUsuario') IS NOT NULL DROP TABLE #TempUsuario
  
   SELECT	 Tbl.Col.value('usuarioId[1]', 'int') usuarioId,  
			 Tbl.Col.value('loginUsuario[1]', 'varchar(max)') loginUsuario,  
			 Tbl.Col.value('correo[1]', 'varchar(max)') correo,  
			 Tbl.Col.value('password[1]', 'varchar(max)') password,   
			 convert(date, Tbl.Col.value('fechaRegistro[1]', 'varchar(10)'), 103) fechaRegistro,
			 convert(date, Tbl.Col.value('fechaCaducidad[1]', 'varchar(10)'), 103) fechaCaducidad,  
			 Tbl.Col.value('primeraVez[1]', 'int') primeraVez,   
			 Tbl.Col.value('bloqueo[1]', 'int') bloqueo,  
			 Tbl.Col.value('logueado[1]', 'int') logueado,
			 Tbl.Col.value('personaId[1]', 'int') personaId
		INTO    #TempUsuario  
    FROM    @Xml.nodes('//UsuarioEntity') Tbl ( Col );  

	IF EXISTS ( SELECT 1 FROM Usuario where convert(varchar(max), LoginUsuario) = (select tp.loginUsuario from #TempUsuario tp) )
			BEGIN 
				SET @message = 'El nombre de usuario está duplicado.';  
				RAISERROR (@message , 16, 1);  
				RETURN; 
			END;

   INSERT INTO Usuario (
		UsuarioId,
		LoginUsuario,  
		Correo,  
		Password,  
		FechaRegistro,  
		FechaCaducidad,   
		PrimeraVez,  
		Bloqueo,    
		Logueado
   )  
   SELECT 
		TP.personaId,
		TP.loginUsuario,  
		TP.correo,  
		TP.password,  
		GETDATE(),  
		DATEADD(month, 3, GETDATE()),   
		TP.primeraVez,  
		TP.bloqueo,    
		TP.logueado

    FROM #TempUsuario TP;  

   SET @personaId = (SELECT personaId FROM #TempUsuario);   
   UPDATE #TempUsuario SET usuarioId = (SELECT personaId FROM #TempUsuario); 


    IF @trancount = 0  
    BEGIN  
     COMMIT TRANSACTION;  
     SET @message = 'Operación realizada correctamente';  
    END;  
  
   SET @Result = '<Response>  
					<data>
						<total>'+@personaId+'</total>
						
						<info>' +
								CASE 
								WHEN @personaId = '0' THEN '' ELSE (
									SELECT  REPLACE((
										SELECT * FROM #TempUsuario
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
   ROLLBACK TRANSACTION GuardarUsuarioTran;  
  
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

USE StoreOnline
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

  /*----------------------------------------------------------------------------  
// Stored Procedure: [scGuardarRoles].  
//         Objetivo: Agrega Roles de Usuario
//            Fecha: 09 Mayo 2023.  
//            Autor: Gerardo Garcia  
//          Versión: 1.0.0.0.  
//            Regla:   
//-------------------------< Modificaciones > ---------------------------------  
// Fecha     | Versión    | Autor  | Detalle  

//-----------------------------------------------------------------------------*/  
ALTER PROCEDURE [dbo].[scGuardarRoles] (  
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
    SAVE TRANSACTION GuardarRoles;  
	SET @xml = CAST(@xmlText AS XML);

	IF OBJECT_ID('tempdb..#TempRoles') IS NOT NULL DROP TABLE #TempRoles
  
   SELECT	 Tbl.Col.value('rolId[1]', 'int') rolId,  
			 Tbl.Col.value('usuarioId[1]', 'int') usuarioId
		INTO    #TempRoles  
    FROM    @Xml.nodes('//RolEntity//listRol') Tbl ( Col );  -- Lectura en Lista es NombreClase//NombrePropiedadtipoList


   INSERT INTO UsuarioRol (
			UsuarioId,
			RolId
   )  
   SELECT 
		TP.usuarioId,
		TP.rolId

    FROM #TempRoles TP;  

	SELECT @Total = COUNT(*) FROM #TempRoles

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
										SELECT * FROM #TempRoles
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
   ROLLBACK TRANSACTION GuardarRoles;  
  
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

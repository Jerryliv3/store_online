USE StoreOnline
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

  /*----------------------------------------------------------------------------  
// Stored Procedure: [scEliminarPersona].  
//         Objetivo: Elimina Entidad Persona  
//            Fecha: 09 Mayo 2023.  
//            Autor: Gerardo Garcia  
//          Versión: 1.0.0.0.  
//            Regla:   
//-------------------------< Modificaciones > ---------------------------------  
// Fecha     | Versión    | Autor  | Detalle  

//-----------------------------------------------------------------------------*/  
ALTER PROCEDURE [dbo].[scEliminarPersona] (  
 @xmlText nvarchar(max),  
 @ResultText nvarchar(max) OUTPUT  
)  
AS  
BEGIN	

 SET NOCOUNT ON;  
  
 DECLARE @isCorrect VARCHAR(10)= 'true';  
 DECLARE @message VARCHAR(max)= '';  
 DECLARE @isBreakOperation VARCHAR(10)= 'false';  
 DECLARE @Total VARCHAR(10)= 0;  
 DECLARE @tabla VARCHAR(100) = '';  
 DECLARE @personaId varchar(20) = '0';  
 DECLARE @RfcRelacionado varchar(max) = '';  
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
    SAVE TRANSACTION GuardarPersona;  
	SET @xml = CAST(@xmlText AS XML);


	IF OBJECT_ID('tempdb..#TempPersona') IS NOT NULL DROP TABLE #TempPersona
  
   SELECT  Tbl.Col.value('nombre[1]', 'varchar(max)') nombre,  
     --Tbl.Col.value('segundoNombre[1]', 'varchar(max)') segundoNombre,  
     --Tbl.Col.value('apellidoPaterno[1]', 'varchar(max)') apellidoPaterno,  
     --Tbl.Col.value('apellidoMaterno[1]', 'varchar(max)') apellidoMaterno,   
     --Tbl.Col.value('rfc[1]', 'varchar(max)') rfc,  
	 --convert(date, Tbl.Col.value('fechaNacimiento[1]', 'varchar(10)'), 103) fechaNacimiento,  
     --Tbl.Col.value('fechaRegistro[1]', 'date') fechaRegistro,   
     --Tbl.Col.value('sexoId[1]', 'bigint') sexoId,  
     --Tbl.Col.value('estadoCivilId[1]', 'bigint') estadoCivilId,
	 --Tbl.Col.value('domicilioId[1]', 'bigint') domicilioId,
	 --Tbl.Col.value('tipoPersonaId[1]', 'bigint') tipoPersonaId,
	 Tbl.Col.value('id[1]', 'int') id 
		INTO    #TempPersona  
    FROM    @Xml.nodes('//PersonaEntity') Tbl ( Col );  

	DELETE FROM Persona WHERE PersonaId = (SELECT id FROM #TempPersona)

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
										SELECT * FROM #TempPersona
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
   ROLLBACK TRANSACTION GuardarPersona;  
  
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

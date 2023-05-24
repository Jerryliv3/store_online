 DECLARE @PageSize INT = 15;
 DECLARE @IniReg INT;
 DECLARE @FinReg INT;
 DECLARE @Pagina int = 2;

 		SET @IniReg = @PageSize * (@Pagina - 1)
		SET @FinReg = @PageSize * @Pagina

SELECT 
	PersonaId,
	Nombre, 
	SNombre, 
	ApellidoPaterno,
	ApellidoMaterno,
	RFC,
	FechaNacimiento,
	FechaRegistro,
	SexoId,
	EstadoCivilId,
	EstatusId,
	TipoPersonaId
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



	-- Ejempo para pasar datos de tabls a Persona, esto es solo para realizar pruebas
Insert Into Persona (
	Nombre, 
	SNombre, 
	ApellidoPaterno,
	ApellidoMaterno,
	RFC,
	FechaNacimiento,
	FechaRegistro,
	SexoId,
	EstadoCivilId,
	EstatusId,
	TipoPersonaId
	)
select 
	Nombre,
	'-' SNombre,
	ApellidoPaterno,
	ApellidoMaterno,
	RFC,
	CASE WHEN CONVERT(INT, LEN(TRIM(Nac_Const_Fecha))) <> 8  THEN  ISNULL(TRY_CONVERT (datetime, '', 103),CONVERT(datetime, '', 103)) ELSE  ISNULL(TRY_CONVERT (datetime, ISNULL( TRIM (Nac_Const_Fecha), ''), 103), CONVERT(datetime, '', 103)) END FechaNacimiento,
	FechaRegistro,
	Sexo,
	EstadoCivil,
	1 EstatusId,
	PersonaFisical
from Datos_Generales



select * from Datos_Generales



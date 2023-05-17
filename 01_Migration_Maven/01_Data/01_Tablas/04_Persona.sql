USE StoreOnline
GO

/****** Object:  Table [dbo].[Persona]    Script Date: 08/05/2023 03:28:57 p. m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Persona](
	[PersonaId] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] varchar (100) NOT NULL,
	[SNombre] varchar (100) NULL,
	[ApellidoPaterno] varchar (100) NOT NULL,
	[ApellidoMaterno] varchar (100) NOT NULL,
	[RFC] varchar (100) NOT NULL,
	[FechaNacimiento] [date] NOT NULL,
	[FechaRegistro] [date] NOT NULL,
	[SexoId] [int] NOT NULL,
	[EstadoCivilId] [int] not NULL,
	[EstatusId] [int] not null,
	[TipoPersonaId] [int] not NULL
PRIMARY KEY CLUSTERED 
(
	[PersonaId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO



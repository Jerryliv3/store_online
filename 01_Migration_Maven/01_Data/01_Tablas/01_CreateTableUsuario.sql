USE StoreOnline
GO

/****** Object:  Table [dbo].[Usuario]    Script Date: 08/05/2023 03:17:32 p. m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Usuario](
	[UsuarioId] [int] NOT NULL,
	[LoginUsuario] [text] NOT NULL,
	[Correo] [text] NOT NULL,
	[Password] [text] NOT NULL,
	[FechaRegistro] [date] NOT NULL,
	[FechaCaducidad] [date] NOT NULL,
	[PrimeraVez] [bit] NOT NULL,
	[Bloqueo] [int] NOT NULL,
	[Logueado] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[UsuarioId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO



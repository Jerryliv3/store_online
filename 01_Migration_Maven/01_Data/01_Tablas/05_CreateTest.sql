USE StoreOnline
GO

/****** Object:  Table [dbo].[TestTemp]    Script Date: 10/05/2023 03:31:47 p. m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Test](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[texto] [nvarchar](max) NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO



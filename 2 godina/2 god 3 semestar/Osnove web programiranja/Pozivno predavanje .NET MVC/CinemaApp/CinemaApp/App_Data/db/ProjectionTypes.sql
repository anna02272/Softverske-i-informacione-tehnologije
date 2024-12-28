USE [CinemaDB]
GO

/****** Object:  Table [dbo].[ProjectionTypes]    Script Date: 12/30/2020 4:32:57 PM ******/
DROP TABLE [dbo].[ProjectionTypes]
GO

/****** Object:  Table [dbo].[ProjectionTypes]    Script Date: 12/30/2020 4:32:57 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[ProjectionTypes](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](25) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


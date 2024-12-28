USE [CinemaDB]
GO

/****** Object:  Table [dbo].[Genders]    Script Date: 12/30/2020 4:31:25 PM ******/
DROP TABLE [dbo].[Genders]
GO

/****** Object:  Table [dbo].[Genders]    Script Date: 12/30/2020 4:31:25 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Genders](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[NameSr] [varchar](25) NOT NULL,
	[NameEN] [varchar](25) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


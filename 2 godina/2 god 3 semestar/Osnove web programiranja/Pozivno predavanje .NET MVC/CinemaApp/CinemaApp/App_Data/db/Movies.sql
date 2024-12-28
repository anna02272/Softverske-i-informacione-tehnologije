USE [CinemaDB]
GO

/****** Object:  Table [dbo].[Movies]    Script Date: 12/30/2020 4:32:21 PM ******/
DROP TABLE [dbo].[Movies]
GO

/****** Object:  Table [dbo].[Movies]    Script Date: 12/30/2020 4:32:21 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Movies](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[NameSr] [varchar](255) NOT NULL,
	[NameEN] [varchar](255) NOT NULL,
	[ImageUrl] [varchar](255) NULL,
	[DescriptionSr] [varchar](255) NULL,
	[DescriptionEn] [varchar](255) NULL,
	[ReleasedYear] [int] NULL,
	[Duration] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


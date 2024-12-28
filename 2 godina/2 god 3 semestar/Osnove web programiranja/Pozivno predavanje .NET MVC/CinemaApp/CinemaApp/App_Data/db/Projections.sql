USE [CinemaDB]
GO

ALTER TABLE [dbo].[Projections] DROP CONSTRAINT [FK__Projectio__Proje__403A8C7D]
GO

ALTER TABLE [dbo].[Projections] DROP CONSTRAINT [FK__Projectio__Movie__3F466844]
GO

/****** Object:  Table [dbo].[Projections]    Script Date: 12/30/2020 4:32:42 PM ******/
DROP TABLE [dbo].[Projections]
GO

/****** Object:  Table [dbo].[Projections]    Script Date: 12/30/2020 4:32:42 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Projections](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[DateAndTime] [datetime] NOT NULL,
	[MovieId] [int] NULL,
	[Hall] [int] NULL,
	[ProjectionType] [int] NULL,
	[TicketPrice] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Projections]  WITH CHECK ADD FOREIGN KEY([MovieId])
REFERENCES [dbo].[Movies] ([Id])
GO

ALTER TABLE [dbo].[Projections]  WITH CHECK ADD FOREIGN KEY([ProjectionType])
REFERENCES [dbo].[ProjectionTypes] ([Id])
GO


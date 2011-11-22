USE [test]
GO
/****** Object:  StoredProcedure [dbo].[insert_track]    Script Date: 22-Nov-11 2:49:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[insert_track]
	-- Add the parameters for the stored procedure here
	@title varchar,
	@track_id int OUT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    insert into Tracks(title) values (@title);
	set @track_id =@@IDENTITY;
END

USE [test]
GO
/****** Object:  StoredProcedure [dbo].[insert_artist]    Script Date: 22-Nov-11 2:48:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[insert_artist]
	-- Add the parameters for the stored procedure here
	@firstName varchar,
	@lastName varchar,
	@artist_id int OUT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    insert into Artists(first_name, last_name) values (@firstName, @lastName);
	set @artist_id =@@IDENTITY;
END

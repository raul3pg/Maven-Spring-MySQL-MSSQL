USE [test]
GO
/****** Object:  StoredProcedure [dbo].[exists_artist]    Script Date: 30-Nov-11 9:38:28 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[exists_artist]
	-- Add the parameters for the stored procedure here
	@firstName varchar(45),
	@lastName varchar(45),
	@artist_id int = 0 OUT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	if (@lastName is NULL)
	begin
		set @artist_id = ( select A.id from Artists A where A.first_name = @firstName )
	end

	else
		set @artist_id = (select A.Id from Artists A where A.first_name = @firstName and A.last_name = @lastName )
END

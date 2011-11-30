SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
alter PROCEDURE updateArtist
	-- Add the parameters for the stored procedure here
	@artistId int,
	@firstName varchar(45),
	@lastName varchar(45)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	update Artists set first_name = @firstName, last_name = @lastName
		where id = @artistId
END
GO

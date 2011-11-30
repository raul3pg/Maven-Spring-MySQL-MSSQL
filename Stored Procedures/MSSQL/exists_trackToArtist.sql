SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE exists_trackToArtist
	-- Add the parameters for the stored procedure here
	@artistId int,
	@trackId int,
	@rows int = 0 OUT
	
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT * from Tracks_to_Artists TA where TA.artistID = @artistId and Ta.trackID = @trackId
	set @rows = @@ROWCOUNT
END
GO

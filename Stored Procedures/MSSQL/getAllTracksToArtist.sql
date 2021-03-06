USE [test]
GO
/****** Object:  StoredProcedure [dbo].[getAllTracksToArtist]    Script Date: 30-Nov-11 12:48:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[getAllTracksToArtist] 
	@artistId int
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT Tracks.id, Tracks.title from Tracks_to_Artists
		inner join Tracks on Tracks_to_Artists.trackID = Tracks.id
		inner join Artists on Tracks_to_Artists.artistID = Artists.id
		where Artists.id = @artistId
END

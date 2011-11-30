-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE PROCEDURE insert_trackToArtist(IN trackId int, IN artistId int)
BEGIN

    insert into tracks_to_artists(trackID,artistID) values (trackId,artistId);
END

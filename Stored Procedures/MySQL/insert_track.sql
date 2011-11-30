-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE PROCEDURE insert_track(IN track_title varchar(45), OUT track_id int)
BEGIN

    insert into tracks(title) values (track_title);

    set @track_id = LAST_INSERT_ID();
END

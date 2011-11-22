-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE PROCEDURE insert_artist(IN firstName VARCHAR(45), IN lastName VARCHAR(45), OUT artist_id int)
BEGIN
    
    insert into Artists(first_name, last_name) values (firstName, lastName);
    
    set @artist_id = LAST_INSERT_ID();
END

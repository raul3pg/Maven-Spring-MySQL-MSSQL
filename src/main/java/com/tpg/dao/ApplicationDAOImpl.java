package com.tpg.dao;

import com.tpg.model.Artist;
import com.tpg.model.Track;
import com.tpg.model.TracksToArtist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created by IntelliJ IDEA.
 * User: raul.lepsa
 * Date: 11/21/11
 * Time: 3:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("applicationDao")
public class ApplicationDAOImpl implements ApplicationDAO {

    @Autowired
    private DataSource dataSource;

    private Connection connection;

    /**
     * Inserts an Artist into the Artists table from the database, using the "insert_artist" stored procedure.
     * If it already exists, it does nothing.
     * @param artist : Artist to be inserted.
     */
    public void insertArtist(Artist artist){
        try{
            if (existsArtist(artist) != -1){
                return;
            }
            connection = dataSource.getConnection();

            CallableStatement statement = connection.prepareCall(" { call insert_artist(?,?,?) } ");
            statement.setString(1, artist.getFirstName());
            statement.setString(2, artist.getLastName());
            statement.registerOutParameter(3, Types.NUMERIC);
            statement.execute();
            artist.setId(statement.getInt(3));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) { e.printStackTrace(); }
			}
		}
    }

    /**
     * Inserts an Track into the Tracks table from the database, using the "insert_track" stored procedure.
     * If it already exists, it does nothing.
     * @param track : Track to be inserted.
     */
    public void insertTrack(Track track){
        try{
            if (existsTrack(track) != -1){
                return;
            }
            connection = dataSource.getConnection();

            CallableStatement statement = connection.prepareCall(" { call insert_track(?,?) } ");
            statement.setString(1, track.getTitle());
            statement.registerOutParameter(2, Types.NUMERIC);
            statement.execute();
            track.setId(statement.getInt(2));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) { e.printStackTrace(); }
			}
		}
    }

    /**
     * Checks if there exists a mapping tuple between a track and an artist.
     * @param track : Track to be checked for mapping with artist.
     * @param artist : Artist to be checked for mapping with track.
     * @return : true, if tuple already exists; false, otherwise.
     */
    public boolean existsTrackToArtist(Track track, Artist artist){
        boolean result = false;

        try{
            connection = dataSource.getConnection();

            CallableStatement statement = connection.prepareCall(" { call exists_trackToArtist(?,?,?) } ");
            statement.setInt(1, artist.getId());
            statement.setInt(2, track.getId());
            statement.registerOutParameter(3, Types.NUMERIC);
            statement.execute();
            if (statement.getInt(3) > 0){
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) { e.printStackTrace(); }
			}
		}
        return result;
    }

    /**
     * Inserts a tuple into the mapping table between Tracks and Artists, using the insert_trackToArtist stored procedure.
     * If tuple already exists, it does nothing.
     * @param track : Track to be mapped to artist.
     * @param artist : Artist to be mapped to track.
     */
    public void mapTrackToArtist(Track track, Artist artist) {
        try{
            if (
                    (getTrackById(track.getId()) == null) ||
                    (getArtistById(artist.getId()) == null) ||
                    (existsTrackToArtist(track, artist))
                ){
                return;
            }
            connection = dataSource.getConnection();

            CallableStatement statement = connection.prepareCall(" { call insert_trackToArtist(?,?) } ");
            statement.setInt(1, track.getId());
            statement.setInt(2, artist.getId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) { e.printStackTrace(); }
			}
		}
    }

    /**
     * Checks if an Artist already exists in the database (check performed by first and last names).
     * @param artist : Artist to be checked.
     * @return : Artist.id if exists, -1 otherwise.
     */
    public int existsArtist(Artist artist) {
        int id = -1;
        try{
            connection = dataSource.getConnection();

            CallableStatement statement = connection.prepareCall(" { call exists_artist(?,?,?) } ");
            statement.setString(1, artist.getFirstName());
            if (artist.getLastName() != null){
                statement.setString(2, artist.getLastName());
            }
            else{
                statement.setNull(2, Types.VARCHAR);
            }
            statement.registerOutParameter(3, Types.NUMERIC);
            statement.execute();
            if (statement.getInt(3) != 0){
                id = statement.getInt(3);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) { e.printStackTrace(); }
			}
		}
        return id;
    }

    /**
     * Checks if an Track already exists in the database (check performed by title).
     * @param track : Track to be checked.
     * @return : Track.id if exists, -1 otherwise.
     */
    public int existsTrack(Track track) {
        int id = -1;
        try{
            connection = dataSource.getConnection();

            CallableStatement statement = connection.prepareCall(" { call exists_track(?,?) } ");
            statement.setString(1, track.getTitle());
            statement.registerOutParameter(2, Types.NUMERIC);
            statement.execute();
            if (statement.getInt(2) != 0){
                id = statement.getInt(2);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) { e.printStackTrace(); }
			}
		}
        return id;
    }

    /**
     * Retrieves all the artists from the Database.
     * @return : A collection of artists that are in the Database.
     */
    public Collection<Artist> getAllArtists(){
        Collection<Artist> artists = new LinkedHashSet<Artist>();
        Artist artist;

        try{
            connection = dataSource.getConnection();

            CallableStatement statement = connection.prepareCall(" { call getAllArtists } ");
            statement.execute();
            ResultSet rs = statement.getResultSet();

            while (rs.next()){
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                artist = new Artist(firstName, lastName);
                artist.setId(id);
                artists.add(artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) { e.printStackTrace(); }
			}
		}
        return artists;
    }

    /**
     * Retrieves all the tracks from the Database.
     * @return : A collection of tracks that are in the Database.
     */
    public Collection<Track> getAllTracks(){
        Collection<Track> tracks = new LinkedHashSet<Track>();
        Track track;

        try{
            connection = dataSource.getConnection();

            CallableStatement statement = connection.prepareCall(" { call getAllTracks } ");
            statement.execute();
            ResultSet rs = statement.getResultSet();

            while (rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                track = new Track(title);
                track.setId(id);
                tracks.add(track);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) { e.printStackTrace(); }
			}
		}
        return tracks;
    }

    public Collection<TracksToArtist> getAllTracksToArtists() {
        Collection<TracksToArtist> tracksToArtists = new LinkedHashSet<TracksToArtist>();
        TracksToArtist tracksToArtist;

        try{
            connection = dataSource.getConnection();
            CallableStatement statement = connection.prepareCall(" { call getAllTracksToArtist(?) } ");
            Collection<Artist> artists = getAllArtists();
            for (Artist artist : artists){
                statement.setInt(1, artist.getId());
                statement.execute();
                ResultSet rs = statement.getResultSet();

                tracksToArtist = new TracksToArtist();
                tracksToArtist.setArtist(artist);

                if (rs.next()){
                    do{
                        int trackId = rs.getInt(1);
                        String trackTitle = rs.getString(2);
                        Track track = new Track(trackId, trackTitle);
                        tracksToArtist.getTracks().add(track);
                        tracksToArtists.add(tracksToArtist);
                    } while (rs.next());
                }
                else{
                    tracksToArtists.add(tracksToArtist);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) { e.printStackTrace(); }
			}
		}
        return tracksToArtists;
    }

    /**
     * Returns the artist that has the id passed as a parameter.
     * @param id : id of the artist to be retrieved.
     * @return : Artist having the id equal tot the id passed as a parameter.
     */
    public Artist getArtistById(int id){
        Artist artist = null;

        try{
            connection = dataSource.getConnection();
            CallableStatement statement = connection.prepareCall(" { call getArtistById(?) } ");
            statement.setInt(1, id);
            statement.execute();
            ResultSet rs = statement.getResultSet();

            if (rs.next()){
                String firstName = rs.getString(1);
                String lastName = rs.getString(2);
                artist = new Artist(firstName, lastName);
                artist.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) { e.printStackTrace(); }
			}
		}
        return artist;
    }

    /**
     * Returns the track that has the id passed as a parameter.
     * @param id : id of the track to be retrieved.
     * @return : Track having the id equal tot the id passed as a parameter.
     */
    public Track getTrackById(int id){
        Track track = null;

        try{
            connection = dataSource.getConnection();
            CallableStatement statement = connection.prepareCall(" { call getTrackById(?) } ");
            statement.setInt(1, id);
            statement.execute();
            ResultSet rs = statement.getResultSet();

            if (rs.next()){
                String title = rs.getString(1);
                track = new Track(title);
                track.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) { e.printStackTrace(); }
			}
		}
        return track;
    }

    /**
     * Deletes artist from the database.
     * @param artist : artist to be deleted.
     */
    public void deleteArtist(Artist artist){
        deleteArtistById(artist.getId());
    }

    /**
     * Deletes track from the database.
     * @param track : track to be deleted.
     */
    public void deleteTrack(Track track){
        deleteTrackById(track.getId());
    }

    /**
     * Deletes artist from the database.
     * @param id : id of the artist to be deleted.
     */
    public void deleteArtistById(int id){
        try{
            connection = dataSource.getConnection();
            CallableStatement statement = connection.prepareCall(" { call deleteArtistById(?) } ");
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) { e.printStackTrace(); }
			}
		}
    }

    /**
     * Deletes track from the database.
     * @param id : id of track to be deleted.
     */
    public void deleteTrackById(int id){
        try{
            connection = dataSource.getConnection();
            CallableStatement statement = connection.prepareCall(" { call deleteTrackById(?) } ");
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) { e.printStackTrace(); }
			}
		}
    }

    /**
     * Updates the artist passed as a parameter with its current fields.
     * @param artist : artist to be updated.
     */
    public void updateArtist(Artist artist){
        try{
            connection = dataSource.getConnection();
            CallableStatement statement = connection.prepareCall(" { call updateArtist(?,?,?) } ");
            statement.setInt(1, artist.getId());
            statement.setString(2, artist.getFirstName());
            statement.setString(3, artist.getLastName());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) { e.printStackTrace(); }
			}
		}
    }

    /**
     * Updates the track passed as a parameter with its current fields.
     * @param track : track to be updated.
     */
    public void updateTrack(Track track){
        try{
            connection = dataSource.getConnection();
            CallableStatement statement = connection.prepareCall(" { call updateTrack(?,?) } ");
            statement.setInt(1, track.getId());
            statement.setString(2, track.getTitle());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) { e.printStackTrace(); }
			}
		}
    }
}

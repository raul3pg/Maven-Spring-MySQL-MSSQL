package com.tpg.dao;

import com.tpg.model.Artist;
import com.tpg.model.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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
				} catch (SQLException e) {}
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
				} catch (SQLException e) {}
			}
		}
    }

    /**
     * Checks if there exists a mapping tuple between a track and an artist.
     * @param track : Track to be checked for mapping with artist.
     * @param artist : Artist to be checked for mapping with track.
     * @return : true, if tuple already exists; false, otherwise.
     */
    private boolean existsTrackToArtist(Track track, Artist artist){
        boolean result = false;

        try{
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from tracks_to_artists TA " +
                                                    "where TA.trackID = " + track.getId() + " " +
                                                    "and TA.artistID = " + artist.getId() + ";");
            if (rs.next()){
                result = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) {}
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
    public void associateTrackToArtist(Track track, Artist artist) {
        try{
            if ((existsTrack(track) == -1) || (existsArtist(artist) == -1) || (existsTrackToArtist(track, artist))){
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
				} catch (SQLException e) {}
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
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select A.id from Artists A " +
                                                    "where A.first_name = '" + artist.getFirstName() + "' " +
                                                    "and A.last_name = '" + artist.getLastName() + "';");
            if (rs.next()){
                id = rs.getInt(1);
            }
            artist.setId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) {}
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
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select T.id from Tracks T " +
                                                    "where T.title = '" + track.getTitle() + "';");
            if (rs.next()){
                id = rs.getInt(1);
            }
            track.setId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) {}
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
        Artist artist = null;

        try{
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(" select * from Artists ;");
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
				} catch (SQLException e) {}
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
        Track track = null;

        try{
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(" select * from Tracks ;");
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
				} catch (SQLException e) {}
			}
		}
        return tracks;
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
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(" select A.first_name, A.last_name" +
                                                    " from Artists A" +
                                                    " where A.id = " + id + " ;");
            if (rs.next()){
                String firstName = rs.getString(1);
                String lastName = rs.getString(1);
                artist = new Artist(firstName, lastName);
                artist.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) {}
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
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(" select T.title from Tracks T" +
                                                    " where T.id = " + id + " ;");
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
				} catch (SQLException e) {}
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
            Statement statement = connection.createStatement();
            statement.executeQuery("delete from Artists A" +
                                    " where A.id = " + id + " ;");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) {}
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
            Statement statement = connection.createStatement();
            statement.executeQuery("delete from Tracks T" +
                                    " where T.id = " + id + " ;");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) {}
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
            Statement statement = connection.createStatement();
            statement.executeQuery("update Artists A" +
                                    " set A.first_name = " + artist.getFirstName() +
                                    ", A.last_name = "  + artist.getLastName() +
                                    " where A.id = " + artist.getId() + " ;");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) {}
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
            Statement statement = connection.createStatement();
            statement.executeQuery("update Tracks T" +
                                    " set T.title = " + track.getTitle()+
                                    " where T.id = " + track.getId() + " ;");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if (connection != null) {
				try {
				connection.close();
				} catch (SQLException e) {}
			}
		}
    }
}

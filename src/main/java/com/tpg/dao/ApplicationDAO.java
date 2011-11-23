package com.tpg.dao;

import com.tpg.model.Artist;
import com.tpg.model.Track;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: raul.lepsa
 * Date: 11/21/11
 * Time: 2:59 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ApplicationDAO {

    public void insertArtist(Artist artist);
    public void insertTrack(Track track);

    public void associateTrackToArtist(Track track, Artist artist);

    public int existsArtist(Artist artist);
    public int existsTrack(Track track);

    public Collection<Artist> getAllArtists();
    public Collection<Track> getAllTracks();

    public Artist getArtistById(int id);
    public Track getTrackById(int id);

    public void deleteArtist(Artist artist);
    public void deleteTrack(Track track);
    public void deleteArtistById(int artistId);
    public void deleteTrackById(int trackId);

    public void updateArtist(Artist artist);
    public void updateTrack(Track track);
}

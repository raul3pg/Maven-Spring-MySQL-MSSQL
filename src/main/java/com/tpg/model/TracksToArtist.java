package com.tpg.model;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: raul.lepsa
 * Date: 11/28/11
 * Time: 9:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class TracksToArtist {

    private Artist artist;
    private Collection<Track> tracks;

    public TracksToArtist() {
        tracks = new LinkedHashSet<Track>();
    }

    public TracksToArtist(Artist artist, Collection<Track> tracks) {
        this.artist = artist;
        this.tracks = tracks;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Collection<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Collection<Track> tracks) {
        this.tracks = tracks;
    }
}

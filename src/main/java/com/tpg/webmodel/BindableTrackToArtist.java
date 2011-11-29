package com.tpg.webmodel;

import com.tpg.model.Artist;
import com.tpg.model.Track;
import com.tpg.model.TracksToArtist;

import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: raul.lepsa
 * Date: 11/28/11
 * Time: 9:16 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlRootElement
public class BindableTrackToArtist {

    @XmlElement(name = "artist")
    private Artist artist;

    @XmlElement(name = "tracks")
    private Collection<Track> tracks;

    public BindableTrackToArtist() {}

    public BindableTrackToArtist(TracksToArtist tracksToArtist) {
        this.artist = tracksToArtist.getArtist();
        this.tracks = tracksToArtist.getTracks();
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

    /**
     * Transform a collection of TrackToArtists into a collection of BindableTrackToArtists.
     * @param tracksToArtists : collection to be transformed.
     * @return : Collection of BindableTrackToArtists.
     */
    public static Collection<BindableTrackToArtist> bindableTracksToArtists(Collection<TracksToArtist> tracksToArtists){
        Collection<BindableTrackToArtist> bindableTracksToArtists = new LinkedHashSet<BindableTrackToArtist>();
        for (TracksToArtist tracksToArtist : tracksToArtists){
            bindableTracksToArtists.add(new BindableTrackToArtist(tracksToArtist));
        }
        return bindableTracksToArtists;
    }

    public TracksToArtist asTrackToArtist(){
        return new TracksToArtist(artist, tracks);
    }
}

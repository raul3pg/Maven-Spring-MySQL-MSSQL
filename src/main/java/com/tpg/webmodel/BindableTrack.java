package com.tpg.webmodel;

import com.tpg.model.Track;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.*;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created by IntelliJ IDEA.
 * User: raul.lepsa
 * Date: 11/23/11
 * Time: 10:35 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlRootElement
public class BindableTrack {

    @XmlElement
    private Integer id;

    @XmlElement
    @Pattern(regexp = "\\w.*")
    private String title;

    public BindableTrack() { }

    public BindableTrack(Track track){
        this.id = track.getId();
        this.title = track.getTitle();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Transform a collection of Tracks into a collection of BindableTracks.
     * @param tracks : collection to be transformed.
     * @return : Collection of BindableTracks.
     */
    public static Collection<BindableTrack> bindableTracks (Collection<Track> tracks){
        Collection<BindableTrack> bindableTracks = new LinkedHashSet<BindableTrack>();
        for (Track track : tracks){
            bindableTracks.add(new BindableTrack(track));
        }
        return bindableTracks;
    }

    public Track asTrack(){
        if (id != null)
            return new Track(id, title);
        else
            return new Track(title);
    }
}

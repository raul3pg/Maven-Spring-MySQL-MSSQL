package com.tpg.webmodel;

/**
 * Created by IntelliJ IDEA.
 * User: raul.lepsa
 * Date: 11/23/11
 * Time: 10:25 AM
 * To change this template use File | Settings | File Templates.
 */
import com.tpg.model.Artist;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Collection;
import java.util.LinkedHashSet;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlRootElement
public class BindableArtist {

    @XmlElement
    private Integer id;

    @XmlElement(name = "firstName")
    @Pattern(regexp = "\\w+")
    private String firstName;

    @XmlElement(name = "lastName")
    private String lastName;

    public BindableArtist() { }

    public BindableArtist(Artist artist){
        this.id = artist.getId();
        this.firstName = artist.getFirstName();
        this.lastName = artist.getLastName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Transform a collection of Artists into a collection of BindableArtists.
     * @param artists : collection to be transformed.
     * @return : Collection of BindableArtists.
     */
    public static Collection<BindableArtist> bindableArtists(Collection<Artist> artists){
        Collection<BindableArtist> bindableArtists = new LinkedHashSet<BindableArtist>();
        for (Artist artist : artists){
            bindableArtists.add(new BindableArtist(artist));
        }
        return bindableArtists;
    }

    public Artist asArtist(){
        if (id != null)
            return new Artist(id, firstName,lastName);
        else
            return new Artist(firstName, lastName);
    }
}

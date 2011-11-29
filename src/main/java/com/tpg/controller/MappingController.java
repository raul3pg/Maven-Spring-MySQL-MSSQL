package com.tpg.controller;

import com.tpg.dao.ApplicationDAOImpl;
import com.tpg.model.Artist;
import com.tpg.model.Track;
import com.tpg.webmodel.BindableTrackToArtist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: raul.lepsa
 * Date: 11/29/11
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/map")
public class MappingController {

    @Autowired
    private ApplicationDAOImpl applicationDAO;


    @RequestMapping(method = RequestMethod.GET)
    public Collection<BindableTrackToArtist> getAllTracksAndArtists() {
        Set<BindableTrackToArtist> collection = new HashSet<BindableTrackToArtist>();
        Collection<Artist> allArtists = applicationDAO.getAllArtists();
        Collection<Track> allTracks = applicationDAO.getAllTracks();

        Iterator<Artist> artistIterator = allArtists.iterator();
        if (artistIterator.hasNext()){
            Artist artist = artistIterator.next();
            BindableTrackToArtist bindableTrackToArtist = new BindableTrackToArtist();
            bindableTrackToArtist.setArtist(artist);
            bindableTrackToArtist.setTracks(allTracks);
            collection.add(bindableTrackToArtist);
        }
        while (artistIterator.hasNext()){
            Artist artist = artistIterator.next();
            BindableTrackToArtist bindableTrackToArtist = new BindableTrackToArtist();
            bindableTrackToArtist.setArtist(artist);
            collection.add(bindableTrackToArtist);
        }
        return collection;
    }


    @RequestMapping(value = "/{artistId}/{trackId}", method = RequestMethod.GET)
    public String mapTrackToArtist(@PathVariable Integer artistId, @PathVariable Integer trackId){
        Artist artist = applicationDAO.getArtistById(artistId);
        Track track = applicationDAO.getTrackById(trackId);
        applicationDAO.mapTrackToArtist(track, artist);
        return "redirect:../../index";
    }
}
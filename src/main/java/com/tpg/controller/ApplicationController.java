package com.tpg.controller;

import com.tpg.dao.ApplicationDAOImpl;
import com.tpg.model.Artist;
import com.tpg.model.Track;
import com.tpg.webmodel.BindableArtist;
import com.tpg.webmodel.BindableTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: raul.lepsa
 * Date: 11/23/11
 * Time: 11:11 AM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/index")
public class ApplicationController {

    @Autowired
    private ApplicationDAOImpl applicationDAO;

    /**
     * Retrieves all the artists.
     * @return : A collection of existing artists.
     */

    @RequestMapping(method = RequestMethod.GET)
    public Collection<BindableArtist> getAllArtists(){
        return BindableArtist.bindableArtists(applicationDAO.getAllArtists());
    }
    /**
     * Retrieves all the tracks.
     * @return : A collection of existing tracks.
     */
//    @RequestMapping(method = RequestMethod.GET)
//    public Collection<BindableTrack> getAllTracks(){
//        return BindableTrack.bindableTracks(applicationDAO.getAllTracks());
//    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/artist/new", method = RequestMethod.GET)
    public String getArtist(Model model){
        return getArtist(null, model);
    }

    /**
     *
     * @param artistId
     * @param model
     * @return
     */
    @RequestMapping(value = "/artist/{artistId}", method = RequestMethod.GET)
    public String getArtist(@PathVariable Integer artistId, Model model){
        Artist artist = null;
        if (artistId != null){
            artist = applicationDAO.getArtistById(artistId);
        }
        if (artist != null){
            model.addAttribute(new BindableArtist(artist));
        }
        else{
            model.addAttribute(new BindableArtist());
        }
        return "artist";
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/track/new", method = RequestMethod.GET)
    public String getTrack(Model model){
        return getTrack(null, model);
    }

    /**
     *
     * @param trackId
     * @param model
     * @return
     */
    @RequestMapping(value = "/track/{trackId}", method = RequestMethod.GET)
    public String getTrack(@PathVariable Integer trackId, Model model){
        Track track = null;
        if (trackId != null){
            track = applicationDAO.getTrackById(trackId);
        }
        if (track != null){
            model.addAttribute(new BindableTrack(track));
        }
        else{
            model.addAttribute(new BindableTrack());
        }
        return "track";
    }

    /**
     *
     * @param artistId
     * @return
     */
    @RequestMapping(value = "/artist/{artistId}/delete", method = RequestMethod.GET)
    public String deleteArtistViaGet(@PathVariable Integer artistId){
        return deleteArtist(artistId);
    }

    /**
     *
     * @param artistId
     * @return
     */
    @RequestMapping(value = "/artist/{artistId}", method = RequestMethod.DELETE)
    public String deleteArtist(@PathVariable Integer artistId){
        applicationDAO.deleteArtistById(artistId);
        return "redirect:../../";
    }

    /**
     *
     * @param trackId
     * @return
     */
    @RequestMapping(value = "/track/{trackId}/delete", method = RequestMethod.GET)
    public String deleteTrackViaGet(@PathVariable Integer trackId){
        return deleteTrack(trackId);
    }

    /**
     *
     * @param trackId
     * @return
     */
    @RequestMapping(value = "/track/{trackId}", method = RequestMethod.DELETE)
    public String deleteTrack(@PathVariable Integer trackId){
        applicationDAO.deleteTrackById(trackId);
        return "redirect:../../";
    }

    /**
     *
     * @param bindableArtist
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/addArtist",method = RequestMethod.POST)
    public String saveArtist(@Valid BindableArtist bindableArtist, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "artist";
        }

        Artist artist = bindableArtist.asArtist();
        Artist existentArtist = applicationDAO.getArtistById(artist.getId());
        if (existentArtist != null){
            existentArtist.setFirstName(artist.getFirstName());
            existentArtist.setLastName(artist.getLastName());
            applicationDAO.updateArtist(existentArtist);
        }
        else{
            applicationDAO.insertArtist(artist);
        }

        return "redirect:../index";
    }

    /**
     *
     * @param bindableTrack
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/addTrack", method = RequestMethod.POST)
    public String saveTrack(@Valid BindableTrack bindableTrack, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "track";
        }

        Track track = bindableTrack.asTrack();
        Track existentTrack = applicationDAO.getTrackById(track.getId());
        if (existentTrack != null){
            existentTrack.setTitle(track.getTitle());
            applicationDAO.updateTrack(existentTrack);
        }
        else{
            applicationDAO.insertTrack(track);
        }
        return "redirect:../index";
    }
}

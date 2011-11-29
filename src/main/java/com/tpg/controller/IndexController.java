package com.tpg.controller;

import com.tpg.dao.ApplicationDAOImpl;
import com.tpg.model.Artist;
import com.tpg.model.Track;
import com.tpg.webmodel.BindableArtist;
import com.tpg.webmodel.BindableTrackToArtist;
import com.tpg.webmodel.BindableTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: raul.lepsa
 * Date: 11/23/11
 * Time: 11:11 AM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private ApplicationDAOImpl applicationDAO;


    @RequestMapping(method = RequestMethod.GET)
    public Collection<BindableTrackToArtist> getAllTracksToArtists(){
        return BindableTrackToArtist.bindableTracksToArtists(applicationDAO.getAllTracksToArtists());
    }

    @RequestMapping(value = "/artist/new", method = RequestMethod.GET)
    public String getArtist(Model model){
        return getArtist(null, model);
    }

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


    @RequestMapping(value = "/track/new", method = RequestMethod.GET)
    public String getTrack(Model model){
        return getTrack(null, model);
    }


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


    @RequestMapping(value = "/artist/{artistId}/delete", method = RequestMethod.GET)
    public String deleteArtistViaGet(@PathVariable Integer artistId){
        return deleteArtist(artistId);
    }


    @RequestMapping(value = "/artist/{artistId}", method = RequestMethod.DELETE)
    public String deleteArtist(@PathVariable Integer artistId){
        applicationDAO.deleteArtistById(artistId);
        return "redirect:../../";
    }


    @RequestMapping(value = "/track/{trackId}/delete", method = RequestMethod.GET)
    public String deleteTrackViaGet(@PathVariable Integer trackId){
        return deleteTrack(trackId);
    }


    @RequestMapping(value = "/track/{trackId}", method = RequestMethod.DELETE)
    public String deleteTrack(@PathVariable Integer trackId){
        applicationDAO.deleteTrackById(trackId);
        return "redirect:../../";
    }


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

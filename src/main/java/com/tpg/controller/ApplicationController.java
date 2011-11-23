package com.tpg.controller;

import com.tpg.dao.ApplicationDAOImpl;
import com.tpg.webmodel.BindableArtist;
import com.tpg.webmodel.BindableTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @RequestMapping(method = RequestMethod.GET)
    public Collection<BindableTrack> getAllTracks(){
        return BindableTrack.bindableTracks(applicationDAO.getAllTracks());
    }


}

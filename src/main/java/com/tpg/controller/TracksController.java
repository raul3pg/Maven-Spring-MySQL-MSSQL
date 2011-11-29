package com.tpg.controller;

import com.tpg.dao.ApplicationDAOImpl;
import com.tpg.webmodel.BindableTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: raul.lepsa
 * Date: 11/29/11
 * Time: 11:01 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/tracks")
public class TracksController {

    @Autowired
    private ApplicationDAOImpl applicationDAO;

    /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Collection<BindableTrack> getAllTracks(){
        return BindableTrack.bindableTracks(applicationDAO.getAllTracks());
    }
}

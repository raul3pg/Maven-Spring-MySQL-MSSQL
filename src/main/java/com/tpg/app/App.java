package com.tpg.app;

import com.tpg.dao.ApplicationDAO;
import com.tpg.model.Artist;
import com.tpg.model.Track;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main(String[] args) {

        // Get the application context
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/BeanLocations.xml");

        // Get the mobile business object bean
        ApplicationDAO myDAO = (ApplicationDAO)applicationContext.getBean("applicationDao");

        // Insert 2 artists
        Artist artist1 = new Artist("Armin", "van Buuren");
        myDAO.insertArtist(artist1);

        Artist artist2 = new Artist("Sophie", "Ellis Bextor");
        myDAO.insertArtist(artist2);

        // Insert a track belonging to both the artists
        Track track = new Track("Not giving up on love");
        myDAO.insertTrack(track);

        // Map the track to the artists
        myDAO.associateTrackToArtist(track,artist1);
        myDAO.associateTrackToArtist(track,artist2);
    }
}

package com.tpg.model;

/**
 * Created by IntelliJ IDEA.
 * User: raul.lepsa
 * Date: 11/21/11
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class Track {

    private int id;
    private String title;

    public Track() { }

    public Track(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package com.tpg.model;

/**
 * Created by IntelliJ IDEA.
 * User: raul.lepsa
 * Date: 11/21/11
 * Time: 2:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Artist {
    private int id;
    private String firstName;
    private String lastName;

    public Artist() {}

    public Artist(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}

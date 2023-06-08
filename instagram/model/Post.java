package com.instagram.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Represents the post of the user
 *
 * @author Arun
 * @version 1.1
 */
public class Post {

    private User user;
    private int id;
    private String caption;
    private String location;
    private LocalTime time;
    private LocalDate date;



    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(final LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public String toString() {
        return String.format("Id = %d, \tCaption = %s, \tLocation = %s", id, caption, location);
    }
}

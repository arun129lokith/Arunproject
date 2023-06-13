package com.instagram.model;

import java.sql.Timestamp;

/**
 * <p>
 * Represents the post entity of the user with various properties and methods.
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public class Post {

    private long userId;
    private long id;
    private String caption;
    private String location;
    private Timestamp timestamp;
    private Format format;

    public enum Format {
        IMAGE, VIDEO;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(final Format format) {
        this.format = format;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(final long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
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

    public String toString() {
        return String.format("Id = %d, Caption = %s, Location = %s, Time And Date = %s, User Id = %d, Format = %s",
                              id, caption, location, timestamp, userId, format);
    }
}

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

    private Long userId;
    private Long id;
    private String caption;
    private String location;
    private Timestamp uploadTime;
    private Format format;

    public enum Format {

        IMAGE, VIDEO;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(final Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(final Format format) {
        this.format = format;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
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
        return String.format("Id = %d, Caption = %s, Location = %s, Time And Date = %s, User Id = %d, Format = %s\n",
                              id, caption, location, uploadTime, userId, format);
    }
}

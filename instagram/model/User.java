package com.instagram.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents the user detail
 *
 * @author Arun
 * @version 1.1
 */
public class User {

    private long id;
    private String mobileNumber;
    private String name;
    private String email;
    private String password;
    private List<Post> posts;

    public User() {
        this.posts = new ArrayList<>();
    }

    public void addPost(final Post post) {
        posts.add(post);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String toString() {
        return String.format("Id = %s \tName = %s \tMobileNumber = %s \tEmail = %s",
                              id, name, mobileNumber, email);
    }
}

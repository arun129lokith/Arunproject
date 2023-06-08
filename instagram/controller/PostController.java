package com.instagram.controller;

import com.instagram.model.User;
import com.instagram.model.Post;
import com.instagram.service.PostService;
import com.instagram.service.impl.PostServiceImpl;

import java.util.Collection;

/**
 * Communicates with service provider and user
 */
public class PostController {

    private static final PostService POST_SERVICE = new PostServiceImpl();

    /**
     * Creates the user post.
     *
     * @param user  The user details
     * @param post The post detail of the user
     */
    public void createPost(final User user, final Post post) {
        POST_SERVICE.createPost(user, post);
    }

    /**
     * Gets the all post of the user
     *
     * @param user The user details
     * @return The collection of post
     */
    public Collection<Post> getAllPost(final User user) {
        return POST_SERVICE.getAllPost(user);
    }

    /**
     * Gets the post detail of the user
     *
     * @param id The post id of the user
     * @param user The user details
     * @return The post details of the user
     */
    public Post getPostDetail(final int id, final User user) {
        return POST_SERVICE.getPostDetail(id, user);
    }

    /**
     * Deletes the user post
     *
     * @param id The post id of the user
     * @param user The user details
     * @return True if post is deleted, false otherwise
     */
    public boolean deletePost(final int id, final User user) {
        return POST_SERVICE.deletePost(id, user);
    }

    /**
     * Updates the user post details
     *
     * @param user The user details
     * @param updatedPost The post of the user
     * @return True if post is updated, false otherwise
     */
    public boolean updatePost(final User user, final Post updatedPost) {
        return POST_SERVICE.updatePost(user, updatedPost);
    }
}

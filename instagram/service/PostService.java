package com.instagram.service;

import com.instagram.model.User;
import com.instagram.model.Post;

import java.util.Collection;

/**
 * Provides post service for the user
 *
 * @author Arun
 * @version 1.1
 */
public interface PostService {

    /**
     * Creates the user post.
     *
     * @param user The user details
     * @param post   The post detail of the user
     */
    void createPost(final User user, final Post post);

    /**
     * Gets the all post of the user
     *
     * @param user The user details
     * @return The collection of post
     */
    Collection<Post> getAllPost(final User user);

    /**
     * Gets the post detail of the user
     *
     * @param id The post id of the user
     * @param user The user details
     * @return The post details of the user
     */
    Post getPostDetail(final int id, final User user);

    /**
     * Deletes the user post
     *
     * @param id The post id of the user
     * @param user The user details
     * @return True if post is deleted, false otherwise
     */
    boolean deletePost(final int id, final User user);

    /**
     * Updates the user post details
     *
     * @param user The user details
     * @param post The post of the user
     * @return True if post is updated, false otherwise
     */
    boolean updatePost(final User user, final Post post);
}

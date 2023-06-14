package com.instagram.service;

import com.instagram.model.Post;

import java.util.Collection;

/**
 * <p>
 * Provides post service for the user.
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public interface PostService {

    /**
     * <p>
     * Creates the user post.
     * </p>
     *
     * @param post The post detail of the user.
     */
    void create(final Post post);

    /**
     * <p>
     * Gets the all post of the user.
     * </p>
     *
     * @return The collection of post.
     */
    Collection<Post> getAllPost();

    /**
     * <p>
     * Gets the post detail of the user.
     * </p>
     *
     * @param id The post id of the user.
     * @return The post details of the user.
     */
    Post getPost(final Long id);

    /**
     * <p>
     * Deletes the user post.
     * </p>
     *
     * @param id The post id of the user.
     * @return True if post is deleted, false otherwise.
     */
    boolean delete(final Long id);

    /**
     * <p>
     * Updates the user post details.
     * </p>
     *
     * @param updatedPost The updated post of the user.
     * @return True if post is updated, false otherwise.
     */
    void update(final Post updatedPost);
}

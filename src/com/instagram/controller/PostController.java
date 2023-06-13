package com.instagram.controller;

import com.instagram.model.Post;
import com.instagram.service.PostService;
import com.instagram.service.impl.PostServiceImpl;

import java.util.Collection;

/**
 * <p>
 * Handles the post related operation of the user and responsible for receiving user input and processing it.
 * </p>
 */
public class PostController {

    private static final PostService POST_SERVICE = new PostServiceImpl();

    /**
     * <p>
     * Creates the user post.
     * </p>
     *
     * @param post The post detail of the user.
     */
    public boolean createPost(final Post post) {
        return POST_SERVICE.createPost(post);
    }

    /**
     * <p>
     * Gets the all post of the user.
     * </p>
     *
     * @return The collection of post.
     */
    public Collection<Post> getAllPost() {
        return POST_SERVICE.getAllPost();
    }

    /**
     * <p>
     * Gets the post detail of the user.
     * </p>
     *
     * @param id The post id of the user
     * @return The post details of the user
     */
    public Post getPost(final long id) {
        return POST_SERVICE.getPost(id);
    }

    /**
     * <p>
     * Deletes the user post.
     * </p>
     *
     * @param id The post id of the user
     * @return True if post is deleted, false otherwise
     */
    public boolean deletePost(final long id) {
        return POST_SERVICE.deletePost(id);
    }

    /**
     * <p>
     * Updates the user post details.
     * </p>
     *
     * @param updatedPost The post of the user
     * @return True if post is updated, false otherwise
     */
    public boolean updatePost(final Post updatedPost) {
        return POST_SERVICE.updatePost(updatedPost);
    }
}

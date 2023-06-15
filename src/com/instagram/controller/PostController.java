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

    private static final PostService POST_SERVICE = PostServiceImpl.getInstance();
    private static PostController postController = null;

    private PostController() {}

    /**
     * <p>
     * Gets a static instance object of the class.
     * </p>
     *
     * @return The post controller object.
     */
    public static PostController getInstance() {
        if (null == postController) {
            postController = new PostController();
        }

        return postController;
    }

    /**
     * <p>
     * Creates the user post.
     * </p>
     *
     * @param post Represents {@link Post} details.
     */
    public void create(final Post post) {
        POST_SERVICE.create(post);
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
     * @param id Represents post id.
     * @return The post details of the user.
     */
    public Post getPost(final Long id) {
        return POST_SERVICE.getPost(id);
    }

    /**
     * <p>
     * Deletes the user post.
     * </p>
     *
     * @param id Represents post id.
     * @return True if post is deleted, false otherwise.
     */
    public boolean delete(final Long id) {
        return POST_SERVICE.delete(id);
    }

    /**
     * <p>
     * Updates the user post details.
     * </p>
     *
     * @param updatedPost Represents {@link Post} update details.
     * @return True if post is updated, false otherwise.
     */
    public void update(final Post updatedPost) {
        POST_SERVICE.update(updatedPost);
    }
}

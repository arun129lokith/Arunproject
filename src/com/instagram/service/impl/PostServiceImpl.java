package com.instagram.service.impl;

import com.instagram.model.Post;
import com.instagram.model.User;
import com.instagram.service.PostService;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * Handles the logic for post related operation and implements the post service of the user.
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public class PostServiceImpl implements PostService {

    private static final List<Post> POSTS = new ArrayList<>();
    private static long id = 0L;

    /**
     * {@inheritDoc}
     *
     * @param post The post object contains post details.
     * @return True if post is created, false otherwise.
     */
    @Override
    public boolean createPost(final Post post) {
        final User user = new User();

        post.setId(++id);
        user.addPost(post);

        return POSTS.add(post);
    }

    /**
     * {@inheritDoc}
     *
     * @return The collection of post.
     */
    @Override
    public Collection<Post> getAllPost() {
        return POSTS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Post getPost(final long id) {
        for (final Post post : POSTS) {

            if (post.getId() == id) {
                return post;
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deletePost(final long id) {
        final Post post = getPost(id);

        if (! POSTS.isEmpty()) {
            return POSTS.remove(post);
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updatePost(final Post updatedPost) {
        final User user = new User();

        for (int index = 0; index < POSTS.size(); index++) {

           /* if (user.getPosts().get(index).getId() == updatedPost.getId()) {
                user.getPosts().set(index, updatedPost);
            }*/

            if (POSTS.get(index).getId() == updatedPost.getId()) {
                POSTS.set(index, updatedPost);

                return true;
            }
        }

        return false;
    }
}

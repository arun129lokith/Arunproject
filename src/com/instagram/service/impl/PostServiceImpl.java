package com.instagram.service.impl;

import com.instagram.model.Post;
import com.instagram.model.User;
import com.instagram.service.PostService;
import com.instagram.view.UserView;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * Implements the service of the post related operation.
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public class PostServiceImpl implements PostService {

    private static final List<Post> POSTS = new ArrayList<>();
    private static Long id = 0L;
    private static PostServiceImpl postServiceImpl = null;

    private PostServiceImpl() {}

    /**
     * <p>
     * Gets a static instance object of the class.
     * </p>
     *
     * @return The post service implementation object.
     */
    public static PostServiceImpl getInstance() {
        if (null == postServiceImpl) {
            postServiceImpl = new PostServiceImpl();
        }

        return postServiceImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param post Represents {@link Post} details of the user.
     * @return True if post is created, false otherwise.
     */
    @Override
    public void create(final Post post) {
        final UserView userView = UserView.getInstance();
        final User user = userView.getUserById(post.getUserId()) ;

        post.setId(++id);
        user.addPost(post);
        POSTS.add(post);
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
     *
     * @param id Represents post id.
     * @return Represents {@link Post} details.
     */
    @Override
    public Post getPost(final Long id) {
        for (final Post post : POSTS) {

            if (post.getId().equals(id)) {
                return post;
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param id Represents post id.
     * @return True if post is removed, false otherwise.
     */
    @Override
    public boolean delete(final Long id) {
        final Post post = getPost(id);

        return POSTS.contains(post) && POSTS.remove(post);
    }

    /**
     * {@inheritDoc}
     *
     * @param updatedPost Represents {@link Post} update details.
     * @return True if post is updated, false otherwise.
     */
    @Override
    public void update(final Post updatedPost) {
        for (int index = 0; index < POSTS.size(); index++) {

            if (POSTS.get(index).getId().equals(updatedPost.getId())) {
                POSTS.set(index, updatedPost);

                break;
            }
        }
    }
}

package com.instagram.service.impl;

import com.instagram.model.User;
import com.instagram.model.Post;
import com.instagram.service.PostService;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Implements the post service of the user
 *
 * @author Arun
 * @version 1.1
 */
public class PostServiceImpl implements PostService {

    private static final Map<Long, List<Post>> USER_POSTS = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPost(final User user, final Post post) {
        List<Post> userPosts = USER_POSTS.getOrDefault(user.getId(), new ArrayList<>());

        userPosts.add(post);
        user.addPost(post);
        USER_POSTS.put(user.getId(), userPosts);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> getAllPost(final User user) {
        return USER_POSTS.getOrDefault(user.getId(), new ArrayList<>());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Post getPostDetail(final int id, final User user) {
        List<Post> posts = USER_POSTS.getOrDefault(user.getId(), new ArrayList<>());

        for (int index = 0; index < posts.size(); index++) {

            if (posts.get(index).getId().equals(id)) {
                return posts.get(index);
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deletePost(final int postId, final User user) {
        List<Post> posts = USER_POSTS.getOrDefault(user.getId(), new ArrayList<>());

        for (int index = 0; index < posts.size(); index++) {

            if (posts.get(index).getId().equals(postId)) {
                posts.remove(index);
                break;
            }

            return false;
        }
        USER_POSTS.put(user.getId(), posts);

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updatePost(final User user, final Post updatedPost) {
        List<Post> posts = USER_POSTS.getOrDefault(user.getId(), new ArrayList<>());

        for (int index = 0; index < posts.size(); index++) {

            if (posts.get(index).getId().equals(updatedPost.getId())) {
                posts.set(index, updatedPost);
                break;
            }

            return false;
        }
        USER_POSTS.put(user.getId(), posts);

        return true;
    }
}

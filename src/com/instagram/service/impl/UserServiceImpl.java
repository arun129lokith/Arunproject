package com.instagram.service.impl;

import com.instagram.model.User;
import com.instagram.service.UserService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Handles the logic for user related operation and implements the service of the user.
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public class UserServiceImpl implements UserService {

    private static final Map<Long, User> USERS = new HashMap<>();
    private static long id = 0L;

    /**
     * {@inheritDoc}
     *
     *
     */
    @Override
    public long signUp(final User user) {
        for (final User existingUser : USERS.values()) {

            if (user.getEmail().equals(existingUser.getEmail())) {
                return 0;
            }
        }
        user.setId(++id);
        USERS.put(user.getId(), user);

        return user.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long signIn(final User user) {
        for (Map.Entry<Long, User> entry : USERS.entrySet()) {
            final User existingUser = entry.getValue();

            if (existingUser.getEmail().equals(user.getEmail()) && existingUser.getPassword().equals(user.getPassword())
                    || existingUser.getMobileNumber().equals(user.getMobileNumber())
                    && existingUser.getPassword().equals(user.getPassword())) {
                return entry.getKey();
            }
        }

        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(final long id) {
        return USERS.containsKey(id) ? USERS.get(id) : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<User> getAllUsers() {
        return USERS.values();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateUser(final User user) {
        for (final User existingUser : USERS.values()) {

            if (existingUser.getId() == user.getId()) {
                existingUser.setName(user.getName());
                existingUser.setPassword(user.getPassword());
                existingUser.setEmail(user.getEmail());

                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteUserAccount(final long id) {
        if (USERS.containsKey(id)) {
            USERS.remove(id);

            return true;
        }

        return false;
    }

    @Override
    public User getUserById(final long id) {
        return USERS.containsKey(id) ? USERS.get(id) : null;
    }
}


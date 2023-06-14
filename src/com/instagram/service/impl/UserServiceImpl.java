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
    private static Long id = 0L;
    private static UserServiceImpl userServiceImpl = null;

    private UserServiceImpl() {}

    /**
     * <p>
     * Gets a static instance object of the class.
     * </p>
     *
     * @return The user service implementation object.
     */
    public static UserServiceImpl getInstance() {
        if (null == userServiceImpl) {
            userServiceImpl = new UserServiceImpl();
        }

        return userServiceImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param user The user object containing the details of the user.
     * @return True if sign-in is successful, false otherwise.
     */
    @Override
    public boolean signUp(final User user) {
        user.setId(++id);
        USERS.put(user.getId(), user);

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param user The user object containing the details of the user.
     * @return True if sign-in is successful, false otherwise.
     */
    @Override
    public boolean signIn(final User user) {
        return null != user.getMobileNumber() ? isMobileNumberExist(user) : isEmailExist(user);
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents te user detail.
     * @return True if mobile number is exists, false otherwise.
     */
    private boolean isMobileNumberExist(final User user) {
        for (final User existingUser : USERS.values()) {

            if (user.getMobileNumber().equals(existingUser.getMobileNumber())
                    && user.getPassword().equals(existingUser.getPassword())) {
                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents te user detail.
     * @return True if email is exists, false otherwise.
     */
    private boolean isEmailExist(final User user) {
        for (final User existingUser : USERS.values()) {

            if (existingUser.getEmail().equals(user.getEmail())
                    && existingUser.getPassword().equals(user.getPassword())) {
                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param id The id of the user.
     * @return The user details.
     */
    @Override
    public User getUser(final long id) {
        return USERS.containsKey(id) ? USERS.get(id) : null;
    }

    /**
     * {@inheritDoc}
     *
     * @return The collection of user details.
     */
    @Override
    public Collection<User> getAllUsers() {
        return USERS.values();
    }

    /**
     * {@inheritDoc}
     *
     * @param user The user object contains user details.
     * @return True if user details is update, false otherwise.
     */
    @Override
    public void updateUser(final User user) {
        for (final User existingUser : USERS.values()) {

            if (existingUser.getId().equals(user.getId())) {
                existingUser.setName(user.getName());
                existingUser.setPassword(user.getPassword());
                existingUser.setEmail(user.getEmail());
                break;
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param id The id of the user.
     * @return True if account is deleted, false otherwise.
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

    @Override
    public Long getId(final User user) {
        for (final Map.Entry<Long, User> entry : USERS.entrySet()) {
            final User existingUser = entry.getValue();

            if (existingUser.getEmail().equals(user.getEmail())
                    || existingUser.getMobileNumber().equals(user.getMobileNumber())) {
                return entry.getKey();
            }
        }

        return null;
    }

    @Override
    public boolean isNameExist(final String name) {
        for (final User existingUser : USERS.values()) {

            return existingUser.getName().equals(name);
        }

        return false;
    }

    public boolean isEmailExist(final String email) {
        for (final User existingUser : USERS.values()) {

            return existingUser.getEmail().equals(email);
        }

        return false;
    }

    public boolean isMobileNumberExist(final String mobileNumber) {
        for (final User existingUser : USERS.values()) {

            return existingUser.getMobileNumber().equals(mobileNumber);
        }

        return false;
    }

}


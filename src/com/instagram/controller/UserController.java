package com.instagram.controller;

import com.instagram.model.User;
import com.instagram.service.UserService;
import com.instagram.service.impl.UserServiceImpl;

import java.util.Collection;

/**
 * <p>
 * Handles the user related operation and responsible for receiving user input and processing it.
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public class UserController {

    private static final UserService USER_SERVICE = new UserServiceImpl();

    /**
     * <p>
     * Signs up a new user with user details of user class.
     * </p>
     *
     * @param user The user object contains details of the user.
     * @return True if sign-up is successful, false otherwise.
     */
    public long signUp(final User user) {
        return USER_SERVICE.signUp(user);
    }

    /**
     * <p>
     * Signs in a new user with user details of user class.
     * </p>
     *
     * @param user The user object contains details of the user.
     * @return True if sign-in is successful, false otherwise.
     */
    public long signIn(final User user) {
        return USER_SERVICE.signIn(user);
    }

    /**
     * <p>
     * Gets the user details of the user.
     * </p>
     *
     * @param id The id of the user.
     * @return The user details.
     */
    public User getUser(final long id) {
        return USER_SERVICE.getUser(id);
    }

    /**
     * <p>
     * Collects the all user information.
     * </p>
     *
     * @return The collection of user details.
     */
    public Collection<User> getAllUsers() {
        return USER_SERVICE.getAllUsers();
    }

    /**
     * <p>
     * Updates the user details.
     * </p>
     *
     * @param user The user object contains user details.
     * @return True if user details is update, false otherwise
     */
    public boolean updateUser(final User user) {
        return USER_SERVICE.updateUser(user);
    }

    /**
     * <p>
     * Deletes the user account details.
     * </p>
     *
     * @param id The id of the user.
     * @return True if account is deleted, false otherwise.
     */
    public boolean deleteUserAccount(final long id) {
        return USER_SERVICE.deleteUserAccount(id);
    }

    /**
     * <p>
     * Gets the user detail by the id of the user.
     * </p>
     *
     * @param id The id of the user.
     * @return The information of the user.
     */
    public User getUserById(final long id) {
        return USER_SERVICE.getUserById(id);
    }
}
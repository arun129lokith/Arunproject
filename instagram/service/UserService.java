package com.instagram.service;

import com.instagram.model.User;

import java.util.Collection;

/**
 * Provides service for the user
 *
 * @author Arun
 * @version 1.1
 */
public interface UserService {

    /**
     * Signs up a new user with user details of user class.
     *
     * @param user The user object containing the details of the user.
     * @return True if sign-up is successful, false otherwise.
     */
    boolean signUp(final User user);

    /**
     * Signs in a new user with user details of user class.
     *
     * @param user The user object containing the details of the user.
     * @return True if sign-in is successful, false otherwise.
     */
    boolean signIn(final User user);

    /**
     * Gets the user details of the user.
     *
     * @param id The id of the user.
     * @return The user details.
     */
    User getUser(final Long id);

    /**
     * Collects the all user information.
     *
     * @return The collection of user details.
     */
    Collection<User> getAllUsers();

    /**
     * Updates the user details
     *
     * @param user The user object contains user details.
     * @return True if user details is update, false otherwise
     */
    boolean isValidUpdate(final User user);

    /**
     * Deletes the user account details.
     *
     * @param email The email of the user.
     * @return True if account is deleted, false otherwise.
     */
    boolean deleteUserAccount(final String email);
}

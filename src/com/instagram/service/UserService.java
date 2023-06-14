package com.instagram.service;

import com.instagram.model.User;

import java.util.Collection;

/**
 * <p>
 * Provides service for the user
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public interface UserService {

    /**
     * <p>
     * Signs up a new user with user details of user class.
     * </p>
     *
     * @param user The user object containing the details of the user.
     * @return True if sign-up is successful, false otherwise.
     */
    boolean signUp(final User user);

    /**
     * <p>
     * Signs in a new user with user details of user class.
     * </p>
     *
     * @param user The user object containing the details of the user.
     * @return True if sign-in is successful, false otherwise.
     */
    boolean signIn(final User user);

    /**
     * <p>
     * Gets the user details of the user.
     * </p>
     *
     * @param id The id of the user.
     * @return The user details.
     */
    User getUser(final long id);

    /**
     * <p>
     * Collects the all user information.
     * </p>
     *
     * @return The collection of user details.
     */
    Collection<User> getAllUsers();

    /**
     * <p>
     * Updates the user details.
     * </p>
     *
     * @param user The user object contains user details.
     * @return True if user details is update, false otherwise.
     */
    void updateUser(final User user);

    /**
     * <p>
     * Deletes the user account details.
     * </p>
     *
     * @param id The id of the user.
     * @return True if account is deleted, false otherwise.
     */
    boolean deleteUserAccount(final long id);

    /**
     * <p>
     * Gets the user details by id of the user.
     * </p>
     *
     * @param id The id of the user.
     * @return The user information.
     */
    User getUserById(final long id);

    /**
     * <p>
     * Gets the user id of the user.
     * </p>
     *
     * @param user Represents user detail.
     * @return The user id of the user.
     */
    Long getId(final User user);

    /**
     * <p>
     * Checks the username is exists.
     * </p>
     *
     * @param name The name of the user.
     * @return True if username is exists, false otherwise.
     */
    boolean isNameExist(final String name);

    /**
     * <p>
     * Checks the email is exists.
     * </p>
     *
     * @param email The email of the user.
     * @return True if email is exists, false otherwise.
     */
    boolean isEmailExist(final String email);

    /**
     * <p>
     * Checks the mobile number is exists.
     * </p>
     *
     * @param mobileNumber The mobile number of the user.
     * @return True if mobile number is exists, false otherwise.
     */
    boolean isMobileNumberExist(final String mobileNumber);
}

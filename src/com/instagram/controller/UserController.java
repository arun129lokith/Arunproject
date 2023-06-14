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

    private static final UserService USER_SERVICE = UserServiceImpl.getInstance();
    private static UserController userController = null;

    private UserController() {}

    /**
     * <p>
     * Gets a static instance object of the class.
     * </p>
     *
     * @return The user controller object.
     */
    public static UserController getInstance() {
        if (null == userController) {
            userController = new UserController();
        }

        return userController;
    }

    /**
     * <p>
     * Signs up a new user with user details of user class.
     * </p>
     *
     * @param user The user object contains details of the user.
     * @return True if sign-up is successful, false otherwise.
     */
    public boolean signUp(final User user) {
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
    public boolean signIn(final User user) {
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
    public User getUser(final Long id) {
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
    public void updateUser(final User user) {
        USER_SERVICE.updateUser(user);
    }

    /**
     * <p>
     * Deletes the user account details.
     * </p>
     *
     * @param id The id of the user.
     * @return True if account is deleted, false otherwise.
     */
    public boolean deleteUserAccount(final Long id) {
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
    public User getUserById(final Long id) {
        return USER_SERVICE.getUserById(id);
    }

    /**
     * <p>
     * Gets the user id of the user.
     * </p>
     *
     * @param user Represents user detail.
     * @return The user id of the user.
     */
    public Long getId(final User user) {
        return USER_SERVICE.getId(user);
    }

    /**
     * <p>
     * Checks the username is exists.
     * </p>
     *
     * @param name The name of the user.
     * @return True if username is exists, false otherwise.
     */
    public boolean isNameExist(final String name) {
        return USER_SERVICE.isNameExist(name);
    }

    /**
     * <p>
     * Checks the email is exists.
     * </p>
     *
     * @param email The email of the user.
     * @return True if email is exists, false otherwise.
     */
    public boolean isEmailExist(final String email) {
        return USER_SERVICE.isEmailExist(email);
    }

    /**
     * <p>
     * Checks the mobile number is exists.
     * </p>
     *
     * @param mobileNumber The mobile number of the user.
     * @return True if mobile number is exists, false otherwise.
     */
    public boolean isMobileNumberExist(final String mobileNumber) {
        return USER_SERVICE.isMobileNumberExist(mobileNumber);
    }
}
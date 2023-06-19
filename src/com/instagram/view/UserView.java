package com.instagram.view;

import com.instagram.controller.UserController;
import com.instagram.model.Post;
import com.instagram.model.User;
import com.instagram.view.validation.Validation;

import java.util.Scanner;

/**
 * <p>
 * Displays the user option for sign in, signup and provides methods to render user data on the screen.
 * </p>
 *
 * @author Arun
 * @version 1.1
 */
public class UserView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final UserController USER_CONTROLLER = UserController.getInstance();
    private static final Validation USER_VALIDATION = Validation.getInstance();
    private static UserView userView = null;

    private UserView(){}

    /**
     * <p>
     * Gets a static instance object of the class.
     * </p>
     *
     * @return The user view object.
     */
    public static UserView getInstance() {
        if (null == userView) {
            userView = new UserView();
        }

        return userView;
    }

    public static void main(String[] args) {
        final UserView userView = getInstance();

        userView.menu();
    }

    /**
     * <p>
     * Gets the choice for user menu.
     * </p>
     */
    private void menu() {
        System.out.println("Click 1 To Sign Up \nClick 2 To Sign In \nClick 3 To Exit");

        switch (getChoice()) {
            case 1:
                signUp();
                break;
            case 2:
                signIn();
                break;
            case 3:
                exit();
                break;
            default:
                System.out.println("Invalid User Choice. Please Try Again\n[Enter the choice in the range 1-3]");
                menu();
                break;
        }
    }

    /**
     * <p>
     * Gets the valid username from the user.
     * </p>
     *
     * @return The valid username of the user.
     */
    private String getName() {
        System.out.println(String.join("\n", "Enter Your UserName:",
                "[Username Contains Lowercase Letter And Underscore And Digits Without Space]",
                "If You Want To Exit Press '!'"));
        final String name = SCANNER.nextLine().trim();

        exitMenu(name);

        return USER_VALIDATION.validateUserName(name) ? name : getName();
    }

    /**
     * <p>
     * Gets the valid username is not already exists in the application.
     * </p>
     *
     * @param name Represents user name.
     * @return The valid username.
     */
    private String getValidName(final String name) {
        if (USER_CONTROLLER.isNameExist(name)) {
            System.out.println("User Name Already Exist. Please Re-enter The Valid User Name");

            return getValidName(getName());
        }

        return name;
    }

    /**
     * <p>
     * Gets the valid email from the user.
     * </p>
     *
     * @return The valid email of the user.
     */
    private String getEmail() {
        System.out.println(String.join(" ", "Enter Your EmailId:",
                "\n[EmailId Must Contains Lowercase Letter[a-z] Then Contain Digits[0-9] Is Optional One",
                "'@' After Must Contains [5 Or Above] Lowercase Letter And '.' After Must Contains 2 Or 3 ",
                "Characters]\nIF You Want To Exit Press '!'"));
        final String email = SCANNER.nextLine().trim();

        exitMenu(email);

        return USER_VALIDATION.validateEmail(email) ? email : getEmail();
    }

    /**
     * <p>
     * Gets the email is not already exists in the application.
     * </p>
     *
     * @param email Represents user email.
     * @return The mobile number of the user.
     */
    private String getValidEmail(final String email) {
        if (USER_CONTROLLER.isEmailExist(email)) {
            System.out.println("User Email Is Already Exist. Please Re-enter The Valid User Name");

            return getValidEmail(getEmail());
        }

        return email;
    }

    /**
     * <p>
     * Gets the valid password from the user.
     * </p>
     *
     * @return The valid password of the user.
     */
    private String getPassword() {
        System.out.println(String.join(" ", "Enter Your Password:", "\n[Password Must Contain At least",
                "One Uppercase, One Lowercase, Special Character And Digits In The Range 8-20 Characters]",
                "\nIF You Want To Exit Press '!'"));
        final String password = SCANNER.nextLine().trim();

        exitMenu(password);

        return USER_VALIDATION.validatePassword(password) ? password : getPassword();
    }

    /**
     * <p>
     * Gets the valid mobile number from the user.
     * </p>
     *
     * @return The mobile number of the user.
     */
    private String getMobileNumber() {
        System.out.println(String.join(" ", "Enter Your Mobile Number:", "\n[Mobile Number Must",
                "Contains 10 Digits  And Starts With [6-9]]", "\nIf You Want To Exit Press '!'"));
        final String mobileNumber = SCANNER.nextLine().trim();

        exitMenu(mobileNumber);

        return USER_VALIDATION.validateMobileNumber(mobileNumber) ? mobileNumber : getMobileNumber();
    }

    /**
     * <p>
     * Gets the mobile number is not already exists in the application.
     * </p>
     *
     * @param mobileNumber Represents user mobile number.
     * @return The mobile number of the user.
     */
    private String getValidMobileNumber(final String mobileNumber) {
        if (USER_CONTROLLER.isMobileNumberExist(mobileNumber)) {
            System.out.println("User Mobile Number Is Already Exist. Please Re-enter The Valid User Name");

            return getValidMobileNumber(getMobileNumber());
        }

        return mobileNumber;
    }

    /**
     * <p>
     * Gets the valid choice from the user.
     * </p>
     *
     * @return The choice of the user.
     */
    public int getChoice() {
        System.out.println("Enter Your Choice:");

        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (final NumberFormatException message) {
            System.out.println("Invalid Choice. Please Enter An Integer");
        }

        return getChoice();
    }

    /**
     * <p>
     * Gets the user details of the user.
     * </p>
     *
     * @return Represents {@link User} information.
     */
    private User getUser() {
        System.out.println("Enter Your UserId:");
        final User user = USER_CONTROLLER.getUser(getUserId());

        System.out.println(null != user ? user : "User Not Found");

        return user;
    }

    /**
     * <p>
     * Gets the all user details.
     * </p>
     */
    private void getAllUsers() {
        System.out.println(USER_CONTROLLER.getAllUsers());
    }

    /**
     * <p>
     * Users to enter sign up details for sign up process.
     * </p>
     */
    private void signUp() {
        final User user = new User();

        user.setName(getValidName(getName()));
        user.setEmail(getValidEmail(getEmail()));
        user.setPassword(getPassword());
        user.setMobileNumber(getValidMobileNumber(getMobileNumber()));

        if (USER_CONTROLLER.signUp(user)) {
            System.out.println("Sign Up Successfully");

            if (exitAccess()) {
                menu();
            } else {
                userScreen(USER_CONTROLLER.getId(user));
            }
        }
        menu();
    }

    /**
     * <p>
     * Prints the features of the application.
     * </p>
     *
     * @param id Represents user id.
     */
    public void userScreen(final Long id) {
        System.out.println(String.join(" ","Click 1 To User Post Menu\nClick 2 To Logout", "\nClick 3",
                "To Get User\nClick 4 To Get All User \nClick 5 To Update User\nClick 6 To Delete User",
                "\nClick 7 To Main Menu\nClick 8 To Display Post Of The User"));
        final PostView postView = PostView.getInstance();

        switch (getChoice()) {
            case 1:
                postView.menu(id);
                break;
            case 2:
                logout();
                break;
            case 3:
                getUser();
                break;
            case 4:
                getAllUsers();
                break;
            case 5:
                update(id);
                break;
            case 6:
                delete();
                break;
            case 7:
                menu();
                break;
            case 8:
                displayPost();
                break;
            default:
                System.out.println("Invalid User Choice. Please Try Again\n[Enter The Choice In The Range 1-8]");
                userScreen(id);
        }
        userScreen(id);
    }

    /**
     * <p>
     * Displays the collection of user post.
     * </p>
     */
    private void displayPost() {
        System.out.println("Enter The User Id To Get Collection Of Post:");
        final User user = getUserById(getUserId());

        if (null != user) {

            if (! user.getPosts().isEmpty()) {

                for (final Post post : user.getPosts()) {

                    if (post.getUserId().equals(user.getId())) {
                        System.out.println(post);
                    }
                }
            } else {
                System.out.println("Post Not Created By The User");
            }
        } else {
            System.out.println("User Not Found.Please Try Again");
        }
    }

    /**
     * <p>
     * Gets the valid user id.
     * </p>
     *
     * @return The user id.
     */
    private long getUserId() {
        try {
            return Long.parseLong(SCANNER.nextLine());
        } catch (final NumberFormatException message) {
            System.out.println("Invalid User Id Format. Please Enter A Number");
        }

        return getUserId();
    }

    /**
     * <p>
     * Users to enter update details of the user information.
     * </p>
     *
     * @param id Represents user id.
     */
    private void update(final Long id) {
        final User user = new User();
        final User existingUser = getUserById(id);

        System.out.println(existingUser);
        user.setId(id);
        user.setName(exitAccess() ? existingUser.getName() : getValidName(getName()));
        user.setPassword(exitAccess() ? existingUser.getPassword() : getPassword());
        user.setEmail(exitAccess() ? existingUser.getEmail() : getValidEmail(getEmail()));
        user.setMobileNumber(exitAccess() ? existingUser.getMobileNumber() : getValidMobileNumber(getMobileNumber()));

        USER_CONTROLLER.update(user);
        System.out.println("Account Updated Successfully");
    }

    /**
     * <p>
     * Gets user information by id of the user.
     * </p>
     *
     * @param id Represents user id.
     * @return Represents {@link User} information.
     */
    public User getUserById(final Long id) {
        return USER_CONTROLLER.getUser(id);
    }

    /**
     * <p>
     * Users to delete his account.
     * </p>
     */
    private void delete() {
        System.out.println("Enter Your User Id:");
        if (USER_CONTROLLER.delete(getUserId())) {
            System.out.println("User Account Deleted Successfully");
            menu();
        } else {
            System.out.println("User Not Found. Please Try Again");
        }
    }

    /**
     * <p>
     * Users to log out the page.
     * </p>
     */
    private void logout() {
        System.out.println("Logged Out Successfully");

        if (exitAccess()) {
            SCANNER.close();
            System.exit(0);
        }
        menu();

    }

    /**
     * <p>
     * Users to enter sign in details to sign in process.
     * </p>
     */
    private void signIn() {
        final User user = new User();

        userChoice(user);
        user.setPassword(getPassword());

        if (USER_CONTROLLER.signIn(user)) {
            System.out.println("Sign in successfully");
            userScreen(USER_CONTROLLER.getId(user));
        } else {
            System.out.println("User Not Found. Please Try Again");
            menu();
        }
    }

    /**
     * <p>
     * Gets the user choice for sign in with email or mobile number.
     * </p>
     *
     * @param user Represents {@link User} details.
     */
    private void userChoice(final User user) {
        System.out.println("Click 1 To Get Email\nClick 2 To Get Mobile Number");

        switch (getChoice()) {
            case 1:
                user.setEmail(getEmail());
                break;
            case 2:
                user.setMobileNumber(getMobileNumber());
                break;
            default:
                System.out.println("Invalid User Choice. Please Enter the Choice 1 or 2");
                userChoice(user);
                break;
        }
    }

    /**
     * <p>
     * Exits the screen to menu.
     * </p>
     *
     * @param userChoice Represents the choice of the user.
     */
    private void exitMenu(final String userChoice) {
        if (USER_VALIDATION.backMenu(userChoice)) {
            menu();
        }
    }

    /**
     * <p>
     * Checks the process to continue or exit.
     * </p>
     *
     * @return True if exit the process, false otherwise.
     */
    public boolean exitAccess() {
        System.out.println(String.join(" ","Do You Want To Continue The Process Press 'Any Key Or",
                "Word' Else Press 'N Key Or No Word' For Exit The Process\nEnter Your Message For Continue Or Exit:"));

        return USER_VALIDATION.isExit(SCANNER.nextLine());
    }

    /**
     * <p>
     * Exits the user from the application.
     * </p>
     */
    private void exit() {
        System.out.println("Exiting");
        SCANNER.close();
        System.exit(0);
    }
}


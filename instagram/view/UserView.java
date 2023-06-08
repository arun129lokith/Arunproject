package com.instagram.view;

import com.instagram.controller.UserController;
import com.instagram.model.User;
import com.instagram.view.validation.UserValidation;

import java.util.Scanner;

/**
 * Displays the user option for sign in, signup and features of instagram.
 *
 * @author Arun
 * @version 1.1
 */
public class UserView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final UserController USER_CONTROLLER = new UserController();
    private static final UserValidation USER_VALIDATION = new UserValidation();
    private static long id = 0l;

    public static void main(String[] args) {
        final UserView userView = new UserView();

        userView.menu();
    }

    /**
     * Gets the choice for user menu.
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
     * Gets the valid username from the user.
     *
     * @return The valid username of the user.
     */
    private String getName() {
        System.out.println(String.join("\n", "Enter Your UserName:",
                "[Username Contains Lowercase Letter And Underscore And Digits]",
                "If You Want to Exit Press '!'"));
        final String name = SCANNER.nextLine().trim();

        exitBack(name);

        return USER_VALIDATION.validateUserName(name) ? name : getName();
    }

    /**
     * Gets the valid email from the user.
     *
     * @return The valid email of the user.
     */
    private String getEmail() {
        System.out.println(String.join(" ", "Enter Your EmailId:",
                "\n[EmailId Must Contain Lowercase Letter[a-z] Then Contain Digits[0-9]",
                "'@' After Must Contains [5-10] Lowercase Letter With Digits And '.' 2 Or 3 Characters]",
                "\nIF You Want To Exit Press '!'"));
        final String email = SCANNER.nextLine().trim();

        exitBack(email);

        return USER_VALIDATION.validateEmail(email) ? email : getEmail();
    }

    /**
     * Gets the valid password from the user.
     *
     * @return The valid password of the user.
     */
    private String getPassword() {
        System.out.println(String.join(" ", "Enter Your Password:", "\n[Password Must Contain At least",
                "One Uppercase, Special Character And Digits In The Range 8-20 Characters]",
                "\nIF You Want To Exit Press '!'"));
        final String password = SCANNER.nextLine().trim();

        exitBack(password);

        return USER_VALIDATION.validatePassword(password) ? password : getPassword();
    }

    /**
     * Gets the valid mobile number from the user.
     *
     * @return The mobile number of the user.
     */
    private String getMobileNumber() {
        System.out.println(String.join(" ", "Enter Your Mobile Number:", "\n[Mobile Number Must",
                "Contains 10 Digits  And Starts With [6-9]]", "\nIf You Want To Exit Press '!'"));
        final String mobileNumber = SCANNER.nextLine().trim();

        exitBack(mobileNumber);

        return USER_VALIDATION.validateMobileNumber(mobileNumber) ? mobileNumber : getMobileNumber();
    }

    /**
     * Gets the valid choice from the user.
     *
     * @return The choice of the user.
     */
    public int getChoice() {
        System.out.println("Enter Your Choice:");

        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException message) {
            System.out.println("Invalid Choice. Please Enter An Integer");
        }

        return getChoice();
    }

    /**
     * Gets the user details of the user.
     */
    public User getUser() {
        System.out.println("Enter Your UserId:");
        final User user = USER_CONTROLLER.getUser(Long.valueOf(SCANNER.nextLine()));

        System.out.println(user);

        return user;
    }

    /**
     * Gets the all user details.
     */
    private void getAllUsers() {
        System.out.println(USER_CONTROLLER.getAllUsers());
        userScreen();
    }

    /**
     * Users to enter sign up details and calls the controller to sign up the user.
     */
    private void signUp() {
        final User user = new User();

        user.setId(++id);
        user.setName(getName());
        user.setEmail(getEmail());
        user.setPassword(getPassword());
        user.setMobileNumber(getMobileNumber());

        if (USER_CONTROLLER.signUp(user)) {
            System.out.println("Sign Up Successfully");

            if (exitAccess()) {
                menu();
            } else {
                userScreen();
            }
        } else {
            System.out.println("User Already Exists. Please Try Again");
            menu();
        }
    }

    /**
     * Prints the user screen for instagram features
     */
    public void userScreen() {
        System.out.println(String.join(" ","Click 1 To User Post Menu\nClick 2 To Logout", "\nClick 3",
                "To Get User\nClick 4 To Get All User \nClick 5 To Update User\nClick 6 To Delete User",
                "\nClick 7 To Main Menu"));
        final User user = new User();
        final PostView postView = new PostView();

        switch (getChoice()) {
            case 1:
                postView.postMenu(user);
                break;
            case 2:
                logout();
                break;
            case 3:
                getUser();
                userScreen();
                break;
            case 4:
                getAllUsers();
                break;
            case 5:
                updateUserDetails();
                break;
            case 6:
                deleteUserAccount();
                break;
            case 7:
                menu();
                break;
            default:
                System.out.println("Invalid User Choice. Please Try Again\n[Enter The Choice In The Range 1-7]");
                userScreen();
        }
    }

    /**
     * Users to enter update details.
     */
    private void updateUserDetails() {
        final User user = new User();

        setUpdateDetails(user);

        if (USER_CONTROLLER.isValidUpdate(user)) {
            System.out.println("User Account Updated Successfully");
            userScreen();
        } else {
            System.out.println("User Account Not Updated");
            userScreen();
        }
    }

    /**
     * Sets the details of user to update
     *
     * @param user The user object containing user details
     */
    private void setUpdateDetails(final User user) {
        System.out.println("Get The User To Update The User Details");
        final User userDetails = getUser();

        user.setName(exitAccess() ? userDetails.getName() : getName());
        user.setPassword(exitAccess() ? userDetails.getPassword() : getPassword());
        user.setEmail(exitAccess() ? userDetails.getEmail() : getEmail());
    }

    /**
     * Users to delete his account.
     */
    private void deleteUserAccount() {
        System.out.println("Enter Your User Email:");

        if (USER_CONTROLLER.deleteUserAccount(SCANNER.nextLine())) {
            System.out.println("User Account Deleted Successfully");
            userScreen();
        } else {
            System.out.println("User Not Found. Please Try Again");
            userScreen();
        }
    }

    /**
     * Users to log out the page.
     */
    private void logout() {
        System.out.println("Logged Out Successfully");
        menu();
    }

    /**
     * Users to enter sign in details and calls the controller to sign in the user.
     */
    private void signIn() {
        final User user = new User();

        userChoice(user);
        user.setPassword(getPassword());

        if (USER_CONTROLLER.signIn(user)) {
            System.out.println("Sign in successfully");
            userScreen();
        } else {
            System.out.println("User Not Found. Please Try Again");
            menu();
        }
    }

    /**
     * Gets the user choice for sign in with email or mobile number.
     *
     * @param user The user object containing user details.
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

    private void exitBack(final String userChoice) {
        if (USER_VALIDATION.isBack(userChoice)) {
            menu();
        }
    }

    private boolean exitAccess() {
        System.out.println(String.join(" ","Do You Want To Continue The Process Press 'Any Word'",
                "Else Press 'No' For Exit The Process\nEnter Your Message For Continue Or Exit:"));

        return USER_VALIDATION.isExit(SCANNER.nextLine());
    }

    /**
     * Exits the user from the application.
     */
    private void exit() {
        System.out.println("Exiting");
        SCANNER.close();
        System.exit(0);
    }
}


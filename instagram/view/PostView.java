package com.instagram.view;

import com.instagram.controller.PostController;
import com.instagram.model.User;
import com.instagram.model.Post;
import com.instagram.view.validation.UserValidation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents the posts, posted by user
 *
 * @author Arun
 * @version 1.1
 */

public class PostView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final PostController POST_CONTROLLER = new PostController();
    private static final UserView USER_VIEW = new UserView();
    private static final UserValidation USER_VALIDATION = new UserValidation() ;
    private static int id = 0;

    /**
     * Prints the post menu of the user.
     */
    public void postMenu(final User user) {
        System.out.println(String.join(" ","Click 1 To Create Post\nClick 2 To Display All Post",
                "\nClick 3 To Delete Post\nClick 4 To Update Post\nClick 5 To Display Post\nClick 6 To User Screen",
                "\nDo You Want To Continue Press 'Any Word' And Press 'No' For User Screen\nEnter Your Message:"));

        if (USER_VALIDATION.isExit(SCANNER.nextLine())) {
            USER_VIEW.userScreen();
        }

        switch (USER_VIEW.getChoice()) {
            case 1:
                createPost(user);
                break;
            case 2:
                displayAllPost(user);
                break;
            case 3:
                deletePost(user);
                break;
            case 4:
                updatePost(user);
                break;
            case 5:
                getPostDetail();
                break;
            case 6:
                USER_VIEW.userScreen();
                break;
            default:
                System.out.println("Invalid User Choice. Please Enter The Choice In The Range[1-6]");
                postMenu(user);
                break;
        }
    }

    /**
     * Creates the post of the user
     *
     * @param user The user object containing user details
     */
    private void createPost(final User user) {
        final Post post = new Post();

        post.setId(++id);
        post.setLocation(getLocation());
        post.setCaption(getCaption());
        post.setTime(LocalTime.now());
        post.setDate(LocalDate.now());

        POST_CONTROLLER.createPost(user, post);
        System.out.println("User Posted Successfully");
        postMenu(user);
    }

    /**
     * Prints the all posts, posted by user
     *
     * @param user The user object containing user details
     */
    public void displayAllPost(final User user) {
        final User userDetails = USER_VIEW.getUser();

        System.out.println(POST_CONTROLLER.getAllPost(userDetails));
        postMenu(user);
    }

    /**
     * Gets the post detail of the user
     *
     * @return {@link Post} user post
     */
    public Post getPostDetail() {
        final User userDetail = USER_VIEW.getUser();

        System.out.println("Enter Your PostId:");
        final Post getDetails = POST_CONTROLLER.getPostDetail(Integer.valueOf(SCANNER.nextLine()),  userDetail);

        System.out.println(getDetails);

        return getDetails;
    }

    /**
     * Users to delete the post
     *
     * @param user The user object containing user details
     */
    public void deletePost(final User user) {
        final User userDetail = USER_VIEW.getUser();

        System.out.println("Enter Your PostId:");
        System.out.println(POST_CONTROLLER.deletePost(Integer.valueOf(SCANNER.nextLine()), userDetail) ?
                "Post Deleted Successfully" : "Post Not Found");
        postMenu(user);
    }

    /**
     * Sets the details of the user post to update
     *
     * @param user The user object containing user details
     */
    private void updatePost(final User user) {
        final Post userPost = new Post();
        final Post postDetail = getPostDetail();
        final User userDetail = USER_VIEW.getUser();

        System.out.println(String.join(" ","Do You Want to Edit User Caption Press 'Any Word' Else",
                "Press 'No' For Skip The Edit For User Caption"));

        if (USER_VALIDATION.isExit(SCANNER.nextLine())) {
            userPost.setCaption(postDetail.getCaption());
        } else {
            userPost.setCaption(getCaption());
        }
        System.out.println("Do You Want to Edit Password Press Any Word Else Press No For Skip The Edit User Password");

        if (USER_VALIDATION.isExit(SCANNER.nextLine())) {
            userPost.setLocation(postDetail.getLocation());
        } else {
            userPost.setLocation(getLocation());
        }

        if (POST_CONTROLLER.updatePost(userDetail, userPost)) {
            System.out.println("User Post Updated Successfully");
            postMenu(user);
        } else {
            System.out.println("User Post Not Updated");
            updatePost(user);
        }
    }

    /**
     * Gets user location.
     *
     * @return The location of the user.
     */
    private String getLocation() {
        System.out.println("Enter Your Location Of Your Post:");

        return SCANNER.nextLine();
    }

    /**
     * Gets user caption.
     *
     * @return The caption of the user.
     */
    private String getCaption() {
        System.out.println("Enter Your Caption:");

        return SCANNER.nextLine();
    }
}




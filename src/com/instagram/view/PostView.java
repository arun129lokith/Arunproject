package com.instagram.view;

import com.instagram.controller.PostController;
import com.instagram.model.Post;
import com.instagram.model.Post.Format;

import java.sql.Timestamp;
import java.time.Instant;

import java.util.Scanner;

/**
 * <p>
 * Displays post information of the user and provides methods to render post data on the screen.
 * </p>
 *
 * @author Arun
 * @version 1.1
 */

public class PostView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final PostController POST_CONTROLLER = PostController.getInstance();
    private static final UserView USER_VIEW = UserView.getInstance();
    private static PostView postView = null;

    private PostView() {}

    /**
     * <p>
     * Gets a static instance object of the class.
     * </p>
     *
     * @return The post view object.
     */
    public static PostView getInstance() {
        if (null == postView) {
            postView = new PostView();
        }

        return postView;
    }

    /**
     * <p>
     * Prints the post menu of the user.
     * </p>
     *
     * @param userId Represents user id.
     */
    public void menu(final Long userId) {
        System.out.println(String.join(" ","Click 1 To Create Post\nClick 2 To Display All Post",
                "\nClick 3 To Delete Post\nClick 4 To Update Post\nClick 5 To Display Single Post\nClick 6 To User",
                "Screen\nEnter Your Message:"));

        if ((USER_VIEW.exitAccess())) {
            USER_VIEW.userScreen(userId);
        }

        switch (USER_VIEW.getChoice()) {
            case 1:
                create(userId);
                break;
            case 2:
                displayAll();
                break;
            case 3:
                delete();
                break;
            case 4:
                update();
                break;
            case 5:
                getPost();
                break;
            case 6:
                USER_VIEW.userScreen(userId);
                break;
            default:
                System.out.println("Invalid User Choice. Please Enter The Choice In The Range[1-6]");
                menu(userId);
                break;
        }
        menu(userId);
    }

    /**
     * <p>
     * Gets user location.
     * </p>
     *
     * @return The location of the user.
     */
    private String getLocation() {
        System.out.println("Enter Your Location Of Your Post:");

        return SCANNER.nextLine();
    }

    /**
     * <p>
     * Gets user caption.
     * </p>
     *
     * @return The caption of the user.
     */
    private String getCaption() {
        System.out.println("Enter Your Caption:");

        return SCANNER.nextLine();
    }

    /**
     * <p>
     * Creates the post of the user.
     * </p>
     *
     * @param userId Represents user id.
     */
    private void create(final Long userId) {
        final Post post = new Post();

        post.setFormat(getFormat());
        post.setLocation(getLocation());
        post.setCaption(getCaption());
        post.setUploadTime(Timestamp.from(Instant.now()));
        post.setUserId(userId);

        POST_CONTROLLER.create(post);
        System.out.println("User Posted Successfully");
        menu(userId);
    }

    /**
     * <p>
     * Gets the format of the post of the user.
     * </p>
     *
     * @return Represents {@link Post} format of the user.
     */
    private Format getFormat() {
        System.out.println("Enter Post Format:\n[Post Format Must Be 'Image' or 'Video']");
        final String format = SCANNER.nextLine().toUpperCase();
        try {
            return Format.valueOf(format);
        } catch (IllegalArgumentException message) {
            System.out.println("Invalid Post Format. Please Try Again");
        }

        return getFormat();
    }

    /**
     * <p>
     * Prints the all posts, posted by user.
     * </p>
     */
    public void displayAll() {
        System.out.println(POST_CONTROLLER.getAllPost());
    }

    /**
     * <p>
     * Gets the post detail of the user.
     * </p>
     *
     * @return Represents {@link Post} user post.
     */
    public Post getPost() {
        System.out.println("Enter Your PostId:");
        final Post post = POST_CONTROLLER.getPost(Long.parseLong(SCANNER.nextLine()));

        System.out.println(post);

        return post;
    }

    /**
     * <p>
     * Users to delete the post.
     * </p>
     */
    public void delete() {
        System.out.println("Enter Your PostId:");
        System.out.println(POST_CONTROLLER.delete(Long.parseLong(SCANNER.nextLine())) ? "Post Deleted Successfully"
                : "Post Not Found");
    }

    /**
     * <p>
     * Sets the details of the user post to update.
     * </p>
     */
    private void update() {
        System.out.println("Get The Post Of The User To Update Post Details");
        final Post post = new Post();
        final Post detail = getPost();

        if (null != detail) {
            post.setId(detail.getId());
            post.setLocation(USER_VIEW.exitAccess() ? detail.getLocation() : getLocation());
            post.setCaption(USER_VIEW.exitAccess() ? detail.getCaption() : getCaption());
            post.setUploadTime(Timestamp.from(Instant.now()));

            POST_CONTROLLER.update(post);
            System.out.println("User Post Updated Successfully");
        } else {
            System.out.println("Post Not Found. Please Try Again");
        }
    }
}




package com.instagram.view;

import com.instagram.controller.PostController;
import com.instagram.model.Post;

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
    private static final PostController POST_CONTROLLER = new PostController();
    private static final UserView USER_VIEW = UserView.getInstance();
    private static PostView postView = null;

    private PostView() {}

    public static PostView getInstance() {
        if (postView == null) {
            postView = new PostView();
        }

        return postView;
    }

    /**
     * <p>
     * Prints the post menu of the user.
     * </p>
     */
    public void menu(final long id) {
        System.out.println(String.join(" ","Click 1 To Create Post\nClick 2 To Display All Post",
                "\nClick 3 To Delete Post\nClick 4 To Update Post\nClick 5 To Display Single Post\nClick 6 To User",
                "Screen\nEnter Your Message:"));
        System.out.println(id);

        if ((USER_VIEW.exitAccess())) {
            USER_VIEW.userScreen(id);
        }

        switch (USER_VIEW.getChoice()) {
            case 1:
                createPost(id);
                break;
            case 2:
                displayAllPost();
                break;
            case 3:
                deletePost();
                break;
            case 4:
                updatePost();
                break;
            case 5:
                getPost();
                break;
            case 6:
                USER_VIEW.userScreen(id);
                break;
            default:
                System.out.println("Invalid User Choice. Please Enter The Choice In The Range[1-6]");
                menu(id);
                break;
        }
        menu(id);
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
     * @param id The id of the user.
     */
    private void createPost(final long id) {
        final Post post = new Post();

        getFormat(post);
        post.setLocation(getLocation());
        post.setCaption(getCaption());
        post.setTimestamp(Timestamp.from(Instant.now()));
        System.out.println(id);
        post.setUserId(id);

        System.out.println(POST_CONTROLLER.createPost(post) ? "User Posted Successfully" : "User Post Not Created");
        menu(id);
    }

    /**
     * <p>
     * Gets the format of the post of the user.
     * </p>
     *
     * @param post The post object containing post detail.
     */
    private void getFormat(final Post post) {
        System.out.println("Enter Post Format\nClick 1 To Image\nClick 2 To Video");

        switch (USER_VIEW.getChoice()) {
            case 1:
                post.setFormat(Post.Format.IMAGE);
                break;
            case 2:
                post.setFormat(Post.Format.VIDEO);
                break;
            default:
                System.out.println("Invalid User Choice.Please Enter 1 Or 2 To Get Post Format");
                getFormat(post);
                break;
        }
    }

    /**
     * <p>
     * Prints the all posts, posted by user.
     * </p>
     */
    public void displayAllPost() {
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
    public void deletePost() {
        System.out.println("Enter Your PostId:");
        System.out.println(POST_CONTROLLER.deletePost(Long.parseLong(SCANNER.nextLine())) ? "Post Deleted Successfully"
                : "Post Not Found");
    }

    /**
     * <p>
     * Sets the details of the user post to update.
     * </p>
     */
    private void updatePost() {
        System.out.println("Get The Post Of The User To Update Post Details");
        final Post post = new Post();
        final Post detail = getPost();

        if (detail != null) {
            post.setId(detail.getId());
            post.setLocation(USER_VIEW.exitAccess() ? detail.getLocation() : getLocation());
            post.setCaption(USER_VIEW.exitAccess() ? detail.getCaption() : getCaption());
            post.setTimestamp(Timestamp.from(Instant.now()));

            System.out.println(POST_CONTROLLER.updatePost(post) ? "User Post Updated Successfully" : "Post Not Updated");
        } else {
            System.out.println("Post Not Found. Please Try Again");
            updatePost();
        }
    }
}




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
                "\nClick 3 To Delete Post\nClick 4 To Update Post\nClick 5 To Display Post By Id\nClick 6 To User",
                "Screen"));

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
        System.out.println("Click 1 To Image Format\nClick 2 To Video Format");
        final Format format = Format.findFormat(USER_VIEW.getChoice());

        if (null != format) {
            return format;
        } else {
            System.out.println("Invalid Post Format Choice. Please Enter The Choice For Post Format In The Range[1-2]");

            return getFormat();
        }
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
    private Post getPost() {
        System.out.println("Enter Your PostId:");
        final Post post = POST_CONTROLLER.getPost(getPostId());

        System.out.println(null != post ? post : "Post Not Found");

        return post;
    }

    /**
     * <p>
     * Users to delete the post.
     * </p>
     */
    public void delete() {
        System.out.println("Enter Your PostId:");
        System.out.println(POST_CONTROLLER.delete(getPostId()) ? "Post Deleted Successfully"
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
        final Post existingPost = getPost();

        if (null != existingPost) {
            post.setId(existingPost.getId());
            post.setUserId(existingPost.getUserId());
            post.setFormat(existingPost.getFormat());
            post.setLocation(USER_VIEW.exitAccess() ? existingPost.getLocation() : getLocation());
            post.setCaption(USER_VIEW.exitAccess() ? existingPost.getCaption() : getCaption());
            post.setUploadTime(Timestamp.from(Instant.now()));

            POST_CONTROLLER.update(post);
            System.out.println("Post Updated Successfully");
        } else {
            System.out.println("Post Not Found. Please Try Again");
        }
    }

    /**
     * <p>
     * Gets valid post id of the user.
     * </p>
     *
     * @return The post id of the user.
     */
    private long getPostId() {
        try {
            return Long.parseLong(SCANNER.nextLine());
        } catch (final NumberFormatException message) {
            System.out.println("Invalid Post Id Format. Please Enter A Number");
        }

        return getPostId();
    }
}




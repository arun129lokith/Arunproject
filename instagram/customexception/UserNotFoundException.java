package com.instagram.customexception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(final String message) {
        super(message);
    }
}

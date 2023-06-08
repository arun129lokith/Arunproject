package com.instagram.customexception;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(final String message) {
        super(message);
    }
}

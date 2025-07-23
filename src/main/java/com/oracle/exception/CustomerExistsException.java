package com.oracle.exception;

public class CustomerExistsException extends RuntimeException {

    public CustomerExistsException(String message) {
        super(message);
    }
}

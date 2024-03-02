package com.example.demo.exception;

public class NotNullValueException extends RuntimeException {
    public NotNullValueException() {
        super();
    }

    public NotNullValueException(String message) {
        super(message);
    }
}

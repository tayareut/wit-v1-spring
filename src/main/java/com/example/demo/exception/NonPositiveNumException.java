package com.example.demo.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NonPositiveNumException extends RuntimeException {

    public NonPositiveNumException(String message) {
        super(message);
    }

}

package com.example.demo.hendler;

import com.example.demo.dto.ErrorDto;
import com.example.demo.exception.NonPositiveNumException;
import com.example.demo.exception.NotNullValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class ValidValueExceptionHandler {
    Logger logger = LoggerFactory.getLogger(ValidValueExceptionHandler.class);

    @ExceptionHandler(NotNullValueException.class)
    public ResponseEntity<ErrorDto> handleException(NotNullValueException exception) {
        logger.warn("There is empty field: {}", exception.getMessage());

        logger.warn(Arrays.toString(exception.getStackTrace()));
        return ResponseEntity.badRequest().body(new ErrorDto(400, exception.getMessage()));
    }

    @ExceptionHandler(NonPositiveNumException.class)
    public ResponseEntity<ErrorDto> handleException(NonPositiveNumException exception) {
        logger.warn("There is not positive number: {}", exception.getMessage());

        logger.warn(Arrays.toString(exception.getStackTrace()));
        return ResponseEntity.badRequest().body(new ErrorDto(400, exception.getMessage()));
    }
}

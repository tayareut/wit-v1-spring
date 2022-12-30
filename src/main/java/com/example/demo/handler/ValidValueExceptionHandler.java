package com.example.demo.handler;

import com.example.demo.dto.ErrorDto;
import com.example.demo.exception.NonPositiveNumException;
import com.example.demo.exception.NotNullValueException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@Slf4j
@ControllerAdvice
public class ValidValueExceptionHandler {

    @ExceptionHandler(NotNullValueException.class)
    public ResponseEntity<ErrorDto> handleException(NotNullValueException exception) {
        log.warn("There is empty field: {}", exception.getMessage());

        log.warn(Arrays.toString(exception.getStackTrace()));
        return ResponseEntity.badRequest().body(new ErrorDto(400, exception.getMessage()));
    }

    @ExceptionHandler(NonPositiveNumException.class)
    public ResponseEntity<ErrorDto> handleException(NonPositiveNumException exception) {
        log.warn("There is not positive number: {}", exception.getMessage());

        log.warn(Arrays.toString(exception.getStackTrace()));
        return ResponseEntity.badRequest().body(new ErrorDto(400, exception.getMessage()));
    }
}

package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {
    private int status;
    private HttpStatus httpStatus;
    private String message;
    private Object errors;

    public ErrorDto(final int status, final HttpStatus httpStatus, final String errors) {
        this.status = status;
        this.httpStatus = httpStatus;
        this.errors = errors;
    }
}

package com.example.confectioneryApp.exceptionhandling;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ApiError {

    private HttpStatus status;
    private List<String> errors;

    public ApiError(HttpStatus status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String error) {
        this.status = status;
        errors = Arrays.asList(error);
    }
}

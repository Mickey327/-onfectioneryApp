package com.example.confectioneryApp.exceptionhandling;

import com.example.confectioneryApp.category.CategoryNotFoundException;
import com.example.confectioneryApp.exceptionhandling.ApiError;
import com.example.confectioneryApp.product.ProductNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionControllerAdvice  extends ResponseEntityExceptionHandler {
    @ExceptionHandler({CategoryNotFoundException.class, ProductNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundExceptions(Exception ex) {
        ApiError apiError =
                new ApiError(NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }
}

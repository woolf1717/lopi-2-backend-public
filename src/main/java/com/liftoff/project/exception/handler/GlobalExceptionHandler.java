package com.liftoff.project.exception.handler;

import com.liftoff.project.exception.CannotDeleteCategoryException;
import com.liftoff.project.exception.CategoryNotFoundException;
import com.liftoff.project.exception.InvalidParentCategoryException;
import com.liftoff.project.exception.LoginAuthenticationException;
import com.liftoff.project.exception.ParentCategoryNotFoundException;
import com.liftoff.project.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFoundException(ProductNotFoundException ex) {
        return createErrorResponse(ex.getMessage(), ex.getStatus());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return createErrorResponse(ex.getMessage(), ex.getStatus());
    }

    @ExceptionHandler(ParentCategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleParentCategoryNotFoundException(ParentCategoryNotFoundException ex) {
        return createErrorResponse(ex.getMessage(), ex.getStatus());
    }

    @ExceptionHandler(CannotDeleteCategoryException.class)
    public ResponseEntity<Map<String, String>> handleCannotDeleteCategoryException(CannotDeleteCategoryException ex) {
        return createErrorResponse(ex.getMessage(), ex.getStatus());
    }

    @ExceptionHandler(InvalidParentCategoryException.class)
    public ResponseEntity<Map<String, String>> handleInvalidParentCategoryException(InvalidParentCategoryException ex) {
        return createErrorResponse(ex.getMessage(), ex.getStatus());

    }

    @ExceptionHandler(LoginAuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleInvalidLoginException(LoginAuthenticationException ex) {
        return createErrorResponse(ex.getMessage(), ex.getStatus());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleInvalidLoginFieldsException(MethodArgumentNotValidException ex) {


        return createErrorResponse(ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(joining(", ")), HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<Map<String, String>> createErrorResponse(String errorMessage, HttpStatus status) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", errorMessage);
        return ResponseEntity.status(status).body(errorResponse);
    }

}

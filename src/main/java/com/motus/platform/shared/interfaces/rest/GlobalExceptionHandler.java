package com.motus.platform.shared.interfaces.rest;

import com.motus.platform.shared.domain.exceptions.ConflictException;
import com.motus.platform.shared.domain.exceptions.ResourceNotFoundException;
import com.motus.platform.shared.interfaces.rest.resources.ErrorResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResource> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResource(Instant.now(), 404, "RESOURCE_NOT_FOUND", ex.getMessage(), Map.of()));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResource> handleConflict(ConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResource(Instant.now(), 409, "RESOURCE_CONFLICT", ex.getMessage(), Map.of()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResource> handleValidation(MethodArgumentNotValidException ex) {
        var details = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(e -> e.getField(), e -> e.getDefaultMessage() != null ? e.getDefaultMessage() : "Invalid", (a, b) -> a));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResource(Instant.now(), 400, "VALIDATION_ERROR", "Validation failed", details));
    }
}
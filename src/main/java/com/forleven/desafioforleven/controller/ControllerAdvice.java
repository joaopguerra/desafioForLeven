package com.forleven.desafioforleven.controller;

import com.forleven.desafioforleven.exception.ConflictException;
import com.forleven.desafioforleven.exception.StudentNotFound;
import com.forleven.desafioforleven.exception.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(StudentNotFound.class)
    public ResponseEntity<StandardError> entityNotFound(StudentNotFound e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError();
        standardError.setTimestamp(Instant.now());
        standardError.setStatus(status.value());
        standardError.setError(status.name());
        standardError.setMessage(e.getMessage());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<StandardError> entityNotFound(ConflictException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError standardError = new StandardError();
        standardError.setTimestamp(Instant.now());
        standardError.setStatus(status.value());
        standardError.setError(status.name());
        standardError.setMessage(e.getMessage());
        standardError.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }
}

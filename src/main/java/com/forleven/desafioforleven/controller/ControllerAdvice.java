package com.forleven.desafioforleven.controller;

import com.forleven.desafioforleven.exception.EntityNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<Object> handleNotFoundException(EntityNotFound entityNotFound){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entityNotFound.getMessage());
    }
}

package com.forleven.desafioforleven.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {
    private HttpStatus status;

    public NotFoundException() {
    }

    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}

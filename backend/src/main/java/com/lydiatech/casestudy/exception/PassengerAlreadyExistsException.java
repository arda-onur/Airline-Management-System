package com.lydiatech.casestudy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class PassengerAlreadyExistsException extends RuntimeException {
    public PassengerAlreadyExistsException(String message) {
        super(message);
    }
}

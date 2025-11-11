package com.lydiatech.casestudy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ThereIsNoSuchPassengerException extends RuntimeException {
    public ThereIsNoSuchPassengerException(String message) {
        super(message);
    }
}

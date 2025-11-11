package com.lydiatech.casestudy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ThereIsNoSuchFlightException extends RuntimeException {
    public ThereIsNoSuchFlightException(String message) {
        super(message);
    }
}

package com.lydiatech.casestudy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class FlightCannotCreateException extends RuntimeException {
    public FlightCannotCreateException(String message) {
        super(message);
    }
}

package com.lydiatech.casestudy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class FlightDateExpiredExcepiton extends RuntimeException {
    public FlightDateExpiredExcepiton(String message) {
        super(message);
    }
}

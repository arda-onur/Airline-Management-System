package com.lydiatech.casestudy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class BookingCannotCreateException extends RuntimeException {
    public BookingCannotCreateException(String message) {
        super(message);
    }
}

package com.lydiatech.casestudy.exception;

public class ThereIsNoSuchIcaoCodeException extends RuntimeException {
    public ThereIsNoSuchIcaoCodeException(String message) {
        super(message);
    }
}

package com.gsnimbus.api.exception;

public class ApiFetchErrorException extends RuntimeException {
    public ApiFetchErrorException(String message) {
        super(message);
    }
}

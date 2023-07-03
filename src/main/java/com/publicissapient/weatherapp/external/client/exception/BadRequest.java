package com.publicissapient.weatherapp.external.client.exception;

public class BadRequest extends Exception {
    private String message;
    private Throwable throwable;


    public BadRequest(String message) {
        super(message);
    }

    public BadRequest(String message, Throwable throwable) {
        super(message, throwable);
    }
}

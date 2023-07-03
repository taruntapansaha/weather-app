package com.publicissapient.weatherapp.external.client.exception;

public class DownstreamServerError extends Exception {
    private String message;
    private Throwable throwable;

    public DownstreamServerError(String message, Throwable throwable) {
        super(message, throwable);
    }
    
}

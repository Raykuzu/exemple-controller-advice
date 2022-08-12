package com.example.demo;

public class CustomException extends RuntimeException {

    public CustomException(final String message) {
        super("[CUSTOM] " + message);
    }
}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RESTExceptionHandler {

    private final KafkaMock kafka;

    @Autowired
    private RESTExceptionHandler(KafkaMock kafka) {
        this.kafka = kafka;
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<String> handleNotFound(final NoSuchElementException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IllegalArgumentException.class, CustomException.class})
    public ResponseEntity<String> handleBadRequest(final Exception ex) {
        if (ex instanceof CustomException) {
            // Custom handling
        }
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IllegalDBStateException.class})
    public ResponseEntity<String> handleDBProblems(final IllegalDBStateException ex) {
        // Kafka reporter
        kafka.sendMessage("reports.db", ex.getReport());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleGeneralException(final Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

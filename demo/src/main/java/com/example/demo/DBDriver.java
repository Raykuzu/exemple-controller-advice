package com.example.demo;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.UUID;

@Service
public class DBDriver {

    public User getUser(@NonNull final String id) {
        Random rand = new Random();
        int randomNum = rand.ints(1, 0, 10).findFirst().orElseThrow();

        if (randomNum == 8) {
            throw new NoSuchElementException("User not found");
        } else if (randomNum == 9) {
            throw new IllegalDBStateException("USER", "Multiple user found for a single id", UUID.randomUUID().toString());
        } else if (randomNum == 10) {
            // The runtime exception is corresponding to the exception you catch from the db
            // you need to provide with ServerErrorException
            throw new ServerErrorException("Something bad happend", new RuntimeException());
        }
        return new User("John Doe", 35);
    }
}

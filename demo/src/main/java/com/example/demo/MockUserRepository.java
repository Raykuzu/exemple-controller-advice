package com.example.demo;

import lombok.NonNull;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MockUserRepository {

    private static final int STANDARD_ID_LENGTH = 24;

    private final DBDriver dbDriver;

    @Autowired
    private MockUserRepository(final DBDriver dbDriver) {
        this.dbDriver = dbDriver;
    }

    public User getUser(@NonNull final String id) {

        // Imagine some controls

        if (id.length() != STANDARD_ID_LENGTH) {
            throw new IllegalArgumentException("L'id doit faire exactement 24 caracteres");
        }
        if (!Base64.isBase64(id.getBytes())) {
            throw new CustomException("L'id doit être au format base64");
        }

        return dbDriver.getUser(id);
    }
}

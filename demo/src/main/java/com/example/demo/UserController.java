package com.example.demo;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final MockUserRepository userRepository;

    @Autowired
    private UserController(final MockUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<User> getMyUserProfile(
            @NonNull @RequestParam("id") final String myId
    ) {
        return ResponseEntity.ok(userRepository.getUser(myId));
    }
}

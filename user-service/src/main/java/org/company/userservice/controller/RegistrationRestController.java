package org.company.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.company.userservice.model.User;
import org.company.userservice.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationRestController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/register", consumes = "application/json")
    public User register(@RequestBody User user) {
        String encode = passwordEncoder.encode(user.getPassword());
        return userService.registerUser(user, encode);
    }
}

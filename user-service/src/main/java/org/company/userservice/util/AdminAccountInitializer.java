package org.company.userservice.util;

import lombok.RequiredArgsConstructor;
import org.company.userservice.enums.Role;
import org.company.userservice.model.User;
import org.company.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Component
@RequiredArgsConstructor
public class AdminAccountInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!userRepository.existsByUsername("admin")) {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin"));
            adminUser.setRoles(EnumSet.of(Role.ROLE_ADMIN, Role.ROLE_USER));
            userRepository.save(adminUser);
        }
    }
}

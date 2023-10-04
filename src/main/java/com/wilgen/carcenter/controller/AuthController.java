package com.wilgen.carcenter.controller;

import com.wilgen.carcenter.dto.CreateUserDTO;
import com.wilgen.carcenter.model.ERole;
import com.wilgen.carcenter.model.Role;
import com.wilgen.carcenter.model.User;
import com.wilgen.carcenter.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO createUserDTO) {
        Set<Role> roles = createUserDTO.getRoles().stream()
                .map(role -> Role.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        User user = User.builder()
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .email(createUserDTO.getEmail())
                .roles(roles)
                .build();

        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

}

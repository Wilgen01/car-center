package com.wilgen.carcenter.controller;

import com.wilgen.carcenter.dto.CreateUserDTO;
import com.wilgen.carcenter.dto.Response;
import com.wilgen.carcenter.dto.SuccessResponse;
import com.wilgen.carcenter.model.ERole;
import com.wilgen.carcenter.model.Role;
import com.wilgen.carcenter.model.User;
import com.wilgen.carcenter.repository.UserRepository;
import com.wilgen.carcenter.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
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

        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/profile")
    public ResponseEntity<Response> getProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        try {
            User user = userService.findByEmail(email);
            user.setPassword(null);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SuccessResponse<>("Get Profile", "ok", user));
        } catch (EntityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new Response(e.getMessage(), "error"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response(e.getMessage(), "error"));
        }
    }

}

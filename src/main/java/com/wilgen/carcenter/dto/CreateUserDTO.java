package com.wilgen.carcenter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String email;
    private String username;
    @NotNull
    @NotBlank
    private String password;
    private Set<String> roles;
}

package com.example.bds.api.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String fullname;
    @NotEmpty(message = "Username cannot be blank")
    private String username;
    private String email;
    @NotEmpty(message = "Password cannot be blank")
    private String password;
    private List<String> roles;
}

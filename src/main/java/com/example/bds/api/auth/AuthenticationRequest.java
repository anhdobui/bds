package com.example.bds.api.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @NotEmpty(message = "Username cannot be blank")
    private String username;
    @NotEmpty(message = "Password cannot be blank")
    private String password;
}

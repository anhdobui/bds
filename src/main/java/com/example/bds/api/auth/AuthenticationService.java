package com.example.bds.api.auth;

import com.example.bds.config.JwtTokenProvider;
import com.example.bds.constant.SystemConstant;
import com.example.bds.entity.RoleEntity;
import com.example.bds.entity.UserEntity;
import com.example.bds.exception.CustomRuntimeException;
import com.example.bds.exception.NotFoundException;
import com.example.bds.repository.RoleRepository;
import com.example.bds.repository.UserRepository;
import com.example.bds.user.CustomUserDetails;
import com.example.bds.utils.DefaultUtils;
import com.example.bds.utils.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        UserEntity user = userRepository.findByUserName(request.getUsername()).orElseThrow(() -> new NotFoundException(SystemConstant.ERROR_MESSAGE));
        String token = jwtTokenProvider.generateToken(new CustomUserDetails(user));
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        if(userRepository.existsByUserName(request.getUsername())){
            throw new CustomRuntimeException(SystemConstant.ERROR_USERNAME_EXIST);
        }
        List<RoleEntity> roleEntityList = Arrays.asList(DefaultUtils.getRoleDefault());
        if(request.getRoles() != null){
            roleEntityList = request.getRoles().stream().map(item -> {
                RoleEntity roleEntity = roleRepository.findByCode(item).orElseThrow(() -> new NotFoundException(SystemConstant.ROLE_NOT_FOUND));
                return roleEntity;
            }).collect(Collectors.toList());
        }
        UserEntity user =new UserEntity();
            user.setUserName(request.getUsername());
            user.setEmail(!StringUtils.isNullOrEmpty(request.getEmail()) ? request.getEmail():"");
            user.setRoles(roleEntityList);
            user.setFullName(!StringUtils.isNullOrEmpty(request.getFullname()) ? request.getFullname():"");
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        String jwtToken = jwtTokenProvider.generateToken(new CustomUserDetails(user));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

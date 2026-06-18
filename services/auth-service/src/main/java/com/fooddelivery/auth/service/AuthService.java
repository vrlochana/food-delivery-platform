package com.fooddelivery.auth.service;

import com.fooddelivery.auth.dto.AuthResponse;
import com.fooddelivery.auth.dto.LoginRequest;
import com.fooddelivery.auth.dto.RegisterRequest;
import com.fooddelivery.auth.entity.User;
import com.fooddelivery.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fooddelivery.auth.client.UserServiceClient;
import com.fooddelivery.auth.dto.UserProfileRequest;
import com.fooddelivery.auth.exception.UserAlreadyExistsException;
import com.fooddelivery.auth.exception.InvalidCredentialsException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserServiceClient userServiceClient;

    /*public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }*/

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       UserServiceClient userServiceClient) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userServiceClient = userServiceClient;
    }

   /* public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }*/

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists with email: " + request.getEmail());
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);
        userServiceClient.createProfile(
                new UserProfileRequest(user.getFullName(), user.getEmail())
        );
        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token);
    }
}
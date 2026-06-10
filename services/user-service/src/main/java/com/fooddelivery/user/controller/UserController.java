package com.fooddelivery.user.controller;

import com.fooddelivery.user.dto.CreateUserProfileRequest;
import com.fooddelivery.user.dto.UserProfileResponse;
import com.fooddelivery.user.service.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserProfileService userProfileService;

    public UserController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/health")
    public String health() {
        return "User Service Running";
    }



    @PostMapping("/profiles")
    public UserProfileResponse createProfile(@Valid @RequestBody CreateUserProfileRequest request) {
        return userProfileService.createProfile(request);
    }

    @GetMapping("/profiles/{id}")
    public UserProfileResponse getProfile(@PathVariable Long id) {
        return userProfileService.getProfile(id);
    }

    @GetMapping("/profiles/email/{email}")
    public UserProfileResponse getProfileByEmail(@PathVariable String email) {
        return userProfileService.getProfileByEmail(email);
    }
}
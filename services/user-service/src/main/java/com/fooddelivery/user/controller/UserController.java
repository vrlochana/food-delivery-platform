package com.fooddelivery.user.controller;

import com.fooddelivery.user.dto.ApiResponse;
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
    public ApiResponse<String> health() {
        return new ApiResponse<>(
                true,
                "User service is running",
                "User Service Running"
        );
    }

    @PostMapping("/profiles")
    public ApiResponse<UserProfileResponse> createProfile(
            @Valid @RequestBody CreateUserProfileRequest request
    ) {
        return new ApiResponse<>(
                true,
                "User profile created successfully",
                userProfileService.createProfile(request)
        );
    }

    @GetMapping("/profiles/{id}")
    public ApiResponse<UserProfileResponse> getProfile(@PathVariable Long id) {
        return new ApiResponse<>(
                true,
                "User profile fetched successfully",
                userProfileService.getProfile(id)
        );
    }

    @GetMapping("/profiles/email/{email}")
    public ApiResponse<UserProfileResponse> getProfileByEmail(@PathVariable String email) {
        return new ApiResponse<>(
                true,
                "User profile fetched successfully",
                userProfileService.getProfileByEmail(email)
        );
    }
}
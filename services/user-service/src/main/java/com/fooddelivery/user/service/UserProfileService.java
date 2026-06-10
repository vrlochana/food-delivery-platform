package com.fooddelivery.user.service;

import com.fooddelivery.user.dto.CreateUserProfileRequest;
import com.fooddelivery.user.dto.UserProfileResponse;
import com.fooddelivery.user.entity.UserProfile;
import com.fooddelivery.user.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfileResponse createProfile(CreateUserProfileRequest request) {
        if (userProfileRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User profile already exists");
        }

        UserProfile profile = new UserProfile();
        profile.setFullName(request.getFullName());
        profile.setEmail(request.getEmail());
        profile.setAddress(request.getAddress());
        profile.setPhone(request.getPhone());

        UserProfile savedProfile = userProfileRepository.save(profile);

        return new UserProfileResponse(
                savedProfile.getId(),
                savedProfile.getFullName(),
                savedProfile.getEmail(),
                savedProfile.getAddress(),
                savedProfile.getPhone()
        );
    }

    public UserProfileResponse getProfile(Long id) {

        UserProfile profile = userProfileRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User profile not found"));

        return new UserProfileResponse(
                profile.getId(),
                profile.getFullName(),
                profile.getEmail(),
                profile.getAddress(),
                profile.getPhone()
        );
    }

    public UserProfileResponse getProfileByEmail(String email) {

        UserProfile profile = userProfileRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User profile not found"));

        return new UserProfileResponse(
                profile.getId(),
                profile.getFullName(),
                profile.getEmail(),
                profile.getAddress(),
                profile.getPhone()
        );
    }



}
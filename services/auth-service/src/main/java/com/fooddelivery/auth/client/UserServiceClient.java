package com.fooddelivery.auth.client;

import com.fooddelivery.auth.dto.UserProfileRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "user-service",
        url = "http://localhost:8082"
)
public interface UserServiceClient {

    @PostMapping("/api/v1/users/profiles")
    Object createProfile(
            @RequestBody UserProfileRequest request
    );

}
package com.fooddelivery.auth.dto;

public class UserProfileRequest {

    private String fullName;
    private String email;

    public UserProfileRequest() {
    }

    public UserProfileRequest(
            String fullName,
            String email
    ) {
        this.fullName = fullName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
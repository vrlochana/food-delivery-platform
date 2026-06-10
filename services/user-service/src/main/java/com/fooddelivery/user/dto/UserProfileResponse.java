package com.fooddelivery.user.dto;

public class UserProfileResponse {

    private Long id;
    private String fullName;
    private String email;
    private String address;
    private String phone;

    public UserProfileResponse(Long id, String fullName, String email, String address, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
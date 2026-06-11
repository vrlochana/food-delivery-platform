package com.fooddelivery.restaurant.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateRestaurantRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String cuisine;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
}
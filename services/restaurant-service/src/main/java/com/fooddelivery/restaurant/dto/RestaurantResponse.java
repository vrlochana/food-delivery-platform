package com.fooddelivery.restaurant.dto;

public class RestaurantResponse {

    private Long id;
    private String name;
    private String address;
    private String cuisine;
    private Boolean active;

    public RestaurantResponse(Long id, String name, String address, String cuisine, Boolean active) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cuisine = cuisine;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCuisine() {
        return cuisine;
    }

    public Boolean getActive() {
        return active;
    }
}
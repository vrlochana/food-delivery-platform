package com.fooddelivery.restaurant.dto;

public class CategoryResponse {

    private Long id;
    private String name;
    private Boolean active;
    private Long restaurantId;

    public CategoryResponse(Long id, String name, Boolean active, Long restaurantId) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.restaurantId = restaurantId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return active;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }
}
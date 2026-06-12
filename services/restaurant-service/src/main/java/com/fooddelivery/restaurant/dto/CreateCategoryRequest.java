package com.fooddelivery.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateCategoryRequest {

    @NotBlank(message = "Category name is required")
    private String name;

    @NotNull(message = "Restaurant id is required")
    private Long restaurantId;

    public String getName() {
        return name;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
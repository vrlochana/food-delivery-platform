package com.fooddelivery.order.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateOrderRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long restaurantId;

    private List<CreateOrderItemRequest> items;

    public Long getUserId() {
        return userId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public List<CreateOrderItemRequest> getItems() {
        return items;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setItems(List<CreateOrderItemRequest> items) {
        this.items = items;
    }
}

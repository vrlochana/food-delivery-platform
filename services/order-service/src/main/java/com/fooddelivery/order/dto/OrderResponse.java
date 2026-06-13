package com.fooddelivery.order.dto;

import com.fooddelivery.order.enums.OrderStatus;

import java.util.List;

public class OrderResponse {

    private Long id;
    private Long userId;
    private Long restaurantId;
    private OrderStatus status;
    private List<OrderItemResponse> items;

    public OrderResponse(Long id,
                         Long userId,
                         Long restaurantId,
                         OrderStatus status,
                         List<OrderItemResponse> items) {
        this.id = id;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.status = status;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }
}
package com.fooddelivery.order.dto;

public class PaymentResponse {

    private Long id;
    private Long orderId;
    private Double amount;
    private String status;

    public PaymentResponse() {
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
}
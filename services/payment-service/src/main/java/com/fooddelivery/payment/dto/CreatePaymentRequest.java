package com.fooddelivery.payment.dto;

import jakarta.validation.constraints.NotNull;

public class CreatePaymentRequest {

    @NotNull
    private Long orderId;

    @NotNull
    private Double amount;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
package com.fooddelivery.order.client;

import org.springframework.stereotype.Component;

@Component
public class PaymentClientFallback implements PaymentClient {
    @Override
    public String checkPayment() {
        return "Payment Service is currently unavailable. Please try again later.";
    }
}

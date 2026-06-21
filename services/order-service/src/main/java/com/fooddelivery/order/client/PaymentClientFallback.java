package com.fooddelivery.order.client;

import com.fooddelivery.order.dto.PaymentRequest;
import com.fooddelivery.order.dto.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class PaymentClientFallback implements PaymentClient {

    @Override
    public String checkPayment() {
        return "Payment Service is currently unavailable. Please try again later.";
    }

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        return null;
    }
}
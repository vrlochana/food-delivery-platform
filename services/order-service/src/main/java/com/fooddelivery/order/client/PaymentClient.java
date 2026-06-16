package com.fooddelivery.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "PAYMENT-SERVICE",
        fallback = PaymentClientFallback.class
)

public interface PaymentClient {

    @GetMapping("/api/v1/payments/health")
    String checkPayment();
}
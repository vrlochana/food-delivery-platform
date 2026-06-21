package com.fooddelivery.order.client;

import com.fooddelivery.order.dto.PaymentRequest;
import com.fooddelivery.order.dto.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "PAYMENT-SERVICE"
        //fallback = PaymentClientFallback.class
)
public interface PaymentClient {

    @GetMapping("/api/v1/payments/health")
    String checkPayment();

    @PostMapping("/api/v1/payments/process")
    PaymentResponse processPayment(@RequestBody PaymentRequest request);
}
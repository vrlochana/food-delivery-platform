package com.fooddelivery.payment.client;

import com.fooddelivery.payment.enums.PaymentStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/*@FeignClient(
        name = "order-service",
        url = "http://localhost:8084"
)*/
@FeignClient(name = "ORDER-SERVICE")
public interface OrderClient {

    @PutMapping("/api/v1/orders/{id}/status")
    void updateOrderStatus(
            @PathVariable("id") Long orderId,
            @RequestParam("status") String status
    );
}
package com.fooddelivery.order.controller;

import com.fooddelivery.order.client.PaymentClient;
import com.fooddelivery.order.dto.ApiResponse;
import com.fooddelivery.order.dto.CreateOrderRequest;
import com.fooddelivery.order.dto.OrderResponse;
import com.fooddelivery.order.enums.OrderStatus;
import com.fooddelivery.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final PaymentClient paymentClient;

    public OrderController(
            OrderService orderService,
            PaymentClient paymentClient
    ) {
        this.orderService = orderService;
        this.paymentClient = paymentClient;
    }

    @GetMapping("/health")
    public ApiResponse<Map<String, String>> health(
            @RequestHeader(value = "X-User-Email", required = false) String email,
            @RequestHeader(value = "X-User-Role", required = false) String role
    ) {
        return new ApiResponse<>(
                true,
                "Order service is running",
                Map.of(
                        "service", "order-service",
                        "userEmail", String.valueOf(email),
                        "userRole", String.valueOf(role)
                )
        );
    }

    @GetMapping("/admin/test")
    public ApiResponse<Map<String, String>> adminTest(
            @RequestHeader(value = "X-User-Email", required = false) String email,
            @RequestHeader(value = "X-User-Role", required = false) String role
    ) {
        return new ApiResponse<>(
                true,
                "Admin endpoint accessed successfully",
                Map.of(
                        "service", "order-service",
                        "endpoint", "admin-test",
                        "userEmail", String.valueOf(email),
                        "userRole", String.valueOf(role)
                )
        );
    }

    @PostMapping
    public ApiResponse<OrderResponse> createOrder(
            @Valid @RequestBody CreateOrderRequest request
    ) {
        return new ApiResponse<>(
                true,
                "Order created successfully",
                orderService.createOrder(request)
        );
    }

    @GetMapping("/test-payment")
    public ApiResponse<String> testPaymentService() {
        return new ApiResponse<>(
                true,
                "Payment service response received",
                paymentClient.checkPayment()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<OrderResponse> getOrder(@PathVariable Long id) {
        return new ApiResponse<>(
                true,
                "Order fetched successfully",
                orderService.getOrder(id)
        );
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<OrderResponse>> getOrdersByUser(@PathVariable Long userId) {
        return new ApiResponse<>(
                true,
                "Orders fetched successfully",
                orderService.getOrdersByUser(userId)
        );
    }

    @GetMapping
    public ApiResponse<List<OrderResponse>> getAllOrders() {
        return new ApiResponse<>(
                true,
                "Orders fetched successfully",
                orderService.getAllOrders()
        );
    }

    @PutMapping("/{id}/status")
    public ApiResponse<OrderResponse> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status
    ) {
        return new ApiResponse<>(
                true,
                "Order status updated successfully",
                orderService.updateOrderStatus(id, status)
        );
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ApiResponse<List<OrderResponse>> getOrdersByRestaurant(
            @PathVariable Long restaurantId
    ) {
        return new ApiResponse<>(
                true,
                "Restaurant orders fetched successfully",
                orderService.getOrdersByRestaurant(restaurantId)
        );
    }
}
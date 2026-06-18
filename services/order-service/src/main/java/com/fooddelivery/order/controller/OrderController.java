package com.fooddelivery.order.controller;

import com.fooddelivery.order.client.PaymentClient;
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
            PaymentClient paymentClient) {
        this.orderService = orderService;
        this.paymentClient = paymentClient;
    }

    @GetMapping("/health")
    public Map<String, String> health(
            @RequestHeader(value = "X-User-Email", required = false) String email,
            @RequestHeader(value = "X-User-Role", required = false) String role
    ) {

        return Map.of(
                "service", "order-service",
                "userEmail", String.valueOf(email),
                "userRole", String.valueOf(role)
        );
    }

    @GetMapping("/admin/test")
    public Map<String, String> adminTest(
            @RequestHeader(value = "X-User-Email", required = false) String email,
            @RequestHeader(value = "X-User-Role", required = false) String role
    ) {
        return Map.of(
                "service", "order-service",
                "endpoint", "admin-test",
                "userEmail", String.valueOf(email),
                "userRole", String.valueOf(role)
        );
    }

    @PostMapping
    public OrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping("/test-payment")
    public String testPaymentService() {
        return paymentClient.checkPayment();
    }

    @GetMapping("/{id}")
    public OrderResponse getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

   @GetMapping("/user/{userId}")
    public List<OrderResponse> getOrdersByUser(@PathVariable Long userId) {
        return orderService.getOrdersByUser(userId);
    }

    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{id}/status")
    public OrderResponse updateOrderStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status
    ) {
        return orderService.updateOrderStatus(id, status);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<OrderResponse> getOrdersByRestaurant(@PathVariable Long restaurantId) {
        return orderService.getOrdersByRestaurant(restaurantId);
    }
}
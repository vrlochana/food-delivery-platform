package com.fooddelivery.order.service;

import com.fooddelivery.order.client.PaymentClient;
import com.fooddelivery.order.dto.CreateOrderItemRequest;
import com.fooddelivery.order.dto.CreateOrderRequest;
import com.fooddelivery.order.dto.OrderItemResponse;
import com.fooddelivery.order.dto.OrderResponse;
import com.fooddelivery.order.dto.PaymentRequest;
import com.fooddelivery.order.dto.PaymentResponse;
import com.fooddelivery.order.entity.Order;
import com.fooddelivery.order.entity.OrderItem;
import com.fooddelivery.order.enums.OrderStatus;
import com.fooddelivery.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
/*import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;*/

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;

    public OrderService(
            OrderRepository orderRepository,
            PaymentClient paymentClient
    ) {
        this.orderRepository = orderRepository;
        this.paymentClient = paymentClient;


    }

    public OrderResponse createOrder(CreateOrderRequest request) {

        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setRestaurantId(request.getRestaurantId());
        order.setStatus(OrderStatus.CREATED);

        for (CreateOrderItemRequest itemRequest : request.getItems()) {
            OrderItem item = new OrderItem();
            item.setProductId(itemRequest.getProductId());
            item.setProductName("Product-" + itemRequest.getProductId());
            item.setPrice(100.0);
            item.setQuantity(itemRequest.getQuantity());
            item.setOrder(order);

            order.getItems().add(item);
        }

        Order saved = orderRepository.save(order);

        PaymentRequest paymentRequest =
                new PaymentRequest(
                        saved.getId(),
                        calculateTotalAmount(saved),
                        "UPI"
                );


        PaymentResponse paymentResponse = null;

        try {
            paymentResponse = paymentClient.processPayment(paymentRequest);
        } catch (Exception ex) {
            System.out.println("Payment service failed: " + ex.getMessage());
        }

        if (paymentResponse != null && "SUCCESS".equals(paymentResponse.getStatus())) {
            saved.setStatus(OrderStatus.CONFIRMED);
        } else {
            saved.setStatus(OrderStatus.CANCELLED);
        }
        saved = orderRepository.save(saved);

        return mapToResponse(saved);
    }

    public OrderResponse getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return mapToResponse(order);
    }

    public List<OrderResponse> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public OrderResponse updateOrderStatus(Long id, OrderStatus status) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);

        Order saved = orderRepository.save(order);

        return mapToResponse(saved);
    }

    public List<OrderResponse> getAllOrders() {

        return orderRepository
                .findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<OrderResponse> getOrdersByRestaurant(Long restaurantId) {
        return orderRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    /*@CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    @Retry(name = "paymentService")
    public PaymentResponse callPaymentService(PaymentRequest paymentRequest) {
        return paymentClient.processPayment(paymentRequest);
    }

    public PaymentResponse paymentFallback(PaymentRequest paymentRequest, Throwable throwable) {
        System.out.println("Payment service failed: " + throwable.getMessage());
        return null;
    }*/

    private Double calculateTotalAmount(Order order) {
        return order.getItems()
                .stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    private OrderResponse mapToResponse(Order order) {
        List<OrderItemResponse> items = order.getItems()
                .stream()
                .map(item -> new OrderItemResponse(
                        item.getProductId(),
                        item.getProductName(),
                        item.getPrice(),
                        item.getQuantity()
                ))
                .toList();

        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getRestaurantId(),
                order.getStatus(),
                items
        );
    }
}
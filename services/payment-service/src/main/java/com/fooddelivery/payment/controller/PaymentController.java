package com.fooddelivery.payment.controller;

import com.fooddelivery.payment.dto.CreatePaymentRequest;
import com.fooddelivery.payment.dto.PaymentRequest;
import com.fooddelivery.payment.dto.PaymentResponse;
import com.fooddelivery.payment.enums.PaymentStatus;
import com.fooddelivery.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/health")
    public String health() {
        return "Payment Service Running";
    }

    @PostMapping("/process")
    public PaymentResponse processPayment(
            @Valid @RequestBody PaymentRequest request
    ) {
        return paymentService.processPayment(request);
    }

    @PostMapping
    public PaymentResponse createPayment(@Valid @RequestBody CreatePaymentRequest request) {
        return paymentService.createPayment(request);
    }

   /* @GetMapping("/{id}")
    public PaymentResponse getPayment(@PathVariable Long id) {
        return paymentService.getPayment(id);
    }*/

    @GetMapping("/{orderId}")
    public PaymentResponse getPayment(
            @PathVariable Long orderId
    ) {
        return paymentService.getPayment(orderId);
    }

    @GetMapping("/order/{orderId}")
    public PaymentResponse getPaymentByOrderId(@PathVariable Long orderId) {
        return paymentService.getPaymentByOrderId(orderId);
    }

    @PutMapping("/{id}/status")
    public PaymentResponse updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam PaymentStatus status
    ) {
        return paymentService.updatePaymentStatus(id, status);
    }



    @GetMapping
    public List<PaymentResponse> getAllPayments() {
        return paymentService.getAllPayments();
    }


}
package com.fooddelivery.payment.service;

import com.fooddelivery.payment.client.OrderClient;
import com.fooddelivery.payment.dto.CreatePaymentRequest;
import com.fooddelivery.payment.dto.PaymentResponse;
import com.fooddelivery.payment.entity.Payment;
import com.fooddelivery.payment.enums.PaymentStatus;
import com.fooddelivery.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;

    public PaymentService(
            PaymentRepository paymentRepository,
            OrderClient orderClient
    ) {
        this.paymentRepository = paymentRepository;
        this.orderClient = orderClient;
    }

    public PaymentResponse createPayment(CreatePaymentRequest request) {

        Payment payment = new Payment();
        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        payment.setStatus(PaymentStatus.PENDING);

        Payment saved = paymentRepository.save(payment);

        return mapToResponse(saved);
    }

    public PaymentResponse getPayment(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return mapToResponse(payment);
    }

    public PaymentResponse getPaymentByOrderId(Long orderId) {

        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for order"));

        return mapToResponse(payment);
    }

    public PaymentResponse updatePaymentStatus(Long id, PaymentStatus status) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus(status);

        Payment saved = paymentRepository.save(payment);

        if (status == PaymentStatus.SUCCESS) {
            orderClient.updateOrderStatus(
                    saved.getOrderId(),
                    "CONFIRMED"
            );
        }

        return mapToResponse(saved);
    }


    public List<PaymentResponse> getAllPayments() {

        return paymentRepository
                .findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private PaymentResponse mapToResponse(Payment payment) {

        PaymentResponse response = new PaymentResponse();
        response.setId(payment.getId());
        response.setOrderId(payment.getOrderId());
        response.setAmount(payment.getAmount());
        response.setStatus(payment.getStatus());

        return response;
    }
}
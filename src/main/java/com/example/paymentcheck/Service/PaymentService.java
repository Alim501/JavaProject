package com.example.paymentcheck.Service;

import com.example.paymentcheck.Entity.Payment;
import com.example.paymentcheck.Repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public Payment createPayment(Payment payment) {
        payment.setCreatedAt(LocalDateTime.now());
        payment.setStatus(Payment.Status.PENDING);
        return paymentRepository.save(payment);
    }

    public Payment updatePaymentStatus(Long id, Payment.Status status) {
        Payment payment = getPaymentById(id);
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    public Payment checkPaymentStatus(Long id, Boolean received) {
        Payment payment = getPaymentById(id);
        if (received) {
            payment.setStatus(Payment.Status.COMPLETED);
        } else {
            payment.setStatus(Payment.Status.FAILED);
        }
        return paymentRepository.save(payment);
    }
}


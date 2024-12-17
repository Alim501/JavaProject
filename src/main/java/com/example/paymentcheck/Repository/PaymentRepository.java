package com.example.paymentcheck.Repository;


import com.example.paymentcheck.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}


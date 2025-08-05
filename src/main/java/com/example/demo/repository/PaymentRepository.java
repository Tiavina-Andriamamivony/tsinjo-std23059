package com.example.demo.repository;

import com.example.demo.model.VerificationStatus;
import com.example.demo.model.psp.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
  void save(Payment payment);

  Optional<Payment> findById(String id);

  List<Payment> findAll();

  void updateVerificationStatus(String paymentId, VerificationStatus status);
}

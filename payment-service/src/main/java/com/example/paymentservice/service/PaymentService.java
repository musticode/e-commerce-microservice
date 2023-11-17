package com.example.paymentservice.service;

import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.model.Payment;

import java.util.List;

public interface PaymentService {
    Payment findPayment(long paymentId);

    List<Payment> getAllPayments();
}

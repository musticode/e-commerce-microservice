package com.example.paymentservice.service;

import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.model.Payment;

public interface PaymentService {
    Payment findPayment(long paymentId);

}

package com.example.paymentservice.controller;


import com.example.paymentservice.constant.PaymentConstant;
import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.model.Payment;
import com.example.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PaymentConstant.PAYMENT_ENDPOINT)
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping(PaymentConstant.PAYMENT_ID)
    public ResponseEntity<Payment> findPaymentWithId(@PathVariable long paymentId){
        return new ResponseEntity<>(paymentService.findPayment(paymentId), HttpStatus.OK);
    }


}

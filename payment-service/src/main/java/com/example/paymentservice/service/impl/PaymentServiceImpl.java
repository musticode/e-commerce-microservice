package com.example.paymentservice.service.impl;

import com.example.paymentservice.dto.PaymentDto;
import com.example.ordermanagement.event.OrderEvent;
import com.example.paymentservice.external.ProductClient;
import com.example.paymentservice.external.UserClient;
import com.example.paymentservice.model.Payment;
import com.example.paymentservice.model.external.Product;
import com.example.paymentservice.repository.PaymentRepository;
import com.example.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserClient userClient;
    private final ProductClient productClient;
    private final ModelMapper modelMapper;

    @Override
    public Payment findPayment(long paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("No payment with id " + paymentId));
    }


    @KafkaListener(
            topics = "order_topic",
            groupId = "order"
    )
    public void consume(OrderEvent orderEvent){
        System.out.println(orderEvent.getMessage());

    }


    @Override
    public Payment createPayment(PaymentDto request) {
        final Product product = productClient.findProductById(request.getProductId()).getBody();
        final long productQuantity = product.getQuantity();

        if (productQuantity <= 0){
            throw new RuntimeException("No available products");
        }

        Payment payment = Payment.builder()
                .createdAt(new Date())
                .productId(request.getProductId())
                .sellerId(request.getSellerId())
                .buyerId(request.getBuyerId())
                .build();

        paymentRepository.save(payment);

        log.info("Product : {}", product);
        log.info("productQuantity : {}", product.getQuantity());
        log.info("Payment : {}", payment);

        return payment;
    }


}

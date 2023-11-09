package com.example.paymentservice.service.impl;

import com.example.paymentservice.dto.PaymentDto;
import com.example.ordermanagement.event.OrderEvent;
import com.example.paymentservice.dto.user.User;
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
import java.util.List;
import java.util.stream.Collectors;

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
    public void placeOrder(OrderEvent orderEvent){
        System.out.println(orderEvent.getMessage());
        log.info("Order: {}", orderEvent);

        final String sellerCreditCard = getUserCreditCardDetails(orderEvent.getOrder().getSellerId());
        final String buyerCreditCard  = getUserCreditCardDetails(orderEvent.getOrder().getBuyerId());

        Payment payment = new Payment();
        payment.setSellerId(orderEvent.getOrder().getSellerId());
        payment.setBuyerId(orderEvent.getOrder().getBuyerId());
        payment.setProductId(orderEvent.getOrder().getId());
        payment.setBuyerCreditCardNumber(buyerCreditCard);
        payment.setSellerCreditCardNumber(sellerCreditCard);

        addPayment(payment);
    }


    public Payment addPayment(Payment request){

        Payment payment = new Payment();
        payment.setSellerId(request.getSellerId());
        payment.setBuyerId(request.getBuyerId());
        payment.setCreatedAt(new Date());
        payment.setBuyerCreditCardNumber(request.getBuyerCreditCardNumber());
        payment.setSellerCreditCardNumber(request.getSellerCreditCardNumber());
        return paymentRepository.save(payment);
    }


    @Override
    public Payment createPayment(PaymentDto request) {
        final Product product = productClient.findProductById(request.getProductId()).getBody();
        final long productQuantity = product.getQuantity();

        if (productQuantity <= 0){
            throw new RuntimeException("No available products");
        }

        Payment payment = new Payment();
        payment.setId(request.getProductId());

        paymentRepository.save(payment);

        log.info("Product : {}", product);
        log.info("productQuantity : {}", product.getQuantity());
        log.info("Payment : {}", payment);

        return payment;
    }

    private String getUserCreditCardDetails(final long userId){
        User user = userClient.findUserDetailsById(userId).getBody();
        return user.getCreditCardId();
    }

    private long getProductQuantity(final long productId){
        Product product = productClient.findProductById(productId).getBody();
        return product.getQuantity();
    }


}

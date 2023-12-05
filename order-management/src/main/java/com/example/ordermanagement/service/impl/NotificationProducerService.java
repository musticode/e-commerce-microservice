package com.example.ordermanagement.service.impl;


import com.example.ordermanagement.event.OrderEvent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationProducerService {
    private final NewTopic notification;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendMessage(@NonNull OrderEvent event){

        // create message
        Message<OrderEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, notification.name())
                .build();

        kafkaTemplate.send(message);
    }


}

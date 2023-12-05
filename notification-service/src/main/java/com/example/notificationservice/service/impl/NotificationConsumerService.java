package com.example.notificationservice.service.impl;

import com.example.notificationservice.event.NotificationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumerService {

    private final static String NOTIFICATION_TOPIC = "notification_topic";
    private final static String NOTIFICATION_TOPIC_GROUP_ID = "notification_topic";

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(NotificationEvent notificationEvent){
        System.out.println("Test");
    }



}

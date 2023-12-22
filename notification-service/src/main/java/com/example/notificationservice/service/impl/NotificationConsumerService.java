package com.example.notificationservice.service.impl;

import com.example.notificationservice.event.NotificationEvent;
import com.example.notificationservice.event.NotificationType;
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

    private final MailSenderServiceImpl mailSenderService;

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(NotificationEvent notificationEvent){

        log.info("Notification event : {}", notificationEvent);

        final String receiver = String.valueOf(notificationEvent.getUserId());

        if (notificationEvent.getNotificationType().equals(NotificationType.ORDER_CONFIRMATION)){
            mailSenderService.sendMail("System", receiver, notificationEvent.getMessage());
        }


    }



}

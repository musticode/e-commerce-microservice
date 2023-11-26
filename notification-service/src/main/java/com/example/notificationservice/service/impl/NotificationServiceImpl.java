package com.example.notificationservice.service.impl;

import com.example.notificationservice.model.Notification;
import com.example.notificationservice.repository.NotificationRepository;
import com.example.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification getNotificationWithId(long notificationId) {
        return notificationRepository.findById(notificationId).orElseThrow(()-> new RuntimeException("Notification not found"));
    }


}

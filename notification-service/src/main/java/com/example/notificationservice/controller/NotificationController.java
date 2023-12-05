package com.example.notificationservice.controller;

import com.example.notificationservice.model.Notification;
import com.example.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{notificationId}")
    public ResponseEntity<Notification> findNotificationWithId(@PathVariable long notificationId){
        return new ResponseEntity<>(notificationService.getNotificationWithId(notificationId), HttpStatus.OK);
    }






}

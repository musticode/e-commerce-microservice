package com.example.notificationservice.service.impl;

import com.example.notificationservice.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {

    public void sendMail(String from, String to, String message){
        log.info("Sending mail from {} to {} message : {}", from, to, message);
    }

}

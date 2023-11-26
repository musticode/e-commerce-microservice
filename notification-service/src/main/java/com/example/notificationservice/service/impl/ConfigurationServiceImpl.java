package com.example.notificationservice.service.impl;

import com.example.notificationservice.model.Configuration;
import com.example.notificationservice.repository.ConfigurationRepository;
import com.example.notificationservice.service.ConfigurationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConfigurationServiceImpl implements ConfigurationService {

    private final ConfigurationRepository configurationRepository;

    public Configuration createConfiguration(Configuration configuration){

        return null;
    }


}

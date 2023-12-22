package com.example.logisticservice.service;

import com.example.logisticservice.dto.transport.TransportDto;

public interface TransportService {
    TransportDto findTransportDetailWithId(long transportId);
}

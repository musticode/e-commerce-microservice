package com.example.logisticservice.service;

import com.example.logisticservice.dto.transport.TransportDto;
import com.example.logisticservice.dto.transport.TransportRequest;

public interface TransportService {
    TransportDto findTransportDetailWithId(long transportId);
    TransportDto addTransport(TransportRequest request);
}

package com.example.logisticservice.service.impl;

import com.example.logisticservice.dto.transport.TransportDto;
import com.example.logisticservice.model.Transport;
import com.example.logisticservice.repository.TransportRepository;
import com.example.logisticservice.service.TransportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepository;
    private final ModelMapper modelMapper;


    @Override
    public TransportDto findTransportDetailWithId(long transportId) {
        Transport transport = transportRepository
                .findById(transportId)
                .orElseThrow(()-> new RuntimeException("No transport with id "+ transportId));
        return mapToDto(transport);
    }

    private TransportDto mapToDto(Transport transport){
        return modelMapper.map(transport, TransportDto.class);
    }

}

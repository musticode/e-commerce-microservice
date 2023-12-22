package com.example.logisticservice.service.impl;

import com.example.logisticservice.dto.transport.TransportDto;
import com.example.logisticservice.dto.transport.TransportRequest;
import com.example.logisticservice.model.Transport;
import com.example.logisticservice.model.TransportInsurance;
import com.example.logisticservice.repository.TransportRepository;
import com.example.logisticservice.service.InsuranceService;
import com.example.logisticservice.service.TransportService;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepository;
    private final InsuranceService insuranceService;
    private final ModelMapper modelMapper;


    @Override
    public TransportDto findTransportDetailWithId(long transportId) {
        Transport transport = transportRepository
                .findById(transportId)
                .orElseThrow(()-> new RuntimeException("No transport with id "+ transportId));
        return mapToDto(transport);
    }

    public TransportDto addTransport(TransportRequest request){

        // find insurance
        final long insuranceId = 1L;
        TransportInsurance transportInsurance = insuranceService.findInsurance(insuranceId);


        Transport transport = new Transport();
        transport.setRegistrationNumber(request.getRegistrationNumber());
        transport.setVin(request.getVin());


        return mapToDto(transport);
    }


    private Transport saveTransport(@NonNull Transport transport){
        return transportRepository.save(transport);
    }

    private TransportDto mapToDto(Transport transport){
        return modelMapper.map(transport, TransportDto.class);
    }

}

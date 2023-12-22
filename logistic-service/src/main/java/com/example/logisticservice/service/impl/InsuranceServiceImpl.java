package com.example.logisticservice.service.impl;

import com.example.logisticservice.dto.insurance.TransportInsuranceDto;
import com.example.logisticservice.model.TransportInsurance;
import com.example.logisticservice.repository.TransportInsuranceRepository;
import com.example.logisticservice.service.InsuranceService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class InsuranceServiceImpl implements InsuranceService {

    private final TransportInsuranceRepository transportInsuranceRepository;
    private final ModelMapper modelMapper;

    @Override
    public TransportInsurance findInsurance(long insuranceId) {
        return transportInsuranceRepository.findById(insuranceId).orElseThrow(()-> new RuntimeException("No insurance with id"+ insuranceId));
    }



    private TransportInsuranceDto mapToDto(@NonNull TransportInsurance transportInsurance){
        return modelMapper.map(transportInsurance, TransportInsuranceDto.class);
    }




}

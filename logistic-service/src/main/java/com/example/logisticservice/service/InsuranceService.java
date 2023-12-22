package com.example.logisticservice.service;

import com.example.logisticservice.model.TransportInsurance;

public interface InsuranceService {
    TransportInsurance findInsurance(long insuranceId);
}

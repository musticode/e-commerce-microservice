package com.example.logisticservice.dto.transport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransportDto {
    private int id;
    private String registrationNumber;
    private String vin;
}

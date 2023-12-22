package com.example.logisticservice.dto.insurance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransportInsuranceDto {
    private int id;
    private String number;
    private Date validFrom;
    private Date validTo;
}

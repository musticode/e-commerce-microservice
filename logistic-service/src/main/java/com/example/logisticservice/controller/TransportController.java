package com.example.logisticservice.controller;

import com.example.logisticservice.dto.transport.TransportDto;
import com.example.logisticservice.service.TransportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.logisticservice.constant.TransportConstant.TRANSPORT_BY_ID;
import static com.example.logisticservice.constant.TransportConstant.TRANSPORT_ENDPOINT;

@RestController
@RequestMapping(TRANSPORT_ENDPOINT)
@RequiredArgsConstructor
public class TransportController {

    private final TransportService transportService;

    @GetMapping(TRANSPORT_BY_ID)
    public ResponseEntity<TransportDto> findTransportDetailById(@PathVariable long transportId){
        return new ResponseEntity<>(transportService.findTransportDetailWithId(transportId), HttpStatus.OK);
    }


}

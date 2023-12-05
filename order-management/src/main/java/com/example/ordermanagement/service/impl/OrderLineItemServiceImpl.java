package com.example.ordermanagement.service.impl;

import com.example.ordermanagement.model.OrderLineItem;
import com.example.ordermanagement.repository.OrderLineItemRepository;
import com.example.ordermanagement.service.OrderLineItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderLineItemServiceImpl implements OrderLineItemService {

    private final OrderLineItemRepository orderLineItemRepository;
    private final ModelMapper modelMapper;

    @Override
    public OrderLineItem saveOrderLineItem(OrderLineItem orderLineItem){

        if (orderLineItem == null){
            log.info("Order line item object is null");
            return null;
        }

        log.info("Saved: {}", orderLineItem);
        return orderLineItemRepository.save(orderLineItem);
    }

    @Override
    public OrderLineItem findOrderLineItemById(long orderLineItemId){
        return orderLineItemRepository.findById(orderLineItemId).orElseThrow(()-> new RuntimeException("No order line item"));
    }


}

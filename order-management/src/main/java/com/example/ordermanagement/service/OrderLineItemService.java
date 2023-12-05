package com.example.ordermanagement.service;

import com.example.ordermanagement.model.OrderLineItem;

public interface OrderLineItemService {
    OrderLineItem saveOrderLineItem(OrderLineItem orderLineItem);
    OrderLineItem findOrderLineItemById(long orderLineItemId);
}

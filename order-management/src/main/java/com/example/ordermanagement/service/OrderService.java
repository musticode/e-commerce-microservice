package com.example.ordermanagement.service;

import com.example.ordermanagement.dto.OrderDto;
import com.example.ordermanagement.model.Order;

public interface OrderService{
    Order findOrderById(long orderId);

    OrderDto saveOrder(OrderDto order);
    OrderDto cancelOrder(long orderId);

    OrderDto updateOrder(long orderId, OrderDto orderDto);
}

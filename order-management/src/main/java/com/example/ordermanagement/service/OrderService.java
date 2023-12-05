package com.example.ordermanagement.service;

import com.example.ordermanagement.dto.order.ConfirmOrderRequest;
import com.example.ordermanagement.dto.order.OrderDto;
import com.example.ordermanagement.dto.order.OrderResponse;
import com.example.ordermanagement.model.Order;

import java.util.List;

/**
 * Handles tasks like order creation, order confirmation, order cancellation, and order history.
 * */

public interface OrderService{

    OrderResponse findOrderWithId(long orderId);


    Order findOrderById(long orderId);

    OrderDto saveOrder(OrderDto order);
    OrderDto cancelOrder(long orderId);

    OrderDto updateOrder(long orderId, OrderDto orderDto);

    List<Order> getUserOrderHistory(long userId);
    Order confirmOrder(Order order);

    OrderDto confirmCartOrder(ConfirmOrderRequest confirmOrderRequest);
}

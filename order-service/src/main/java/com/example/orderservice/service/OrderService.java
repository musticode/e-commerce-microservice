package com.example.orderservice.service;

import com.example.orderservice.dto.order.OrderResponse;
import com.example.orderservice.dto.order.OrderStatusRequest;
import com.example.orderservice.dto.order.OrderStatusResponse;
import com.example.orderservice.dto.order.PlaceOrderRequest;

public interface OrderService {
    OrderResponse findOrderById(long orderId);

    OrderResponse placeOrder(PlaceOrderRequest placeOrderRequest);

    OrderStatusResponse setOrderStatus(long orderId, OrderStatusRequest orderStatusRequest);

    OrderStatusResponse getOrderStatus(long orderId);
}

package com.example.ordermanagement.controller;

import com.example.ordermanagement.constant.OrderConstant;
import com.example.ordermanagement.dto.OrderDto;
import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.service.OrderService;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(OrderConstant.ORDER_ENDPOINT)
public class OrderController {

    private final OrderService orderService;


    @GetMapping(OrderConstant.ORDER_ID)
    public ResponseEntity<Order> findOrderWithId(@PathVariable long orderId){
        Order order = orderService.findOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/proceed-to-checkout")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto order){
        return new ResponseEntity<>(orderService.saveOrder(order), HttpStatus.CREATED);
    }

    @PostMapping("/update-order-status/{orderId}")
    public ResponseEntity<OrderDto> updateOrderStatus(@RequestBody OrderDto orderDto, @PathVariable long orderId){
        return new ResponseEntity<>(orderService.updateOrder(orderId, orderDto), HttpStatus.OK);
    }

}

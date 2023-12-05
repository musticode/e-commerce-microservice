package com.example.ordermanagement.controller;

import com.example.ordermanagement.constant.OrderConstant;
import com.example.ordermanagement.dto.order.ConfirmOrderRequest;
import com.example.ordermanagement.dto.order.OrderDto;
import com.example.ordermanagement.dto.order.OrderResponse;
import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(OrderConstant.ORDER_ENDPOINT)
public class OrderController {

    private final OrderService orderService;

    @GetMapping(OrderConstant.ORDER_ID)
    public ResponseEntity<OrderResponse> findOrderWithId(@PathVariable long orderId){
        return new ResponseEntity<>(orderService.findOrderWithId(orderId), HttpStatus.OK);
    }



//    @GetMapping(OrderConstant.ORDER_ID)
//    public ResponseEntity<Order> findOrderWithId(@PathVariable long orderId){
//        Order order = orderService.findOrderById(orderId);
//        return new ResponseEntity<>(order, HttpStatus.OK);
//    }
//
//    @PostMapping("/proceed-to-checkout")
//    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto order){
//        return new ResponseEntity<>(orderService.saveOrder(order), HttpStatus.CREATED);
//    }
//
//    @PostMapping("/confirm-order/{orderId}")
//    public ResponseEntity<OrderDto> confirmOrder(@RequestBody ConfirmOrderRequest confirmOrderRequest){
//        return new ResponseEntity<>(orderService.confirmCartOrder(confirmOrderRequest), HttpStatus.OK);
//    }
//
//    @PostMapping("/update-order-status/{orderId}")
//    public ResponseEntity<OrderDto> updateOrderStatus(@RequestBody OrderDto orderDto, @PathVariable long orderId){
//        return new ResponseEntity<>(orderService.updateOrder(orderId, orderDto), HttpStatus.OK);
//    }
//
//    @GetMapping("/order-history/{userId}")
//    public ResponseEntity<List<Order>> getUserOrderHistory(@PathVariable long userId){
//        return new ResponseEntity<>(orderService.getUserOrderHistory(userId), HttpStatus.OK);
//    }

}

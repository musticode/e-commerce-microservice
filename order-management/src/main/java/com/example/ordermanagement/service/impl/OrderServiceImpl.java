package com.example.ordermanagement.service.impl;

import com.example.ordermanagement.dto.OrderDto;
import com.example.ordermanagement.dto.ProductResponse;
import com.example.ordermanagement.event.OrderEvent;
import com.example.ordermanagement.external.CartClient;
import com.example.ordermanagement.external.ProductClient;
import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.model.OrderLineItem;
import com.example.ordermanagement.model.external.Cart;
import com.example.ordermanagement.model.external.Product;
import com.example.ordermanagement.repository.OrderRepository;
import com.example.ordermanagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    // cart'tan cart bilgilerini al,
    // cart'ın içindeki ürünleri topla
    // bu ürünleri payment service'e gönder (user ve ürün bilgileri)

    private final CartClient cartClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final OrderProducer orderProducer;

    /**
     * 4. When the user decides to proceed to checkout, an order is created.
     */

    @Override
    public Order findOrderById(long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("No order with id: " + orderId));
    }

    @Override
    public OrderDto saveOrder(OrderDto order) {

        final double price = calculateTotalPrice(order.getOrderLineItems());

        Order newOrder = new Order();
        newOrder.setName(order.getName());
        //newOrder.setCartId(order.getCartId());

        newOrder.setTotalPrice(price);

        newOrder.setUserId(order.getUserId());
        newOrder.setOrderLineItems(order.getOrderLineItems());
        newOrder.setSellerId(order.getSellerId());
        newOrder.setBuyerId(order.getBuyerId());



        orderRepository.save(newOrder);
        log.info("Order : {}", newOrder);


        OrderEvent orderEvent = OrderEvent
                .builder()
                .message("Order is placed")
                .status("PLACED")
                .order(newOrder)
                .build();

        orderProducer.sendMessage(orderEvent);
        log.info("Order event: {}", orderEvent);

        return modelMapper.map(newOrder, OrderDto.class);
    }




    @Override
    public OrderDto cancelOrder(long orderId) {

        Order order = findOrderById(orderId);
        order.setCancelledAt(new Date());
        orderRepository.save(order);

        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public OrderDto updateOrder(long orderId, OrderDto orderDto) {
        double totalPrice = calculateTotalPrice(orderDto.getOrderLineItems());
        if (totalPrice < 0){
            totalPrice = 0;
        }

        Order foundedOrder = findOrderById(orderId);
        foundedOrder.setName(orderDto.getName());
        foundedOrder.setCartId(orderDto.getCartId());
        foundedOrder.setTotalPrice(totalPrice);
        foundedOrder.setBuyerId(orderDto.getBuyerId());
        foundedOrder.setSellerId(orderDto.getSellerId());
        orderRepository.save(foundedOrder);

        return modelMapper.map(foundedOrder, OrderDto.class);
    }

    @Override
    public List<Order> getUserOrderHistory(long userId) {
        return orderRepository.findOrdersByBuyerId(userId);
    }

    @Override
    public Order confirmOrder(Order order) {

        // todo : create order / get created order
        // todo : send order to payment topic


        return null;
    }

    @Override
    public void cancelOrderById(long orderId) {
        Order order = findOrderById(orderId);
        orderRepository.delete(order);
        log.info("Order deleted : {}", order);
    }


    private double calculateTotalPrice(List<OrderLineItem> orderLineItemList) {

        // TODO go to product client with id list

        if (orderLineItemList.isEmpty()) {
            throw new RuntimeException("Empty order line item list");
        }


        List<Product> productList2 = new ArrayList<>();
        orderLineItemList.forEach(item -> productList2.add(productClient.findProductById(item.getProductId()).getBody()));

        // way 1
        double sumOfPrice = 0;
        for (OrderLineItem item : orderLineItemList) {
            sumOfPrice += productClient.findProductById(item.getProductId()).getBody().getPrice();
        }




        // way 2
        List<Double> priceList = new ArrayList<>();

        for (OrderLineItem item : orderLineItemList) {
            priceList.add(productClient.findProductById(item.getProductId()).getBody().getPrice());
        }

        double sum = 0;
        for (int i = 0; i < priceList.size(); i++) {
            sum += priceList.get(i);
        }

        return sum;
        // return sumOfPrice;
    }

}

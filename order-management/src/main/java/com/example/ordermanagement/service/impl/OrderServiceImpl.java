package com.example.ordermanagement.service.impl;

import com.example.ordermanagement.dto.order.*;
import com.example.ordermanagement.dto.orderlineitem.OrderLineItemResponse;
import com.example.ordermanagement.event.OrderEvent;
import com.example.ordermanagement.event.notification.NotificationEvent;
import com.example.ordermanagement.event.notification.NotificationType;
import com.example.ordermanagement.external.CartClient;
import com.example.ordermanagement.external.ProductClient;
import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.model.OrderLineItem;
import com.example.ordermanagement.model.external.Cart;
import com.example.ordermanagement.model.external.CartResponse;
import com.example.ordermanagement.model.external.Product;
import com.example.ordermanagement.repository.OrderRepository;
import com.example.ordermanagement.service.OrderLineItemService;
import com.example.ordermanagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private final OrderProducerService orderProducerService;
    private final NotificationProducer notificationProducer;
    private final OrderLineItemService orderLineItemService;
    private final NotificationProducerService notificationProducerService;

    // NEW

    public String cancelOrderStatus(OrderCancelRequest request){
        Order order = findOrderEntityByOrderId( request.getId());

        if (order.getStatus().equals("CONFIRMED")){
            log.error("Order status {}", order.getStatus());
            return null;
        }

        order.setCancelledAt(new Date());
        order.setCancelReason(request.getReason());

        orderRepository.save(order);

        log.info("Order deleted at {}", order.getCancelledAt());
        return "Deleted";

    }

    public OrderResponse confirmOrder(ConfirmOrderRequest request){
        // find order with id in rquest
        Order order = findOrderEntityByOrderId(request.getId());

        // set order's status as CONFIRMED
        order.setStatus("CONFIRMED"); // OrderStatus.CONFIRMED


        OrderEvent orderEvent = OrderEvent.builder()
                .message("Order confirmed")
                .status(order.getStatus())
                .order(order)
                .build();

        // todo send event to notification service
        notificationProducerService.sendMessage(orderEvent);

        // todo send event to payment service
        orderProducerService.sendMessage(orderEvent);

        // save update order with new status
        orderRepository.save(order);



        // return order response
        return mapOrderToOrderResponse(order);
    }



    public OrderResponse proceedToCheckOut(OrderCreateRequest request){

        // order line items from request
        List<OrderLineItem> orderLineItemList = request
                .getItems()
                .stream()
                .map(itemRequest -> modelMapper.map(itemRequest, OrderLineItem.class))
                .collect(Collectors.toList());

        // save all order line items
        orderLineItemList.forEach(orderLineItem -> orderLineItemService.saveOrderLineItem(orderLineItem));

        // find cart with userId in req
        CartResponse cartResponse = cartClient.getUserCart(request.getUserId()).getBody();
        log.info("Cart Response: {}", cartResponse );


        // creating order object
        Order order = Order.builder()
                .buyerId(request.getUserId())
                .orderLineItems(orderLineItemList)
                .build();
        saveOrder(order);


        return mapOrderToOrderResponse(order);
    }


    @Override
    public OrderResponse findOrderWithId(long orderId) {
        Order order = findOrderEntityByOrderId(orderId);
        log.info("FOUND Order: {}", order);
        return mapOrderToOrderResponse(order);
    }

    private Order saveOrderEntity(Order order){
        log.info("Saved Order: {}", order);
        return orderRepository.save(order);
    }
    private Order findOrderEntityByOrderId(long orderId){
        return orderRepository.findById(orderId).orElseThrow(()-> new RuntimeException("Order not found"));
    }

    private OrderResponse mapOrderToOrderResponse(Order order){
        return modelMapper.map(order, OrderResponse.class);
    }







    /// OLD



    /**
     * 4. When the user decides to proceed to checkout, an order is created.
     */


    // proceed to checkout
    public OrderResponse createProductOrder(OrderCreateRequest orderCreateRequest){


        // find user's cart
        CartResponse cartResponse = cartClient
                .getUserCart(orderCreateRequest.getUserId())
                .getBody();

        // create order with cartResponse object
        List<OrderLineItem> orderLineItems = orderCreateRequest
                .getItems()
                .stream()
                .map(item -> modelMapper.map(item, OrderLineItem.class))
                .collect(Collectors.toList());

        orderLineItems.forEach(orderLineItem -> orderLineItemService.saveOrderLineItem(orderLineItem));

        Order order = Order.builder()
                .name("Order")
                .orderLineItems(orderLineItems)
                .build();


        // save order to orderRepository

        // return OrderResponse object to client











        return null;
    }

    public OrderDetailResponse retrieveOrderDetailByOrderId(long orderId) {

        Order order = findOrderById(orderId);

        return OrderDetailResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .items(order
                        .getOrderLineItems()
                        .stream()
                        .map(orderLineItem -> modelMapper.map(orderLineItem, OrderLineItemResponse.class))
                        .collect(Collectors.toList()))
                .build();
    }



    @Override
    public Order findOrderById(long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("No order with id: " + orderId));
    }

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }


    @Override
    public OrderDto saveOrder(OrderDto order) {

        final double price = calculateTotalPrice(order.getOrderLineItems());

        Cart cart = cartClient.findCartById(order.getCartId()).getBody();

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

        orderProducerService.sendMessage(orderEvent);
        log.info("Order event: {}", orderEvent);

        return modelMapper.map(newOrder, OrderDto.class);
    }

    @Override
    public OrderDto cancelOrder(long orderId) {
        Order order = findOrderById(orderId);
        order.setCancelledAt(new Date());
        orderRepository.save(order);
        log.info("Order is cancelled: {}", order);



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

        // send notification
        String notificationMessage = String.format("Order %s is updated", orderDto.toString());
        NotificationEvent notificationEvent = NotificationEvent.builder()
                .userId(orderDto.getUserId())
                .message(notificationMessage)
                .notificationType(NotificationType.ORDER_UPDATE)
                .build();
        notificationProducer.sendMessage(notificationEvent);

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

        // todo: send order to notification topic
        // send notification
        String notificationMessage = String.format("Order %s is confirmed", order.toString());
        NotificationEvent notificationEvent = NotificationEvent.builder()
                .userId(order.getUserId())
                .message(notificationMessage)
                .notificationType(NotificationType.ORDER_CONFIRMATION)
                .build();
        notificationProducer.sendMessage(notificationEvent);

        return null;
    }

    @Override
    public OrderDto confirmCartOrder(ConfirmOrderRequest confirmOrderRequest) {
        // get order with id
        // set order's status as CONFIRMED
        // send event to notification service
        // send event to payment service

        return null;
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

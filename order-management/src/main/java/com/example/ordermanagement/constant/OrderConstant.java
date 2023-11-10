package com.example.ordermanagement.constant;

public class OrderConstant {
    public static final String ORDER_ENDPOINT = "/api/v1/orders";
    public static final String ORDER_ID = "/{orderId}";
    public static final String USER_ORDER_HISTORY = "/order-history/{userId}";
    public static final String UPDATE_ORDER_STATUS_WITH_ID = "/update-order-status/{orderId}";
    public static final String PROCEED_TO_CHECKOUT = "/proceed-to-checkout";
}

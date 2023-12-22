package com.example.logisticservice.model;

import java.util.List;

public class Order {
    private int id;
    private String number;
    private Driver driver;
    private Transport transport;
    private List<Trailer> trailers;
    private Route route;
}

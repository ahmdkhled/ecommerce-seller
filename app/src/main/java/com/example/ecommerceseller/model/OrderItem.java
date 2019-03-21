package com.example.ecommerceseller.model;


import java.util.ArrayList;

public class OrderItem {

    private int id;
    private String name;
    private double price;
    private int orderItem_id;
    private int orderItem_quantity;
    private ArrayList<Media> media;

    public OrderItem(int id, String name, double price, int orderItem_id, int orderItem_quantity, ArrayList<Media> media) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.orderItem_id = orderItem_id;
        this.orderItem_quantity = orderItem_quantity;
        this.media = media;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getOrderItem_id() {
        return orderItem_id;
    }

    public int getOrderItem_quantity() {
        return orderItem_quantity;
    }

    public ArrayList<Media> getMedia() {
        return media;
    }
}

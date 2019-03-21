package com.example.ecommerceseller.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Order {

    @SerializedName("order_id")
    private int order_id;
    @SerializedName("order_date")
    private String order_date;
    @SerializedName("order_userId")
    private int userId;
    @SerializedName("order_status")
    private String status;
    @SerializedName("order_addressId")
    private int addressId;
    @SerializedName("order_total")
    private int totalPrice;
    @SerializedName("orderItems")
    private ArrayList<OrderItem> orderItems;


    public Order(int order_id, String order_date, int userId, String status, int addressId, int totalPrice, ArrayList<OrderItem> orderItems) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.userId = userId;
        this.status = status;
        this.addressId = addressId;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}

package com.example.ecommerceseller.model;

import com.google.gson.annotations.SerializedName;

public class Dashboard {

    @SerializedName("products_number")
    private int productsNum;
    @SerializedName("orders_number")
    private int ordersNum;
    @SerializedName("success_rate")
    private String successRate;
    @SerializedName("revenue")
    private String revenue;

    public int getProductsNum() {
        return productsNum;
    }

    public int getOrdersNum() {
        return ordersNum;
    }

    public String getSuccessRate() {
        return successRate;
    }

    public String getRevenue() {
        return revenue;
    }
}

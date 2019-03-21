package com.example.ecommerceseller.network;

import com.example.ecommerceseller.model.Order;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("orders.php")
    Call<ArrayList<Order>> getOrders(@Query("marketId")String marketId);
}

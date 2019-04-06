package com.example.ecommerceseller.network;

import com.example.ecommerceseller.model.Category;
import com.example.ecommerceseller.model.LoginResponse;
import com.example.ecommerceseller.model.Order;
import com.example.ecommerceseller.model.ProductResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("orders.php")
    Call<ArrayList<Order>> getOrders(@Query("marketId")String marketId);

    @GET("categories.php")
    Call<ArrayList<Category>> getCategories();

    @FormUrlEncoded
    @POST("products.php")
    Call<ProductResponse> UplaodProduct(@Field("name") String name, @Field("description")String desc,
                                        @Field("quantity")int quantity, @Field("price")float price,
                                        @Field("categoryId")int categoryId, @Field("marketId")int marketId);

    @FormUrlEncoded
    @POST("sellerLogin.php")
    Call<LoginResponse> login (@Field("email") String email,@Field("password") String password);
}

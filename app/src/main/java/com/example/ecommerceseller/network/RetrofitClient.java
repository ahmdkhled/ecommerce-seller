package com.example.ecommerceseller.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    private static ApiService ApiService;

    private static Retrofit getInstance(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl("http://ecommerceg.000webhostapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

    public static ApiService getApiService(){
        if (ApiService==null){
            ApiService=getInstance().create(ApiService.class);
        }
        return ApiService;
    }

}

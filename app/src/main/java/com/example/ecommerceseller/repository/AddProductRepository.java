package com.example.ecommerceseller.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.ecommerceseller.model.ProductResponse;
import com.example.ecommerceseller.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductRepository {

    private static AddProductRepository addProductRepository;
    private MutableLiveData<ProductResponse> productResponse=new MutableLiveData<>();

    public static AddProductRepository getInstance(){
        if (addProductRepository==null)
            addProductRepository=new AddProductRepository();
        return addProductRepository;
    }

    public MutableLiveData<ProductResponse> uploadProduct(String name, float price, int quantity
                                        , String desc, int categoryId, int marketId ){

        RetrofitClient.getApiService().
                UplaodProduct(name,desc,quantity,price,categoryId,marketId)
                .enqueue(new Callback<ProductResponse>() {
                    @Override
                    public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                        if (response.isSuccessful()){
                            ProductResponse r=response.body();
                            productResponse.setValue(r);
                        }

                    }

                    @Override
                    public void onFailure(Call<ProductResponse> call, Throwable t) {
                        Log.d("ADDDDPRODUCTT",t.getMessage());
                    }
                });

        return productResponse;
    }
}

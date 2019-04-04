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
    private MutableLiveData<Boolean> isLoading=new MutableLiveData<>();
    private MutableLiveData<String> error=new MutableLiveData<>();


    public static AddProductRepository getInstance(){
        if (addProductRepository==null)
            addProductRepository=new AddProductRepository();
        return addProductRepository;
    }

    public MutableLiveData<ProductResponse> uploadProduct(String name, float price, int quantity
                                        , String desc, int categoryId, int marketId ){

        isLoading.setValue(true);
        RetrofitClient.getApiService().
                UplaodProduct(name,desc,quantity,price,categoryId,marketId)
                .enqueue(new Callback<ProductResponse>() {
                    @Override
                    public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                        if (response.isSuccessful()){
                            ProductResponse r=response.body();
                            productResponse.setValue(r);
                        }
                        isLoading.setValue(false);

                    }

                    @Override
                    public void onFailure(Call<ProductResponse> call, Throwable t) {
                        isLoading.setValue(false);
                        error.setValue(t.getMessage());
                        Log.d("ADDDDPRODUCTT",t.getMessage());
                    }
                });

        return productResponse;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<String> getError() {
        return error;
    }
}

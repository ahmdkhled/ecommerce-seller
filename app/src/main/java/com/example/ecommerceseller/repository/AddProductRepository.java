package com.example.ecommerceseller.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.ecommerceseller.model.Category;
import com.example.ecommerceseller.model.ProductResponse;
import com.example.ecommerceseller.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductRepository {

    private static AddProductRepository addProductRepository;
    private MutableLiveData<ProductResponse> productResponse=new MutableLiveData<>();
    private MutableLiveData<ArrayList<Category>> categoriesList=new MutableLiveData<>();
    private MutableLiveData<Boolean> isUploading =new MutableLiveData<>();
    private MutableLiveData<Boolean> isCategoriesLoading =new MutableLiveData<>();
    private MutableLiveData<String> UploadError =new MutableLiveData<>();
    private MutableLiveData<String> LoadingError =new MutableLiveData<>();


    public static AddProductRepository getInstance(){
        if (addProductRepository==null)
            addProductRepository=new AddProductRepository();
        return addProductRepository;
    }

    public MutableLiveData<ProductResponse> uploadProduct(String name, float price, int quantity
                                        , String desc, int categoryId, int marketId ){
        Log.d("ADDDDPRODUCTT","request");

        isUploading.setValue(true);
        RetrofitClient.getApiService().
                UplaodProduct(name,desc,quantity,price,categoryId,marketId)
                .enqueue(new Callback<ProductResponse>() {
                    @Override
                    public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                        if (response.isSuccessful()){
                            ProductResponse r=response.body();
                            productResponse.setValue(r);
                        }
                        isUploading.setValue(false);

                    }

                    @Override
                    public void onFailure(Call<ProductResponse> call, Throwable t) {
                        isUploading.setValue(false);
                        UploadError.setValue(t.getMessage());
                        Log.d("ADDDDPRODUCTT",t.getMessage());
                    }
                });

        return productResponse;
    }


    public MutableLiveData<ArrayList<Category>> getCategories(){
        RetrofitClient.getApiService()
                .getCategories()
                .enqueue(new Callback<ArrayList<Category>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                        if (response.isSuccessful()){
                            ArrayList<Category> list=response.body();
                            categoriesList.setValue(list);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Category>> call, Throwable t) {

                    }
                });
        return categoriesList;
    }

    public MutableLiveData<Boolean> getIsUploading() {
        return isUploading;
    }

    public MutableLiveData<String> getUploadError() {
        return UploadError;
    }

    public MutableLiveData<Boolean> getIsCategoriesLoading() {
        return isCategoriesLoading;
    }

    public MutableLiveData<String> getLoadingError() {
        return LoadingError;
    }
}

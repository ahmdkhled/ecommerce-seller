package com.example.ecommerceseller.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.ecommerceseller.model.Category;
import com.example.ecommerceseller.model.ProductResponse;
import com.example.ecommerceseller.model.UploadResponse;
import com.example.ecommerceseller.network.RetrofitClient;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductRepository {

    private static AddProductRepository addProductRepository;
    private MutableLiveData<ProductResponse> productResponse=new MutableLiveData<>();
    private MutableLiveData<ArrayList<Category>> categoriesList=new MutableLiveData<>();
    private MutableLiveData<UploadResponse> uploadImage=new MutableLiveData<>();

    private MutableLiveData<Boolean> isProductUploading =new MutableLiveData<>();
    private MutableLiveData<Boolean> isCategoriesLoading =new MutableLiveData<>();
    private MutableLiveData<Boolean> isImageUpLoading =new MutableLiveData<>();

    private MutableLiveData<String> productUploadError =new MutableLiveData<>();
    private MutableLiveData<String> categoriesLoadingError =new MutableLiveData<>();
    private MutableLiveData<String> imageUploadingError =new MutableLiveData<>();


    public static AddProductRepository getInstance(){
        if (addProductRepository==null)
            addProductRepository=new AddProductRepository();
        return addProductRepository;
    }

    public MutableLiveData<ProductResponse> uploadProduct(String name, float price, int quantity
                                        , String desc, int categoryId, int marketId ){
        Log.d("ADDDDPRODUCTT","request");

        isProductUploading.setValue(true);
        RetrofitClient.getApiService().
                UplaodProduct(name,desc,quantity,price,categoryId,marketId)
                .enqueue(new Callback<ProductResponse>() {
                    @Override
                    public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                        if (response.isSuccessful()){
                            ProductResponse r=response.body();
                            productResponse.setValue(r);
                        }
                        isProductUploading.setValue(false);

                    }

                    @Override
                    public void onFailure(Call<ProductResponse> call, Throwable t) {
                        isProductUploading.setValue(false);
                        productUploadError.setValue(t.getMessage());
                        Log.d("ADDDDPRODUCTT",t.getMessage());
                    }
                });

        return productResponse;
    }


    public MutableLiveData<UploadResponse> uploadImages(RequestBody productId, MultipartBody.Part image){
        isImageUpLoading.setValue(true);
        RetrofitClient.getApiService()
                .uploadImages(productId,image)
                .enqueue(new Callback<UploadResponse>() {
                    @Override
                    public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                        Log.d("UPLOOOOD","message "+response.body().getMessage());
                        uploadImage.setValue(response.body());
                        isImageUpLoading.setValue(false);
                    }

                    @Override
                    public void onFailure(Call<UploadResponse> call, Throwable t) {
                        isImageUpLoading.setValue(false);
                        imageUploadingError.setValue(t.getMessage());

                        Log.d("UPLOOOOD","error "+t.getMessage());
                    }
                });
        return uploadImage;
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

    public MutableLiveData<ProductResponse> getProductResponse() {
        return productResponse;
    }

    public MutableLiveData<Boolean> getIsProductUploading() {
        return isProductUploading;
    }

    public MutableLiveData<String> getProductUploadError() {
        return productUploadError;
    }

    public MutableLiveData<Boolean> getIsCategoriesLoading() {
        return isCategoriesLoading;
    }


    public MutableLiveData<Boolean> getIsImageUpLoading() {
        return isImageUpLoading;
    }

    public MutableLiveData<String> getImageUploadingError() {
        return imageUploadingError;
    }

    public MutableLiveData<String> getCategoriesLoadingError() {
        return categoriesLoadingError;
    }
}

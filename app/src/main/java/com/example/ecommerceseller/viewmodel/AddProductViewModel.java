package com.example.ecommerceseller.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.ecommerceseller.model.Category;
import com.example.ecommerceseller.model.ProductResponse;
import com.example.ecommerceseller.model.UploadResponse;
import com.example.ecommerceseller.repository.AddProductRepository;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddProductViewModel extends ViewModel {
    private MutableLiveData<ProductResponse> productResponse;
    private MutableLiveData<ArrayList<Category>> categoriesList;
    private MutableLiveData<UploadResponse> imageUpload;

    public MutableLiveData<ProductResponse> uploadProduct(String name,float price,int quantity
            ,String desc,int categoryId,int marketId){
        productResponse= AddProductRepository.getInstance()
                .uploadProduct(name, price, quantity, desc, categoryId, marketId);
        return productResponse;
    }

    public MutableLiveData<ArrayList<Category>> getCategories(){
        if (categoriesList==null)
            categoriesList=AddProductRepository.getInstance()
                    .getCategories();
        return categoriesList;
    }

    public MutableLiveData<UploadResponse> uploadImage(RequestBody productId, MultipartBody.Part image) {
        imageUpload= AddProductRepository.getInstance()
                .uploadImages(productId,image);
        return imageUpload;
    }

    public MutableLiveData<ProductResponse> getProductResponse() {
        return productResponse;
    }

    public MutableLiveData<Boolean> getIsProductUploading() {
        return AddProductRepository.getInstance().getIsProductUploading();
    }

    public MutableLiveData<Boolean> getIsCategoriesLoading() {
        return AddProductRepository.getInstance().getIsCategoriesLoading();
    }

    public MutableLiveData<Boolean> getIsImagesUpLoading() {
        return AddProductRepository.getInstance().getIsImageUpLoading();
    }

    public MutableLiveData<String> getProductUploadError() {
        return AddProductRepository.getInstance().getProductUploadError();
    }

    public MutableLiveData<String> getCategoriesLoadingError() {
        return AddProductRepository.getInstance().getCategoriesLoadingError();
    }

    public MutableLiveData<String> getImageUploadingError() {
        return AddProductRepository.getInstance().getImageUploadingError();
    }

}

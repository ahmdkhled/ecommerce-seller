package com.example.ecommerceseller.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.ecommerceseller.model.Category;
import com.example.ecommerceseller.model.ProductResponse;
import com.example.ecommerceseller.repository.AddProductRepository;

import java.util.ArrayList;

public class AddProductViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Category>> categoriesList;

    public MutableLiveData<ProductResponse> uploadProduct(String name,float price,int quantity
            ,String desc,int categoryId,int marketId){
        return AddProductRepository.getInstance()
                .uploadProduct(name, price, quantity, desc, categoryId, marketId);
    }

    public MutableLiveData<ArrayList<Category>> getCategories(){
        if (categoriesList==null)
            categoriesList=AddProductRepository.getInstance()
                    .getCategories();
        return categoriesList;
    }

    public MutableLiveData<Boolean> getIsUploading() {
        return AddProductRepository.getInstance().getIsUploading();
    }

    public MutableLiveData<String> getUploadError() {
        return AddProductRepository.getInstance().getUploadError();
    }


    public MutableLiveData<Boolean> getIsCategoriesLoading() {
        return AddProductRepository.getInstance().getIsCategoriesLoading();
    }

    public MutableLiveData<String> getLoadingError() {
        return AddProductRepository.getInstance().getLoadingError();
    }


}

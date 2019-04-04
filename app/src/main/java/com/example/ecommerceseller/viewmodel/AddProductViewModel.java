package com.example.ecommerceseller.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.ecommerceseller.model.ProductResponse;
import com.example.ecommerceseller.repository.AddProductRepository;

public class AddProductViewModel extends ViewModel {
    private MutableLiveData<ProductResponse> productResponse;
    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<String> error;

    public MutableLiveData<ProductResponse> uploadProduct(String name,float price,int quantity
            ,String desc,int categoryId,int marketId){
        return AddProductRepository.getInstance()
                .uploadProduct(name, price, quantity, desc, categoryId, marketId);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return AddProductRepository.getInstance().getIsLoading();
    }

    public MutableLiveData<String> getError() {
        return AddProductRepository.getInstance().getError();
    }
}

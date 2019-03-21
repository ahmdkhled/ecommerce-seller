package com.example.ecommerceseller.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.ecommerceseller.model.Order;
import com.example.ecommerceseller.repository.OrdersRepository;

import java.util.ArrayList;

public class OrdersViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Order>> orders;

    public MutableLiveData<ArrayList<Order>> getOrders(String marketId){
        if (orders==null){
            orders=OrdersRepository.getInstance()
                    .getOrders(marketId);
            Log.d("VIEWMODELL","retrieve orders");
        }
        return orders;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return OrdersRepository.getInstance().getIsLoading();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("VIEWMODELL","onCleared");

    }
}

package com.example.ecommerceseller.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.ecommerceseller.model.Order;
import com.example.ecommerceseller.repository.OrdersRepository;

import java.util.ArrayList;

public class OrdersViewModel extends ViewModel {



    public MutableLiveData<ArrayList<Order>> getOrders(String marketId){
        return OrdersRepository.getInstance()
                .getOrders(marketId);
    }
}

package com.example.ecommerceseller.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.ecommerceseller.R;
import com.example.ecommerceseller.adapter.OrdersAdapter;
import com.example.ecommerceseller.model.Order;
import com.example.ecommerceseller.viewmodel.OrdersViewModel;

import java.util.ArrayList;

public class OrdersFrag extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.orders_frag,container,false);
        final RecyclerView ordersRecycler=v.findViewById(R.id.ordersRecycler);
        ProgressBar ordersPB=v.findViewById(R.id.orders_PB);

        OrdersViewModel ordersViewModel= ViewModelProviders.of(this).get(OrdersViewModel.class);
        ordersViewModel.getOrders("1").observe(this, new Observer<ArrayList<Order>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Order> orders) {
                OrdersAdapter ordersAdapter=new OrdersAdapter(getContext(),orders);
                ordersRecycler.setAdapter(ordersAdapter);
                ordersRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        return v;
    }
}

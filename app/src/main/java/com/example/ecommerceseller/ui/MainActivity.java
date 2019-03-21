package com.example.ecommerceseller.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;

import com.example.ecommerceseller.R;
import com.example.ecommerceseller.adapter.OrdersAdapter;
import com.example.ecommerceseller.model.Order;
import com.example.ecommerceseller.viewmodel.OrdersViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OrdersFrag ordersFrag= (OrdersFrag) getSupportFragmentManager().findFragmentByTag("orders_tag");
        if (ordersFrag==null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_main,new OrdersFrag(),"orders_tag")
                    .commit();
        }



        bottomNavigationView=findViewById(R.id.mainBottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d("BOTTOMVIEWW",""+item.getTitle());
                return true;
            }
        });

    }
}

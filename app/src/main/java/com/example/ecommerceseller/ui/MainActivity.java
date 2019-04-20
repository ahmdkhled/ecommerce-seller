package com.example.ecommerceseller.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.ecommerceseller.R;
import com.example.ecommerceseller.adapter.OrdersAdapter;
import com.example.ecommerceseller.model.Order;
import com.example.ecommerceseller.viewmodel.MainActivityVM;
import com.example.ecommerceseller.viewmodel.OrdersViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    OrdersFrag ordersFrag = new OrdersFrag();
    AddProductFrag addProductFrag =new AddProductFrag();
    DashboardFrag dashboardFrag = new DashboardFrag();
    MainActivityVM vm;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        if(savedInstanceState == null) {
            fragmentManager.beginTransaction().add(R.id.container, dashboardFrag).hide(dashboardFrag).commit();
            fragmentManager.beginTransaction().add(R.id.container, addProductFrag).hide(addProductFrag).commit();
            fragmentManager.beginTransaction().add(R.id.container, ordersFrag).commit();
        }

        bottomNavigationView=findViewById(R.id.mainBottomNavigation);
        toolbar=findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        vm=ViewModelProviders.of(this).get(MainActivityVM.class);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.ordersTab){
                    showFragmenr(ordersFrag);
                }else if (item.getItemId  ()==R.id.addProductTab){
                    showFragmenr(addProductFrag);
                }else if (item.getItemId()==R.id.dashboardTab){
                    showFragmenr(dashboardFrag);
                }
                return true;
            }
        });

    }

    public void showFragmenr(Fragment fragment){
        if(fragment.isAdded()){
            fragmentManager.beginTransaction().show(fragment).commit();
        }else{
            fragmentManager.beginTransaction().add(R.id.container,fragment).commit();
        }

        for(Fragment frag : fragmentManager.getFragments()){
            if(frag != fragment && frag.isAdded()){
                fragmentManager.beginTransaction().hide(frag).commit();
            }
        }
    }


}

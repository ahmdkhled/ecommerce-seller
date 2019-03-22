package com.example.ecommerceseller.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.ecommerceseller.R;
import com.example.ecommerceseller.adapter.OrdersAdapter;
import com.example.ecommerceseller.model.Order;
import com.example.ecommerceseller.viewmodel.OrdersViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    OrdersFrag ordersFrag;
    AddProductFrag addProductFrag;
    DashboardFrag dashboardFrag;
    Fragment current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.mainBottomNavigation);
        toolbar=findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        createFragments();



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.ordersTab){
                    showFragment(ordersFrag);
                }else if (item.getItemId()==R.id.addProductTab){
                    showFragment(addProductFrag);
                }else if (item.getItemId()==R.id.dashboardTab){
                    showFragment(dashboardFrag);

                }
                return true;
            }
        });

    }

    void showFragment(Fragment fragment){
        Log.d("BOTTOMVIEWW","current ");

        if (fragment==current){
            Log.d("BOTTOMVIEWW","null");
            return;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .show(fragment)
                .hide(current)
                .addToBackStack(null)
                .commit();
        current=fragment;
    }

    void createFragments(){
        OrdersFrag ordersFragment = (OrdersFrag) getSupportFragmentManager().findFragmentByTag("orders_frag");
        AddProductFrag addProductFragment = (AddProductFrag) getSupportFragmentManager().findFragmentByTag("addProduct_frag");
        DashboardFrag dashboardFragment = (DashboardFrag) getSupportFragmentManager().findFragmentByTag("dashboard_frag");
        if (ordersFragment==null){
            ordersFrag=new OrdersFrag();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container,ordersFrag,"orders_frag")
                    .commit();
        }

        if (addProductFragment==null){
            addProductFrag=new AddProductFrag();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container,addProductFrag,"addProduct_frag")
                    .hide(addProductFrag)
                    .commit();
        }

        if (dashboardFragment==null){
            dashboardFrag=new DashboardFrag();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container,dashboardFrag,"dashboard_frag")
                    .hide(dashboardFrag)
                    .commit();
        }

        current=ordersFrag;
    }
}

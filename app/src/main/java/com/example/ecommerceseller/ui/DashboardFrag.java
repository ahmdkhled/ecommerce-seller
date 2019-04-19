package com.example.ecommerceseller.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceseller.R;
import com.example.ecommerceseller.model.Dashboard;
import com.example.ecommerceseller.utils.SessionManager;
import com.example.ecommerceseller.viewmodel.DashboardViewModel;

public class DashboardFrag  extends Fragment {

    TextView productsNum,ordersNum,successRate,revenue;
    ProgressBar progressBar;
    DashboardViewModel dashboardViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.dashboard_frag,container,false);
        productsNum=v.findViewById(R.id.productsNum);
        ordersNum=v.findViewById(R.id.ordersNum);
        successRate=v.findViewById(R.id.successRate);
        revenue=v.findViewById(R.id.revenue);
        progressBar=v.findViewById(R.id.dashboard_PB);
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        SessionManager sessionManager=SessionManager.getInstance(getContext());
        dashboardViewModel.getDashBoardData(sessionManager.getMarketId());
        observeDashboardData();
        observeDashboardLoading();
        observeDashboardError();
        return v;
    }

    void observeDashboardData(){
        dashboardViewModel.getDashBoard()
                .observe(getActivity(), new Observer<Dashboard>() {
                    @Override
                    public void onChanged(@Nullable Dashboard dashboard) {
                        populateDashboard(dashboard);
                    }
                });
    }

    void populateDashboard(Dashboard dashboard){
        if (dashboard==null){
            Toast.makeText(getContext(),
                    "error getting dashboard data",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        productsNum.setText(String.valueOf(dashboard.getProductsNum()));
        ordersNum.setText(String.valueOf(dashboard.getOrdersNum()));
        successRate.setText(dashboard.getSuccessRate());
        revenue.setText(dashboard.getRevenue());
    }

    void observeDashboardLoading(){
        dashboardViewModel.getIsDashboardLoading()
                .observe(getActivity(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (aBoolean!=null&&aBoolean)
                            progressBar.setVisibility(View.VISIBLE);
                        else
                            progressBar.setVisibility(View.GONE);
                    }
                });
    }
    void observeDashboardError(){
        dashboardViewModel.getDashboardError()
                .observe(getActivity(), new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        Toast.makeText(getContext()
                                , "error: "+s
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

package com.example.ecommerceseller.repository;

import android.arch.lifecycle.MutableLiveData;

import com.example.ecommerceseller.model.Dashboard;
import com.example.ecommerceseller.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardRepository {

    private MutableLiveData<Dashboard> dashBoard=new MutableLiveData<>();
    private MutableLiveData<Boolean> isDashboardLoading=new MutableLiveData<>();
    private MutableLiveData<String> DashboardError=new MutableLiveData<>();
    private static DashboardRepository dashboardRepository;

    public static DashboardRepository getInstance(){
        if (dashboardRepository==null)
            dashboardRepository=new DashboardRepository();
        return dashboardRepository;
    }

    public MutableLiveData<Dashboard> getDashboardData(int sellerId){
        isDashboardLoading.setValue(true);
        RetrofitClient.getApiService()
                .getDashboardData(sellerId)
                .enqueue(new Callback<Dashboard>() {
                    @Override
                    public void onResponse(Call<Dashboard> call, Response<Dashboard> response) {
                        dashBoard.setValue(response.body());
                        isDashboardLoading.setValue(false);
                    }

                    @Override
                    public void onFailure(Call<Dashboard> call, Throwable t) {
                        DashboardError.setValue(t.getMessage());
                        isDashboardLoading.setValue(false);
                    }
                });
        return dashBoard;
    }

    public MutableLiveData<Dashboard> getDashBoard() {
        return dashBoard;
    }

    public MutableLiveData<Boolean> getIsDashboardLoading() {
        return isDashboardLoading;
    }

    public MutableLiveData<String> getDashboardError() {
        return DashboardError;
    }
}

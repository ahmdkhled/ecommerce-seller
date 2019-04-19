package com.example.ecommerceseller.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.ecommerceseller.model.Dashboard;
import com.example.ecommerceseller.repository.DashboardRepository;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<Dashboard> dashBoard;
    private MutableLiveData<Boolean> isDashboardLoading;
    private MutableLiveData<String> DashboardError;

    public MutableLiveData<Dashboard> getDashBoardData(int sellerId) {
        if (dashBoard==null)
        dashBoard= DashboardRepository.getInstance().getDashboardData(sellerId);
        return dashBoard;
    }

    public MutableLiveData<Boolean> getIsDashboardLoading() {
        return DashboardRepository.getInstance().getIsDashboardLoading();
    }

    public MutableLiveData<String> getDashboardError() {
        return DashboardRepository.getInstance().getDashboardError();
    }

    public MutableLiveData<Dashboard> getDashBoard() {
        return dashBoard;
    }
}

package com.example.ecommerceseller.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.support.v4.app.Fragment;
import android.util.Log;

public class MainActivityVM extends ViewModel {

    private Fragment current;

    public Fragment getCurrent() {
        return current;
    }

    public void setCurrent(Fragment current) {
        this.current = current;

    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}

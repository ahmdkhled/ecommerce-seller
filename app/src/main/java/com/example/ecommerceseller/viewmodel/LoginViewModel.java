package com.example.ecommerceseller.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.ecommerceseller.model.LoginResponse;
import com.example.ecommerceseller.repository.LoginRepository;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<LoginResponse> loginResponse;

    public MutableLiveData<LoginResponse> login(String email,String password){
        loginResponse=LoginRepository.getInstance().login(email,password);
        return loginResponse;
    }

    public MutableLiveData<String> getLoginError() {
        return LoginRepository.getInstance().getLoginError();
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return LoginRepository.getInstance().getIsLoading();
    }

    public MutableLiveData<LoginResponse> getLoginResponse() {
        return loginResponse;
    }
}

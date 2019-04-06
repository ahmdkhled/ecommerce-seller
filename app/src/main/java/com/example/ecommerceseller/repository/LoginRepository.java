package com.example.ecommerceseller.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import com.example.ecommerceseller.model.LoginResponse;
import com.example.ecommerceseller.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private static LoginRepository loginRepository;
    private MutableLiveData<LoginResponse> loginResponse=new MutableLiveData<>();
    private MutableLiveData<String> loginError=new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading=new MutableLiveData<>();

    public static LoginRepository getInstance(){
        if (loginRepository==null)
            loginRepository=new LoginRepository();
        return  loginRepository;
    }

    public MutableLiveData<LoginResponse> login(String email,String password){
        Log.d("LOGINNN", "request: ");
        isLoading.setValue(true);
        RetrofitClient.getApiService()
                .login(email,password)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()){
                            loginResponse.setValue(response.body());
                            isLoading.setValue(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        isLoading.setValue(false);
                        loginError.setValue(t.getMessage());
                    }
                });

        return loginResponse;
    }

    public MutableLiveData<String> getLoginError() {
        return loginError;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }
}

package com.example.ecommerceseller.ui;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ecommerceseller.R;
import com.example.ecommerceseller.model.LoginResponse;
import com.example.ecommerceseller.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout email_IL,password_IL;
    Button login;
    ProgressBar progressBar;
    LoginViewModel loginViewModel;
    LifecycleOwner lifecycleOwner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_IL=findViewById(R.id.loginEMail_IL);
        password_IL=findViewById(R.id.loginPass_IL);
        login=findViewById(R.id.login);
        progressBar=findViewById(R.id.login_PB);

        lifecycleOwner=this;
        loginViewModel= ViewModelProviders.of(this).get(LoginViewModel.class);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setEnabled(false);
                if (!validateInput()){
                    login.setEnabled(true);
                    return;
                }
                String email=email_IL.getEditText().getText().toString();
                String password=password_IL.getEditText().getText().toString();
                loginViewModel.login(email,password);
                observeLoginResponse();
                observeError();
                observeLoading();
            }
        });



    }
    private void observeLoginResponse(){
        if (loginViewModel.getLoginResponse().hasActiveObservers())
            return;
        loginViewModel.getLoginResponse()
                .observe(lifecycleOwner, new Observer<LoginResponse>() {
                    @Override
                    public void onChanged(@Nullable LoginResponse loginResponse) {
                        Log.d("LOGINNN", "on response: ");

                        login.setEnabled(true);
                        if (loginResponse!=null&&loginResponse.getMessage()!=null){
                            Toast.makeText(LoginActivity.this, loginResponse.getMessage()
                                    , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void observeLoading(){
        if (loginViewModel.getIsLoading().hasActiveObservers())
            return;
        loginViewModel.getIsLoading()
                .observe(lifecycleOwner, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean!=null&&aBoolean){
                    progressBar.setVisibility(View.VISIBLE);
                }else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void observeError(){
        if (loginViewModel.getLoginError().hasActiveObservers())
            return;
        loginViewModel.getLoginError()
                .observe(lifecycleOwner, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String error) {
                        login.setEnabled(true);
                        Toast.makeText(LoginActivity.this, "error: "+error, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean validateInput(){
        boolean pass=true;
        if (TextUtils.isEmpty(email_IL.getEditText().getText())){
            pass=false;
            email_IL.setError("required");
        }else{
            email_IL.setError(null);
        }
        if (TextUtils.isEmpty(password_IL.getEditText().getText())){
            pass=false;
            password_IL.setError("required");
        }else{
            password_IL.setError(null);
        }
        return pass;
    }
}

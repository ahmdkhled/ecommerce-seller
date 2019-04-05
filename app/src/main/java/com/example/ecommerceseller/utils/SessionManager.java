package com.example.ecommerceseller.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static SessionManager sessionManager;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String USERID_KEY ="user_id";
    private static final String USERNAME_KEY="user_name";
    private static final String EMAIL_KEY="user_email";
    private static final String MARKETID_KEY="market_id";

    public static SessionManager getInstance(Context context){
        if (sessionManager==null)
            sessionManager=new SessionManager(context);
        return sessionManager;
    }

    private  SessionManager(Context context) {
        sharedPreferences=context.getSharedPreferences("prefs",Context.MODE_PRIVATE);
    }

    public void saveUserSession(int userId,String name,String email){
        editor=sharedPreferences.edit();
        editor.putInt(USERID_KEY,userId);
        editor.putString(USERNAME_KEY,name);
        editor.putString(EMAIL_KEY,email);
        editor.apply();
    }

    public void saveMarketId(int marketId){
        editor=sharedPreferences.edit();
        editor.putInt(MARKETID_KEY,marketId);
        editor.apply();
    }

    public boolean sessionExist(){
        long userId=sharedPreferences.getInt(USERID_KEY,-1);
        return userId > -1;
    }
    public long getId(){
        return sharedPreferences.getInt(USERID_KEY,-1);
    }

    public String getUserName(){
        return sharedPreferences.getString(USERNAME_KEY,null);
    }
    public String getEmail(){
        return sharedPreferences.getString(EMAIL_KEY,null);
    }

    public int getMarketId(){
        return sharedPreferences.getInt(USERID_KEY,1);

    }


    public void logOut(){
        editor=sharedPreferences.edit();
        editor.putInt(USERID_KEY,-1);
        editor.putInt(MARKETID_KEY,-1);
        editor.putString(USERNAME_KEY,null);
        editor.putString(EMAIL_KEY,null);
        editor.apply();
    }
}

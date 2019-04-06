package com.example.ecommerceseller.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("user_id")
    private String userId;
    @SerializedName("user_name")
    private String userName;
    @SerializedName("user_email")
    private String userEmail;
    @SerializedName("user_status")
    private String userStatus;
    @SerializedName("market_id")
    private String marketId;
    @SerializedName("market_name")
    private String marketName;
    @SerializedName("error")
    private Boolean error;
    @SerializedName("message")
    private String message;


    public LoginResponse(String userId, String userName, String userEmail,
                         String userStatus, String marketId, String marketName,
                         Boolean error, String message) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userStatus = userStatus;
        this.marketId = marketId;
        this.marketName = marketName;
        this.error = error;
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public String getMarketId() {
        return marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}

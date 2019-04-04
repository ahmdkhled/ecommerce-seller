package com.example.ecommerceseller.model;

public class ProductResponse {

    private boolean error;
    private String message;

    public ProductResponse(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}

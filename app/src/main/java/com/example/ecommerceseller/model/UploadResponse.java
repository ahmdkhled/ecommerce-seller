package com.example.ecommerceseller.model;

public class UploadResponse {

    private boolean error;
    private String message;

    public UploadResponse(boolean error, String message) {
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

package com.example.billingshared.dto;

public enum Status {
    PENDING("pending"),
    SUCCESS("success");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

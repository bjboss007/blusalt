package com.example.billing.dto;

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

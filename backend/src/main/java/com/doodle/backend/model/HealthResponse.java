package com.doodle.backend.model;

public class HealthResponse {

    private String status;

    public HealthResponse() {
    }

    public HealthResponse(final String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}

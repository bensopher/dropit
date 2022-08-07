package com.dropit.enums;

public enum DeliveryStatus {
    WAITING_FOR_DELIVERY("WAITING_FOR_DELIVERY"),
    ON_ROUTE("ON ROUTE"),
    COMPLETED("COMPLETED"),
    CANCELED("CANCELED");

    private String status;

    DeliveryStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

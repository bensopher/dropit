package com.dropit.model;

import com.dropit.enums.DeliveryStatus;

public class Delivery {
    private Long id;
    private DeliveryStatus status;
    private Timeslot timeslot;

    public Delivery(DeliveryStatus status, Timeslot timeslot) {
        this.status = status;
        this.timeslot = timeslot;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public Long getId() {
        return id;
    }
}

package com.dropit.model;

import com.dropit.DTO.TimeslotDTO;
import com.dropit.enums.DeliveryStatus;

public class Delivery {
    private Long id;
    private DeliveryStatus status;
    private TimeslotDTO timeslot;

    public Delivery(){}

    public Delivery(Long id, DeliveryStatus status, TimeslotDTO timeslot) {
        this.id = id;
        this.status = status;
        this.timeslot = timeslot;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public TimeslotDTO getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeslotDTO timeslot) {
        this.timeslot = timeslot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

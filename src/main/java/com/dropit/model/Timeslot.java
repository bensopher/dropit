package com.dropit.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalTime;
import java.util.List;

@JsonPropertyOrder({"id, startTime, endTime, supportedAddresses"})
public class Timeslot {
    private Long id;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime startTime;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime endTime;
    private List<Address> supportedAddresses;

    public Timeslot(){}

    public Timeslot(LocalTime startTime, LocalTime endTime, List<Address> supportedAddresses) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.supportedAddresses = supportedAddresses;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public List<Address> getSupportedAddresses() {
        return supportedAddresses;
    }

    public void setSupportedAddresses(List<Address> supportedAddresses) {
        this.supportedAddresses = supportedAddresses;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

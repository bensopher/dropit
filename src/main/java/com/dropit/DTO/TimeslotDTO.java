package com.dropit.DTO;

import com.dropit.model.Timeslot;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
@Component
public class TimeslotDTO {
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;

    public TimeslotDTO(){}

    public TimeslotDTO(Long id, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static TimeslotDTO toDTO(Timeslot timeslot) {
        return new TimeslotDTO(timeslot.getId(), timeslot.getStartTime(), timeslot.getEndTime());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}

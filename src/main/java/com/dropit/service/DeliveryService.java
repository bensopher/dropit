package com.dropit.service;

import com.dropit.DTO.TimeslotDTO;
import com.dropit.enums.DeliveryStatus;
import com.dropit.model.Delivery;
import com.dropit.model.DeliveryRequest;
import com.dropit.model.Timeslot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DeliveryService {
    private static AtomicLong deliveryId = new AtomicLong(1);
    private TimeslotService timeslotService;

    public DeliveryService(TimeslotService timeslotService) {
        this.timeslotService = timeslotService;
    }

    public Delivery bookDelivery(DeliveryRequest deliveryRequest) {
        List<Timeslot> availableTimeslots = timeslotService.getAvailableTimeslots();
        for(Timeslot t : availableTimeslots) {
            if (t.getId().equals(deliveryRequest.timeslotId())) {
                return new Delivery(deliveryId.getAndIncrement(),DeliveryStatus.WAITING_FOR_DELIVERY, TimeslotDTO.toDTO(t));
            }
        }
        return new Delivery();
    }
}

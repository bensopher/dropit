package com.dropit.service;

import com.dropit.DTO.TimeslotDTO;
import com.dropit.enums.DeliveryStatus;
import com.dropit.model.Delivery;
import com.dropit.model.DeliveryRequest;
import com.dropit.model.Timeslot;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DeliveryService {
    private static AtomicLong deliveryId = new AtomicLong(1);

    private final int MAX_DELIVERY_PER_TIMESLOT = 2;
    private final int MAX_DELIVERY_PER_DAY = 10;
    private TimeslotService timeslotService;

    private List<Delivery> allDeliveries;

    public DeliveryService(TimeslotService timeslotService) {
        this.timeslotService = timeslotService;
    }

    public Delivery bookDelivery(DeliveryRequest deliveryRequest) {
        List<Timeslot> availableTimeslots = timeslotService.getAvailableTimeslots();
        for (Timeslot t : availableTimeslots) {
            if (t.getId().equals(deliveryRequest.timeslotId())) {
                if (allDeliveries == null) {
                    allDeliveries = new ArrayList<>();
                }
                if (allDeliveries.size() == MAX_DELIVERY_PER_DAY) {
                    throw new RuntimeException("Day has reached maximum deliveries amount!");
                }
                List<Delivery> deliveriesWithTimeslotId = allDeliveries.stream().filter(delivery -> delivery.getTimeslot().getId() == t.getId()).toList();
                if (deliveriesWithTimeslotId.size() == MAX_DELIVERY_PER_TIMESLOT) {
                    throw new RuntimeException("Timeslot already has 2 deliveries!");
                } else {
                    Delivery newDelivery = new Delivery(deliveryId.getAndIncrement(), DeliveryStatus.WAITING_FOR_DELIVERY, TimeslotDTO.toDTO(t));
                    allDeliveries.add(newDelivery);
                    return newDelivery;
                }
            }
        }
        return new Delivery();
    }

    public List<Delivery> getAllDeliveries() {
        return this.allDeliveries;
    }
}

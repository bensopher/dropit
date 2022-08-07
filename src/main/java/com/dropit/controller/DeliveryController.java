package com.dropit.controller;

import com.dropit.DTO.TimeslotDTO;
import com.dropit.model.*;
import com.dropit.service.AddressService;
import com.dropit.service.DeliveryService;
import com.dropit.service.TimeslotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/delivery")
public class DeliveryController {

    private final AddressService addressService;
    private final TimeslotService timeslotService;

    private final DeliveryService deliveryService;

    public DeliveryController(AddressService addressService, TimeslotService timeslotService, DeliveryService deliveryService) {
        this.addressService = addressService;
        this.timeslotService = timeslotService;
        this.deliveryService = deliveryService;
    }

    @PostMapping(path = "/resolve-address")
    public ResponseEntity<? extends Object> resolveAddress(@RequestBody AddressRequest address) {
        Address resolvedAddress;
        try {
            resolvedAddress = addressService.resolveAddress(address);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resolvedAddress, HttpStatus.CREATED);
    }

    @PostMapping(path = "/timeslots")
    public ResponseEntity<? extends Object> getAvailableTimeslots(@RequestBody TimeslotRequest formattedAddress) {
        List<TimeslotDTO> availableTimeslots;
        try {
            availableTimeslots = timeslotService.getAvailableTimeslots(formattedAddress.address());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(availableTimeslots, HttpStatus.OK);
    }

    @PostMapping(path = "/deliveries")
    public ResponseEntity<? extends Object> bookDelivery(@RequestBody DeliveryRequest deliveryRequest) {
        Delivery delivery;
        try {
            delivery = deliveryService.bookDelivery(deliveryRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(delivery, HttpStatus.CREATED);
    }
}

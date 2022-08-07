package com.dropit.controller;

import com.dropit.model.Address;
import com.dropit.model.AddressRequest;
import com.dropit.model.Timeslot;
import com.dropit.model.TimeslotRequest;
import com.dropit.service.AddressService;
import com.dropit.service.TimeslotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/delivery")
public class DeliveryController {

    private final AddressService addressService;
    private final TimeslotService timeslotService;

    public DeliveryController(AddressService addressService, TimeslotService timeslotService) {
        this.addressService = addressService;
        this.timeslotService = timeslotService;
    }

    @PostMapping(path = "/resolve-address")
    public ResponseEntity<? extends Object> resolveAddress(@RequestBody AddressRequest address) {
        Address resolvedAddress = new Address();
        try {
            resolvedAddress = addressService.resolveAddress(address);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resolvedAddress, HttpStatus.CREATED);
    }

    @PostMapping(path = "/timeslots")
    public ResponseEntity<? extends Object> getAvailableTimeslots(@RequestBody TimeslotRequest formattedAddress) {
        List<Timeslot> availableTimeslots = new ArrayList<>();
        try {
            availableTimeslots = timeslotService.getAvailableTimeslots(formattedAddress.address());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(availableTimeslots, HttpStatus.OK);
    }

    @PostMapping(path = "/deliveries")
    public ResponseEntity<? extends Object> bookDelivery(@RequestBody AddressRequest address) {
        Address resolvedAddress = new Address();
        try {
            resolvedAddress = addressService.resolveAddress(address);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resolvedAddress, HttpStatus.CREATED);
    }
}

package com.dropit.service;

import com.dropit.model.Address;
import com.dropit.model.Timeslot;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TimeslotService implements InitializingBean {

    private static final ObjectMapper mapper = new ObjectMapper();

    private List<Timeslot> availableTimeslots;

    public List<Timeslot> getAvailableTimeslots(Address formattedAddress) {
        if (formattedAddress == null || isNotValid(formattedAddress)) throw new NullPointerException("request body is invalid!");
        List<Timeslot> results = new ArrayList<>();
        for(Timeslot timeslot : availableTimeslots) {
            List<Address> supportedAddresses = timeslot.getSupportedAddresses();
            for(Address address : supportedAddresses) {
                if (address.equals(formattedAddress)) {
                    results.add(timeslot);
                }
            }
        }
        return results;
    }

    private void init() {
        try {
            ClassPathResource resource = new ClassPathResource("static/courierAPI.json");
            InputStream is = resource.getInputStream();
            Map<String, Object> parsedJSON = mapper.readValue(is, Map.class);
            ArrayList<Timeslot> timeslots = (ArrayList<Timeslot>)parsedJSON.get("timeslots");
            availableTimeslots = new ArrayList<>();
            for(Object timeslot: timeslots) {
                availableTimeslots.add(mapper.convertValue(timeslot, Timeslot.class));
            }
        } catch (IOException | ClassCastException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    public boolean isNotValid(Address formattedAddress) {
        return formattedAddress.getCountry() == null
                || formattedAddress.getStreet() == null
                || formattedAddress.getPostcode() == null
                || formattedAddress.getLine1() == null
                || formattedAddress.getLine2() == null;
    }
}

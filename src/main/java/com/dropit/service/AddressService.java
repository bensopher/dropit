package com.dropit.service;

import com.dropit.model.Address;
import com.dropit.model.AddressRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressService {
    @Value("${api.key}")
    private String API_KEY;

    private static final ObjectMapper mapper = new ObjectMapper();

    public Address resolveAddress(AddressRequest address) throws IOException, InterruptedException {
        if (address.searchTerm() == null) throw new NullPointerException("request body is invalid!");
        String queryParams = UriUtils.encodeQuery(address.searchTerm(), StandardCharsets.UTF_8);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.geoapify.com/v1/geocode/search?text=" + queryParams + "&format=json&apiKey=" + API_KEY))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Map<String, Object> results = mapper.readValue(response.body(), HashMap.class);
        return createResolvedAddress(getResultsHashMap(results));
    }

    private LinkedHashMap getResultsHashMap(Map<String, Object> results) {
        try {
            List results1 = (List) results.get("results");
            return (LinkedHashMap) results1.get(0);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new LinkedHashMap<String,String>();
    }

    private Address createResolvedAddress(LinkedHashMap<String, String> resultsMap) {
        String street = resultsMap.get("street");
        String line1 = resultsMap.get("address_line1");
        String line2 = resultsMap.get("address_line2");
        String country = resultsMap.get("country");
        String postcode = resultsMap.get("postcode");
        return new Address(street, line1, line2, country, postcode);
    }
}

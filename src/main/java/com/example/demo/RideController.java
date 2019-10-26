package com.example.demo;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RideController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/rides")
    public Ride greeting(@RequestParam(value="pickupLat", defaultValue="1.0") float pickupLat,
        @RequestParam(value="pickupLong", defaultValue="1.0") float pickupLong,
        @RequestParam(value="dropoffLat", defaultValue="2.0") float dropoffLat,
        @RequestParam(value="dropoffLong", defaultValue="2.0") float dropoffLong,
        @RequestParam(value="numPassengers", defaultValue="2") int numPassengers) {
        String[] suppliers = new String[] {"dave", "eric", "jeff"};
        ArrayList<String> optionsList = Part1.getSupplierOptions(suppliers,
            pickupLat,pickupLong,dropoffLat,dropoffLong,numPassengers);
        Object[] optionsArray = optionsList.toArray();
        return new Ride(counter.incrementAndGet(), optionsArray);
    }

}

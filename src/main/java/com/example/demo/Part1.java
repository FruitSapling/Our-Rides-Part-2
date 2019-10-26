package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

class RideOption {
  @JsonProperty("car_type")
  String type;
  @JsonProperty("price")
  int price;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class RideOptionWithSupplier {
  @JsonProperty("supplier_id")
  String supplier_id;
  @JsonProperty("options")
  RideOption[] options;
}

public class Part1 {

  public static String getJSONResponse(String supplier, float pickupLat,
      float pickupLong, float dropoffLat,
      float dropoffLong, int numberOfPassengers) {
    StringBuffer content = new StringBuffer();
    try {
      String urlString = String.format("https://techtest.rideways.com/%s/?pickup=%s,%s&dropoff=%s,%s",
          supplier, pickupLat, pickupLong, dropoffLat, dropoffLong);

      URL url = new URL(urlString);

      try {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // Set the Timeout to 2000ms - if a supplier API takes longer, then time out
        con.setConnectTimeout(2000);

        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
        String inputLine;
        content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
          content.append(inputLine);
        }
        in.close();
        con.disconnect();

      } catch (java.io.IOException IOException) {
        //TODO: handle this
        System.out.println("The API request for " + supplier + " failed. The supplier API may be broken.");
        return null;
      }

    } catch(java.net.MalformedURLException e) {
      System.out.println("Please change the URL called for the API request.");
      System.exit(0);
    }

    return content.toString();
  }

  public static ArrayList<String> getSupplierOptions(String[] suppliers,
      float pickupLat, float pickupLong, float dropoffLat,
      float dropoffLong, int numberOfPassengers) {

    ObjectMapper objectMapper = new ObjectMapper();

    ArrayList<String> options = new ArrayList<String>();

    for (String supplier: suppliers) {
      String JSONResponse = getJSONResponse(supplier, pickupLat, pickupLong, dropoffLat,
          dropoffLong, numberOfPassengers);

      if (JSONResponse == null) {
        continue;
      }

      RideOptionWithSupplier rideOptionWithSupplier = new RideOptionWithSupplier();

      try {
        rideOptionWithSupplier = objectMapper.readValue(JSONResponse, RideOptionWithSupplier.class);
      } catch(Exception e) {
        e.printStackTrace();
      }

        for (RideOption r: rideOptionWithSupplier.options) {

        // Only show the option if the car type can fit this number of passengers
        if (numberOfPassengers <= passengersForType(r.type)) {
          options.add( r.type + " - " + rideOptionWithSupplier.supplier_id + " - " + r.price);
        }
      }
    }
    return options;
  }

  public static int passengersForType(String type) {
    int numPassengers = 0;

    switch(type){
      case "STANDARD":
        numPassengers = 4;
        break;
      case "EXECUTIVE":
        numPassengers = 4;
        break;
      case "LUXURY":
        numPassengers = 4;
        break;
      case "PEOPLE_CARRIER":
        numPassengers = 6;
        break;
      case "LUXURY_PEOPLE_CARRIER":
        numPassengers = 6;
        break;
      case "MINIBUS":
        numPassengers = 16;
        break;
    }

    return numPassengers;
  }


  public static void main(String[] args) {

    for (String s: args) {
      System.out.println(s);
    }

    try {
      String suppliersToCheck = args[0];
      float pickupLat = Float.parseFloat(args[1]);
      float pickupLong = Float.parseFloat(args[2]);
      float dropoffLat = Float.parseFloat(args[3]);
      float dropoffLong = Float.parseFloat(args[4]);
      int numPassengers = Integer.parseInt(args[5]);

      String[] suppliers = new String[] {"dave"};

      ArrayList<String> options;

      if (suppliersToCheck.equals("dave")) {
         options = Part1.getSupplierOptions(suppliers, pickupLat, pickupLong,
            dropoffLat, dropoffLong, 1);
      } else {
        suppliers = new String[] {"dave", "eric", "jeff"};
        options = getSupplierOptions(suppliers, pickupLat, pickupLong,
            dropoffLat, dropoffLong, numPassengers);
      }

      for (String option: options) {
        System.out.println(option);
      }

    } catch(Exception e) {
      System.out.println("Please check the parameters you entered and try again.");
      System.exit(0);
    }
  }
}


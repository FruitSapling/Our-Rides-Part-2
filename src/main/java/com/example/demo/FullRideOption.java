package com.example.demo;

public class FullRideOption {
  private final int numPassengers;
  private final float pickupLong, pickupLat, dropoffLong, dropoffLat;

  public FullRideOption(float pickupLong, float pickupLat , float dropoffLong, float dropoffLat,
      int numPassengers) {
    this.numPassengers = numPassengers;
    this.pickupLong = pickupLong;
    this.pickupLat = pickupLat;
    this.dropoffLong = dropoffLong;
    this.dropoffLat = dropoffLat;
  }

  public float getPickupLong() {
    return pickupLong;
  }

  public float getPickupLat() {
    return pickupLat;
  }

  public float getDropoffLong() {
    return dropoffLong;
  }

  public float getDropoffLat() {
    return dropoffLat;
  }

  public int getNumPassengers() {
    return numPassengers;
  }



}

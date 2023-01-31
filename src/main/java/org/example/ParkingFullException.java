package org.example;

public class ParkingFullException extends Throwable {
    public ParkingFullException(VEHICLE_TYPE vehicleType) {
        super(String.format("No %s parking is space available.", vehicleType.toString()));
    }
}

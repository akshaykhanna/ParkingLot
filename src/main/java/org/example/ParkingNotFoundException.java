package org.example;

public class ParkingNotFoundException extends Throwable {
    public ParkingNotFoundException(VEHICLE_TYPE type) {
        super(String.format("Unable to find to %s parking.", type));
    }
}

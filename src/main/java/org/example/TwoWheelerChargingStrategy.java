package org.example;

public class TwoWheelerChargingStrategy implements ParkingChargingStrategy {
    @Override
    public double calculateCharges(int parkedHrs) {
        if(parkedHrs < 1)
            return 10;
        return parkedHrs * 10;
    }
}

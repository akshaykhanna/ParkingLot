package org.example;

public class FourWheelerChargingStrategy implements ParkingChargingStrategy {
    @Override
    public double calculateCharges(int parkedHrs) {
        if(parkedHrs < 1)
            return 30;
        return parkedHrs * 30;
    }
}

package org.example;

public class Vehicle {
    private String regNumber;
    private String ownerName;
    private VEHICLE_TYPE type;

    public Vehicle(String regNumber, String ownerName, VEHICLE_TYPE type) {
        this.regNumber = regNumber;
        this.ownerName = ownerName;
        this.type = type;
    }

    public String vehicleNo() {
        return this.regNumber;
    }

    public VEHICLE_TYPE type() {
        return this.type;
    }

    public String owner() {
        return this.ownerName;
    }
}

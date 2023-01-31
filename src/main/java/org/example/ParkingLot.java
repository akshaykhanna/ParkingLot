package org.example;

import java.util.Date;

public class ParkingLot {


    private Slot[] fourWheelerSlots;
    private Slot[] twoWheelerSlots;

    public ParkingLot(int noOfTwoWheelerSlots, int noOfFourWheelerSlots) {
        twoWheelerSlots = new Slot[noOfTwoWheelerSlots];
        fourWheelerSlots = new Slot[noOfFourWheelerSlots];
        this.createEmptySlots(twoWheelerSlots);
        this.createEmptySlots(fourWheelerSlots);
    }

    private void createEmptySlots(Slot[] slots) {
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new Slot();
        }
    }

    public Ticket parkAVehicle(Vehicle vehicle) throws ParkingFullException {
        var nextAvailableSlot = this.getNextAvailableSlot(vehicle.type());
        nextAvailableSlot.setParkedVehicle(vehicle);
        return generateTicket(vehicle, nextAvailableSlot);
    }

    private Ticket generateTicket(Vehicle vehicle, Slot nextAvailableSlot) {
        return new Ticket(nextAvailableSlot.slotNo(), vehicle.vehicleNo(), vehicle.type(), new Date());
    }

    private Slot getNextAvailableSlot(VEHICLE_TYPE type) throws ParkingFullException {
        if (type == VEHICLE_TYPE.FOUR_WHEELER) return this.getNextAviableFourWheelerSlot();
        return this.getNextAvailableTwoWheelerSlot();
    }

    private Slot getNextAvailableTwoWheelerSlot() throws ParkingFullException {
        return getAvailableSlot(this.twoWheelerSlots, VEHICLE_TYPE.TWO_WHEELER);
    }

    private Slot getNextAviableFourWheelerSlot() throws ParkingFullException {
        return getAvailableSlot(this.fourWheelerSlots, VEHICLE_TYPE.FOUR_WHEELER);

    }

    private Slot getAvailableSlot(Slot[] listOfSlots, VEHICLE_TYPE type) throws ParkingFullException {
        for (var slot : listOfSlots) {
            if (slot.isEmpty()) return slot;
        }
        throw new ParkingFullException(type);
    }


    public VehicleAndChargesToBePaid unPark(Ticket ticket, ParkingChargingStrategy parkingChargingStrategy) throws ParkingNotFoundException {
        var parkedVehicleSlot = this.getParkedVehicleSlot(ticket);
        Vehicle vehicle = parkedVehicleSlot.getParkedVehicle();
        parkedVehicleSlot.vacateSlot();
        int parkedHours = this.getParkedHours(ticket.timeStamp(), new Date());
        return new VehicleAndChargesToBePaid() {
            public Vehicle getParkedVehicle() {
                return vehicle;
            }

            @Override
            public double getCharges() {
                return parkingChargingStrategy.calculateCharges(parkedHours);
            }
        };
    }

    private int getParkedHours(Date startDate, Date endDate) {
        int MILLISECOND_IN_A_SECOND = 1000;
        int SECONDS_IN_A_HOUR = 3600;
        long secondsSpent = (endDate.getTime() - startDate.getTime()) / MILLISECOND_IN_A_SECOND;
        return (int) (secondsSpent / SECONDS_IN_A_HOUR);
    }

    private Slot getParkedVehicleSlot(Ticket ticket) throws ParkingNotFoundException {
        if (ticket.vehicleType() == VEHICLE_TYPE.FOUR_WHEELER) return this.getParkedFourWheeler(ticket);
        return this.getParkedTwoWheeler(ticket);
    }

    private Slot getParkedTwoWheeler(Ticket ticket) throws ParkingNotFoundException {
        return this.getSlot(this.twoWheelerSlots, ticket, VEHICLE_TYPE.TWO_WHEELER);
    }

    private Slot getParkedFourWheeler(Ticket ticket) throws ParkingNotFoundException {
        return this.getSlot(this.fourWheelerSlots, ticket, VEHICLE_TYPE.FOUR_WHEELER);
    }

    private Slot getSlot(Slot[] slots, Ticket ticket, VEHICLE_TYPE type) throws ParkingNotFoundException {
        for (var slot : slots) {
            if (slot.slotNo() == ticket.slotNo() && slot.getParkedVehicle().vehicleNo() == ticket.vehicleNo())
                return slot;
        }
        throw new ParkingNotFoundException(type);
    }

}

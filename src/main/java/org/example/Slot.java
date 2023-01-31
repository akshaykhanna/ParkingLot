package org.example;

public class Slot {
    static long slotCounter = 1;
    long slotNo;
    boolean isEmpty;
    Vehicle parkedVehicle;

    public Slot() {
        this.slotNo = slotCounter++;
        this.isEmpty = true;
        this.parkedVehicle = null;
    }


    public long getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(long slotNo) {
        this.slotNo = slotNo;
    }

    public boolean isEmpty() {
        return isEmpty;
    }


    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
        this.isEmpty = false;
    }

    public long slotNo() {
        return this.slotNo;
    }

    public void vacateSlot() {
        this.isEmpty = true;
        this.parkedVehicle = null;
    }
}

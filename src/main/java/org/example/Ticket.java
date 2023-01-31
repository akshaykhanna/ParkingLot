package org.example;

import java.util.Date;

public class Ticket {
    private final boolean isPaid;
    private String vehicleNo;
    private long ticketNo = 0;
    private static long ticketCounter = 1;
    private long slotNo = 0;
    private Date timeStamp;
    private VEHICLE_TYPE type;

    public VEHICLE_TYPE vehicleType() {
        return type;
    }

    public Ticket(long slotNo, String vehicleNo, VEHICLE_TYPE type, Date timeStamp) {
        this.type = type;
        this.ticketNo = Ticket.ticketCounter++;
        this.slotNo = slotNo;
        this.vehicleNo = vehicleNo;
        this.timeStamp = timeStamp;
        this.isPaid = false;
    }


    public String vehicleNo() {
        return this.vehicleNo;
    }

    public long ticketNo() {
        return this.ticketNo;
    }

    public long slotNo() {
        return this.slotNo;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public Date timeStamp() {
        return timeStamp;
    }
}

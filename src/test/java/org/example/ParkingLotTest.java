package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

class ParkingLotTest {

    private ParkingLot parkingLot;
    private Ticket ticket;
    private Ticket ticketForMiniCooper;
    private Vehicle fiatPunto;
    private Vehicle miniCooper;
    private Vehicle astonMartin;
    private Vehicle vespa;
    private FourWheelerChargingStrategy fourWheelerChargingStrategy;
    private TwoWheelerChargingStrategy twoWheelerChargingStrategy;

    @BeforeEach
    void setUp() throws ParkingFullException {
        fiatPunto = new Vehicle("MH-7221", "Edd", VEHICLE_TYPE.FOUR_WHEELER);
        miniCooper = new Vehicle("MH-7222", "Eddy", VEHICLE_TYPE.FOUR_WHEELER);
        astonMartin = new Vehicle("MH-7223", "Goofy", VEHICLE_TYPE.FOUR_WHEELER);
        vespa = new Vehicle("MH-7224", "Jasmine", VEHICLE_TYPE.TWO_WHEELER);
        parkingLot = new ParkingLot(0, 2);
        ticket = parkingLot.parkAVehicle(fiatPunto);
        ticketForMiniCooper = parkingLot.parkAVehicle(miniCooper);
        fourWheelerChargingStrategy = new FourWheelerChargingStrategy();
        twoWheelerChargingStrategy = new TwoWheelerChargingStrategy();
    }

    @Test
    void shouldAbleToParkAVehicle() throws ParkingFullException {

        Assertions.assertNotNull(ticket);
        Assertions.assertNotNull(ticket.ticketNo());
        Assertions.assertNotNull(ticket.slotNo());
        Assertions.assertNotNull(ticket.timeStamp());
        Assertions.assertEquals(fiatPunto.vehicleNo(), ticket.vehicleNo());
        Assertions.assertEquals(miniCooper.vehicleNo(), ticketForMiniCooper.vehicleNo());
    }

    @Test
    void shouldNotAbleToParkAVehicleWhenNoSlotIsNotAvailable() throws ParkingFullException {

        var exceptionThrown = Assertions.assertThrows(ParkingFullException.class, () -> {
            parkingLot.parkAVehicle(astonMartin);
        });

        Assertions.assertEquals("No Four Wheeler parking is space available.", exceptionThrown.getMessage());

    }

    @Test
    void shouldNotAbleToParkAnyTwoWheelerVehicle() throws ParkingFullException {

        var exceptionThrown = Assertions.assertThrows(ParkingFullException.class, () -> {
            parkingLot.parkAVehicle(vespa);
        });

        Assertions.assertEquals("No Two Wheeler parking is space available.", exceptionThrown.getMessage());
    }

    @Test
    void shouldBeAbleUnParkVehicleUsingItsParkingTicket() throws ParkingNotFoundException {
        VehicleAndChargesToBePaid vehicleAndChargesToBePaid = parkingLot.unPark(ticketForMiniCooper, fourWheelerChargingStrategy);

        Assertions.assertEquals(miniCooper.vehicleNo(), vehicleAndChargesToBePaid.getParkedVehicle().vehicleNo());
        Assertions.assertEquals(miniCooper.owner(), vehicleAndChargesToBePaid.getParkedVehicle().owner());
        Assertions.assertEquals(miniCooper.type(), vehicleAndChargesToBePaid.getParkedVehicle().type());
        Assertions.assertEquals(30, vehicleAndChargesToBePaid.getCharges());
    }

    @Test
    void shouldNotBeAbleUnParkVehicleWhichIsNotParked() throws ParkingNotFoundException {

        var exceptionThrown = Assertions.assertThrows(ParkingNotFoundException.class, () -> {
            parkingLot.unPark(new Ticket(1, astonMartin.vehicleNo(), astonMartin.type(), new Date()), fourWheelerChargingStrategy);
        });

        Assertions.assertEquals("Unable to find to Four Wheeler parking.", exceptionThrown.getMessage());
    }

    @Test
    void shouldNotBeAbleUnParkTwoWheelerVehicleAsNotTwoWheelerIsParked() throws ParkingNotFoundException {

        var exceptionThrown = Assertions.assertThrows(ParkingNotFoundException.class, () -> {
            parkingLot.unPark(new Ticket(1, vespa.vehicleNo(), vespa.type(), new Date()), fourWheelerChargingStrategy);
        });

        Assertions.assertEquals("Unable to find to Two Wheeler parking.", exceptionThrown.getMessage());
    }
}
# Parking Lot TDD
## Problem
- Design a Parking Lot System using Test Driven Developlment (TDD).
- A vehicle to be parked can be either a two-wheeler or a four-wheeler.
- When the vehicle is to be parked, the owner will be provided a ticket.
- If there are no parking slots available, then owner should not be allowed to park.
- When the vehicle is to be unparked, the owner must provide the ticket.
- The parking charges of the two-wheeler & the four-wheeler would be different.

<img width="1000" height="400" alt="image" src="https://user-images.githubusercontent.com/12964174/210460262-888e1289-d949-41d8-870d-4fbb7aa28b29.png">

## Thinking Solution
    - Functional Requirement
        - Park Vehicles
            - Two / Four
            - Ticket
                - ticketNo
                - slotNo
                - Timestamp
            - Prevent parking when no free slots 
        - Unpark Vehicle
            - Ticket
            - Check for validity
            - Calculate charges
    - Solution
        - Class / Modals / Actor
            - Enum Vehicle Types {Two, Four}
            - Vehicle
                - vehicleNo
                - vehicleType/Size
                - ownerName
            - Slot 
                - slotNo
                - isEmpty/isVacant
                - parkedVehicle
                - parkAVehicle()
                - vacateAVehicle()
            - Ticket
                - ticketNo
                - slotNo
                - Timestamp
                - vehicleNo
        - Services / Logical Classes
            - Parking 
                - parkAVehicle()
                    - Check for availability
                    - Park a vecihle 
                    - Generate ticket
                - unParkVehicle()
                    - Check wether Vehicle is parked on that slot (Ticket)
                    - Calculate charges
                    - Return Vehicle & free slot





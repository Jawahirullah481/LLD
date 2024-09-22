package parkinglot.commands;

import parkinglot.enums.SlotStatus;
import parkinglot.enums.VehicleType;
import parkinglot.exception.InvalidCommandException;
import parkinglot.exception.NoAvailableSlotException;
import parkinglot.models.Floor;
import parkinglot.models.Slot;
import parkinglot.models.Ticket;
import parkinglot.models.Vehicle;
import parkinglot.services.ParkingSystem;
import parkinglot.services.TicketManager;

import java.util.List;

public class ParkCommandExecutor extends CommandExecutor {

    public ParkCommandExecutor(String command) {
        super(command);
    }

    public void executeCommand() {
        try {
            VehicleType vehicleType = VehicleType.getVehicleType(command.split(" ")[1]);
            String vehicleNo = command.split(" ")[2];
            String vehicleColor = command.split(" ")[3];

            Vehicle vehicle = new Vehicle(vehicleType, vehicleNo, vehicleColor);
            ParkingSystem parkingSystem = ParkingSystem.getParkingSystem();

            Slot freeSlot = findFreeSlot(parkingSystem, vehicle);

            if(freeSlot == null) {
                throw new NoAvailableSlotException("Parking Lot Full");
            }

            TicketManager ticketManager = TicketManager.getTicketManager();
            Ticket ticket = ticketManager.createTicket(vehicle, freeSlot.getFloor(), freeSlot.getSlotNo());
            boolean isParked = freeSlot.park(vehicle);
            if(isParked) {
                System.out.println("Parked Vehicle. Ticket ID : " + ticket.getTicketNo());
            }

        } catch(ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid park vehicle command");
        }
    }

    private Slot findFreeSlot(ParkingSystem parkingSystem, Vehicle vehicle) {
        for(Floor floor : parkingSystem.getFloors()) {
            Slot freeSlot = floor.getNextFreeSlot(vehicle.getType());
            if(freeSlot != null) {
                return freeSlot;
            }
        }
        return null;
    }
}

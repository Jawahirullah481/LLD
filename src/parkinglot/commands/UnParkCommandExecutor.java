package parkinglot.commands;

import parkinglot.models.Floor;
import parkinglot.models.Slot;
import parkinglot.models.Ticket;
import parkinglot.services.ParkingSystem;
import parkinglot.services.TicketManager;

public class UnParkCommandExecutor extends CommandExecutor {

    public UnParkCommandExecutor(String command) {
        super(command);
    }

    public void executeCommand() {
        String vehicleNo = command.split(" ")[1];
        TicketManager ticketmanager = TicketManager.getTicketManager();
        Ticket ticket = ticketmanager.getTicket(vehicleNo);
        Floor parkedFloor = ParkingSystem.getParkingSystem().getFloors().get(ticket.getFloorNo() - 1);
        parkedFloor.unpark(ticket);
        ticket.setTicketClosed();
    }
}

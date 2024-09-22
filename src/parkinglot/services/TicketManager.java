package parkinglot.services;

import parkinglot.exception.InvalidTicketException;
import parkinglot.models.Floor;
import parkinglot.models.Slot;
import parkinglot.models.Ticket;
import parkinglot.models.Vehicle;

import java.util.*;

public class TicketManager {
    private int ticketNo = 1;
    private static TicketManager ticketManager;
    private List<Ticket> tickets;

    private TicketManager() {
        tickets = new ArrayList<>();
    }

    public static TicketManager getTicketManager() {
        if(ticketManager == null) {
            synchronized (TicketManager.class) {
                if(ticketManager == null) {
                    ticketManager = new TicketManager();
                    return ticketManager;
                }
            }
        }
        return ticketManager;
    }

    public Ticket createTicket(Vehicle vehicle, int floorNo, int slotNo) {
        Ticket ticket = new Ticket(ticketNo++, floorNo, slotNo, vehicle);
        tickets.add(ticket);
        return ticket;
    }

    public Ticket getTicket(String vehicleNo) {
        Optional<Ticket> ticket = this.tickets.stream().filter(obj -> obj.getVehicle().getRegNo().equals(vehicleNo)).findFirst();
        if(ticket.isEmpty()) {
            throw new InvalidTicketException("Ticket for vehicle " + vehicleNo + " Not available !");
        }
        return ticket.get();
    }
}

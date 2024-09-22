package parkinglot.models;

import parkinglot.enums.TicketStatus;

import java.time.LocalDateTime;

public class Ticket {
    private int floorNo;
    private int slotNo;
    private LocalDateTime parkedTime;
    private Vehicle vehicle;
    private final int ticketNo;
    private TicketStatus ticketStatus;

    public Ticket(int ticketNo, int floorNo, int slotNo, Vehicle vehicle) {
        this.ticketNo = ticketNo;
        this.floorNo = floorNo;
        this.slotNo = slotNo;
        this.vehicle = vehicle;
        this.parkedTime = LocalDateTime.now();
        this.ticketStatus = TicketStatus.AVAILABLE;
    }

    public int getTicketNo() { return ticketNo; }

    public int getFloorNo() {
        return floorNo;
    }

    public int getSlotNo() {
        return slotNo;
    }

    public LocalDateTime getParkedTime() {
        return parkedTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketClosed() {
        this.ticketStatus = TicketStatus.CLOSED;
    }
}

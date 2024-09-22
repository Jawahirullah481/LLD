package parkinglot.models;

import parkinglot.enums.VehicleType;

public class Vehicle {
    private VehicleType type;
    private String regNo;
    private String color;
    private Ticket ticket;

    public Vehicle(VehicleType type, String regNo, String color) {
        this.type = type;
        this.regNo = regNo;
        this.color = color;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}

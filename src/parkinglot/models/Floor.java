package parkinglot.models;

import parkinglot.configuration.AppConfiguration;
import parkinglot.enums.SlotStatus;
import parkinglot.enums.VehicleType;
import parkinglot.exception.InvalidTicketException;
import parkinglot.exception.NoAvailableSlotException;
import parkinglot.parkingstrategy.ParkingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Floor {
    private List<Slot> slots;
    private int floorNo;
    private int availableSlots;
    private ParkingStrategy parkingStrategy;

    public Floor(int floorNo, ParkingStrategy parkingStrategy) {
        this.floorNo = floorNo;
        this.parkingStrategy = parkingStrategy;
        createSlots();
    }

    private void createSlots() {
        int slotNo = 1;
        this.slots = new ArrayList<>();

        for (int i = 1; i <= AppConfiguration.NO_OF_CAR_SLOTS; i++) {
            this.slots.add(new Slot(slotNo, VehicleType.CAR, this.floorNo));
            slotNo++;
        }
        for (int i = 1; i <= AppConfiguration.NO_OF_BIKE_SLOTS; i++) {
            this.slots.add(new Slot(slotNo, VehicleType.BIKE, this.floorNo));
            slotNo++;
        }
        for (int i = 1; i <= AppConfiguration.NO_OF_TRUCK_SLOTS; i++) {
            this.slots.add(new Slot(slotNo, VehicleType.TRUCK, this.floorNo));
            slotNo++;
        }

        this.availableSlots = AppConfiguration.NO_OF_CAR_SLOTS + AppConfiguration.NO_OF_BIKE_SLOTS + AppConfiguration.NO_OF_TRUCK_SLOTS;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }

    public boolean park(Vehicle vehicle, Ticket ticket) throws NoAvailableSlotException {
        Slot freeSlot = getNextFreeSlot(vehicle.getType());
        if (freeSlot == null) {
            throw new NoAvailableSlotException("No available slots");
        }
        freeSlot.park(vehicle);
        availableSlots--;
        return true;
    }

    public boolean unpark(Ticket ticket) throws InvalidTicketException{
        String vehicleRegNo = ticket.getVehicle().getRegNo();
        Optional<Slot> slot = slots.stream().filter(i -> i.getSlotNo() == ticket.getSlotNo()).findFirst();

        if(slot.isEmpty()) {
            throw new InvalidTicketException("Invalid ticket. Vehicle not present in slot");
        }

        Slot parkedSlot = slot.get();
        return parkedSlot.unPark(ticket.getVehicle().getRegNo());
    }

    public Slot getNextFreeSlot(VehicleType vehicleType) {
        return parkingStrategy.getNextFreeSlot(this, vehicleType);
    }

    public List<Slot> getSlots() {
        return this.slots;
    }
}

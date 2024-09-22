package parkinglot.models;

import parkinglot.enums.SlotStatus;
import parkinglot.enums.VehicleType;
import parkinglot.exception.NoAvailableSlotException;

public class Slot {
    private final int slotNo;
    private SlotStatus status;
    private final VehicleType slotType;
    private String vehicleNo;
    private int floorNo;

    public Slot(int slotNo, VehicleType slotType, int floorNo) {
        this.slotNo = slotNo;
        this.slotType = slotType;
        status = SlotStatus.AVAILABLE;
        this.floorNo = floorNo;
    }

    public int getSlotNo() {
        return this.slotNo;
    }

    public SlotStatus getStatus() {
        return this.status;
    }

    public String getVehicleNo() {
        return this.vehicleNo;
    }

    public boolean park(Vehicle vehicle) {
        this.vehicleNo = vehicle.getRegNo();
        this.status = SlotStatus.OCCUPIED;
        return true;
    }

    public VehicleType getSlotType() {
        return slotType;
    }

    public boolean unPark(String vehicleNo) {
        if(this.status != SlotStatus.OCCUPIED || !this.vehicleNo.equals(vehicleNo)) {
            throw new NoAvailableSlotException("No Vehicle parking in the slot");
        }

        this.vehicleNo = null;
        this.status = SlotStatus.AVAILABLE;
        return true;
    }

    public int getFloor() {
        return floorNo;
    }

}

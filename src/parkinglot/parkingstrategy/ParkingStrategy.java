package parkinglot.parkingstrategy;

import parkinglot.enums.VehicleType;
import parkinglot.models.Floor;
import parkinglot.models.Slot;

public interface ParkingStrategy {
    public Slot getNextFreeSlot(Floor floor, VehicleType vehicleType);
}

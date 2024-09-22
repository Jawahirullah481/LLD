package parkinglot.parkingstrategy;

import parkinglot.enums.SlotStatus;
import parkinglot.enums.VehicleType;
import parkinglot.models.Floor;
import parkinglot.models.Slot;

import java.util.List;

public class ParkFromFirstStrategy implements ParkingStrategy {
    public Slot getNextFreeSlot(Floor floor, VehicleType vehicleType) {
        List<Slot> slots = floor.getSlots();
        Slot freeSlot = null;
        for(Slot slot : slots) {
            if(slot.getSlotType() == vehicleType && slot.getStatus() == SlotStatus.AVAILABLE) {
                freeSlot = slot;
                break;
            }
        }
        return freeSlot;
    }
}

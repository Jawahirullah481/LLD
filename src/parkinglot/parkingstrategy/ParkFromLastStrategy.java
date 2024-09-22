package parkinglot.parkingstrategy;

import parkinglot.enums.SlotStatus;
import parkinglot.enums.VehicleType;
import parkinglot.models.Floor;
import parkinglot.models.Slot;

import java.util.List;

public class ParkFromLastStrategy implements ParkingStrategy {

    public Slot getNextFreeSlot(Floor floor, VehicleType vehicleType) {
        List<Slot> slots = floor.getSlots();
        Slot freeSlot = null;
        for(int i = slots.size() - 1; i >= 0; i--) {
            if(slots.get(i).getSlotType() == vehicleType && slots.get(i).getStatus() == SlotStatus.AVAILABLE) {
                freeSlot = slots.get(i);
                break;
            }
        }
        return freeSlot;
    }
}

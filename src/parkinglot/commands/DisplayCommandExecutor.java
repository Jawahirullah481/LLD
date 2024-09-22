package parkinglot.commands;

import parkinglot.enums.SlotStatus;
import parkinglot.enums.VehicleType;
import parkinglot.models.Floor;
import parkinglot.models.Slot;
import parkinglot.services.ParkingSystem;

import java.util.ArrayList;
import java.util.List;

public class DisplayCommandExecutor extends CommandExecutor {

    public DisplayCommandExecutor(String command) {
        super(command);
    }

    public void executeCommand() {
        String cmdType = command.split(" ")[1];

        switch(cmdType) {
            case "free_count": {
                displayCountOfFreeSlots();
            }; break;
            case "free_slots": {
                displayFreeSlots();
            }; break;
            case "occupied_slots ": {
                displayOccupiedSlots();
            }; break;
        }
    }

    private void displayOccupiedSlots() {
        List<Floor> floors = ParkingSystem.getParkingSystem().getFloors();
        for(Floor floor : floors) {
            int carSlotCount = 0;
            int bikeSlotCount = 0;
            int truckSlotcount = 0;

            for(Slot slot : floor.getSlots()) {
                if(slot.getStatus() == SlotStatus.OCCUPIED) {
                    if (slot.getSlotType() == VehicleType.CAR) {
                        carSlotCount++;
                    } else if (slot.getSlotType() == VehicleType.BIKE) {
                        bikeSlotCount++;
                    } else if (slot.getSlotType() == VehicleType.TRUCK) {
                        truckSlotcount++;
                    }
                }
            }

            System.out.println("Occupied slots for CAR on Floor " + floor.getFloorNo() + " : " + carSlotCount);
            System.out.println("Occupied slots for BIKE on Floor " + floor.getFloorNo() + " : " + bikeSlotCount);
            System.out.println("Occupied slots for TRUCK on Floor " + floor.getFloorNo() + " : " + truckSlotcount);
            System.out.println();
        }
    }

    private void displayFreeSlots() {
        List<Floor> floors = ParkingSystem.getParkingSystem().getFloors();
        for (Floor floor : floors) {
            List<Integer> carsSlots = new ArrayList<>();
            List<Integer> bikeSlots = new ArrayList<>();
            List<Integer> truckSlots = new ArrayList<>();

            for (Slot slot : floor.getSlots()) {
                if (slot.getStatus() == SlotStatus.AVAILABLE) {
                    if (slot.getSlotType() == VehicleType.CAR) {
                        carsSlots.add(slot.getSlotNo());
                    } else if (slot.getSlotType() == VehicleType.BIKE) {
                        bikeSlots.add(slot.getSlotNo());
                    } else if (slot.getSlotType() == VehicleType.TRUCK) {
                        truckSlots.add(slot.getSlotNo());
                    }
                }
            }

            switch(VehicleType.getVehicleType((command.split(" ")[2]))) {
                case CAR : {
                    System.out.println("Free slots for CAR on Floor " + floor.getFloorNo() + " : " + carsSlots);
                }; break;
                case BIKE : {
                    System.out.println("Free slots for BIKE on Floor " + floor.getFloorNo() + " : " + bikeSlots);
                }; break;
                case TRUCK : {
                    System.out.println("Free slots for TRUCK on Floor " + floor.getFloorNo() + " : " + truckSlots);
                }; break;
            }
            System.out.println();
        }
    }

    private void displayCountOfFreeSlots() {
        List<Floor> floors = ParkingSystem.getParkingSystem().getFloors();
        for(Floor floor : floors) {
            int carSlotCount = 0;
            int bikeSlotCount = 0;
            int truckSlotcount = 0;

            for(Slot slot : floor.getSlots()) {
                if(slot.getStatus() == SlotStatus.AVAILABLE) {
                    if (slot.getSlotType() == VehicleType.CAR) {
                        carSlotCount++;
                    } else if (slot.getSlotType() == VehicleType.BIKE) {
                        bikeSlotCount++;
                    } else if (slot.getSlotType() == VehicleType.TRUCK) {
                        truckSlotcount++;
                    }
                }
            }

            switch(VehicleType.getVehicleType((command.split(" ")[2]))) {
                case CAR : {
                    System.out.println("No. of free slots for CAR on Floor " + floor.getFloorNo() + " : " + carSlotCount);
                }; break;
                case BIKE : {
                    System.out.println("No. of free slots for BIKE on Floor " + floor.getFloorNo() + " : " + bikeSlotCount);
                }; break;
                case TRUCK : {
                    System.out.println("No. of free slots for truck on Floor " + floor.getFloorNo() + " : " + truckSlotcount);
                }; break;
            }
            System.out.println();
        }
    }
}

package parkinglot.services;

import parkinglot.commands.CommandExecutor;
import parkinglot.commands.CommandExecutorFactory;
import parkinglot.models.Floor;
import parkinglot.models.Slot;
import parkinglot.models.Ticket;
import parkinglot.models.Vehicle;
import parkinglot.parkingstrategy.ParkingStrategy;

import java.util.List;
import java.util.Map;

public class ParkingSystem {
    private List<Floor> floors;
    private Map<Integer, Vehicle> vehicleMap;
    private static ParkingSystem parkingSystem;

    private ParkingSystem() {
        floors = FloorFactory.createFloors();
    }

    public static ParkingSystem getParkingSystem() {
        if (parkingSystem == null) {
            synchronized (ParkingSystem.class) {
                if (parkingSystem == null) {
                    parkingSystem =  new ParkingSystem();
                    return parkingSystem;
                }
            }
        }
        return parkingSystem;
    }

    public void executeCommand(String command) {
        CommandExecutor commandExecutor = CommandExecutorFactory.getCommandExecutor(command);
        commandExecutor.executeCommand();
    }

    public List<Floor> getFloors() {
        return floors;
    }
}

package parkinglot.commands;

import parkinglot.exception.InvalidCommandException;
import parkinglot.services.ParkingSystem;

public class CommandExecutorFactory {
    private static ParkingSystem parkingSystem;

    public static CommandExecutor getCommandExecutor(String command) {

        CommandExecutor commandExecutor = null;
        String str = command.split(" ")[0];

        switch(str) {
            case "display" : {
                commandExecutor = new DisplayCommandExecutor(command);
            }; break;
            case "park_vehicle" : {
                commandExecutor = new ParkCommandExecutor(command);
            }; break;
            case "unpark_vehicle" : {
                commandExecutor = new UnParkCommandExecutor(command);
            } default : {
                throw new InvalidCommandException("Invalid command");
            }
        }

        return commandExecutor;
    }
}

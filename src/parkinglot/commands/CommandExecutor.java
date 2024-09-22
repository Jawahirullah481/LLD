package parkinglot.commands;

import parkinglot.services.ParkingSystem;

public abstract class CommandExecutor {

    protected String command;

    public CommandExecutor(String command) {
        this.command = command;
    }
    public abstract void executeCommand();
}

package parkinglot;

import parkinglot.services.ParkingSystem;
import parkinglot.services.TicketManager;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        ParkingSystem parkingSystem = ParkingSystem.getParkingSystem();

        Scanner get = new Scanner(System.in);
        String command = null;
        while(!(command = get.nextLine()).equals("EXIT")) {
            try {
                parkingSystem.executeCommand(command);
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

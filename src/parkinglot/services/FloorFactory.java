package parkinglot.services;

import parkinglot.configuration.AppConfiguration;
import parkinglot.models.Floor;
import parkinglot.parkingstrategy.ParkFromFirstStrategy;

import java.util.ArrayList;
import java.util.List;

public class FloorFactory {

    public static List<Floor> createFloors() {

        List<Floor> floors = new ArrayList<>();
        for(int i = 1; i <= AppConfiguration.NO_OF_FLOORS; i++) {
            floors.add(new Floor(i, new ParkFromFirstStrategy()));
        }

        return floors;
    }
}

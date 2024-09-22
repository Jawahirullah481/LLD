package parkinglot.enums;

import parkinglot.exception.InvalidCommandException;

public enum VehicleType {
    CAR("CAR"), BIKE("BIKE"), TRUCK("TRUCK");

    private final String vehicleName;

    VehicleType(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public static VehicleType getVehicleType(String vehicleName) {
        try {
            return VehicleType.valueOf(vehicleName);
        } catch(IllegalArgumentException e) {
            throw new InvalidCommandException("Vehicle " + vehicleName + " Not available");
        }
    }
}

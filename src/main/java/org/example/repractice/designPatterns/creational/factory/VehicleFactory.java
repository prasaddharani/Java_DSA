package org.example.repractice.designPatterns.creational.factory;

public class VehicleFactory {
    public static Vehicle getVehicle(String vehicleType) {
        if ("Car".equalsIgnoreCase(vehicleType)) {
            return new Car();
        } else if ("Bike".equalsIgnoreCase(vehicleType)) {
            return new Bike();
        } else {
            throw new IllegalArgumentException("Unsupported vehicle type");
        }
    }
}

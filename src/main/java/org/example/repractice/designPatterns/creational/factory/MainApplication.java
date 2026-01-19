package org.example.repractice.designPatterns.creational.factory;

public class MainApplication {
    public static void main(String[] args) {
        Vehicle car = VehicleFactory.getVehicle("Car");
        Vehicle bike = VehicleFactory.getVehicle("Bike");

        car.start();
        bike.start();

        car.stop();
        bike.stop();
    }
}

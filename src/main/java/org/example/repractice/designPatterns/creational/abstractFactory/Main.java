package org.example.repractice.designPatterns.creational.abstractFactory;

public class Main {
    public static void main(String[] args) {
        VehicleFactory hondaFactory = new HondaFactory();
        VehicleFactory toyotaFactory = new ToyotaFactory();
        VehicleFactory bmwFactory = new BMWFactory();

        Vehicle honda = hondaFactory.createVehicle();
        Vehicle toyota = toyotaFactory.createVehicle();
        Vehicle bmw = bmwFactory.createVehicle();

        honda.start();
        honda.stop();

        toyota.start();
        toyota.stop();

        bmw.start();
        bmw.stop();
    }
}

package org.example.repractice.designPatterns.creational.abstractFactory;

public class ToyotaFactory implements VehicleFactory{
    @Override
    public Vehicle createVehicle() {
        return new Toyota();
    }
}

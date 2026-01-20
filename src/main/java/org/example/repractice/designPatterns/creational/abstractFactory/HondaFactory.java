package org.example.repractice.designPatterns.creational.abstractFactory;

public class HondaFactory implements VehicleFactory{
    @Override
    public Vehicle createVehicle() {
        return new Honda();
    }
}

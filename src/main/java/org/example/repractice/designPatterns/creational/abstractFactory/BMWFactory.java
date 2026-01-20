package org.example.repractice.designPatterns.creational.abstractFactory;

public class BMWFactory implements VehicleFactory{
    @Override
    public Vehicle createVehicle() {
        return new BMW();
    }
}

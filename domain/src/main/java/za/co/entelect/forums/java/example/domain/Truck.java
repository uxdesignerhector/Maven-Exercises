package za.co.entelect.forums.java.example.domain;

import java.awt.*;

public class Truck extends Vehicle {

    private double loadCapacityInTons;

    public Truck(Truck truck) {
        super(truck);
        loadCapacityInTons = truck.getLoadCapacityInTons();
    }

    public Truck(int numberOfWheels, int numberOfSeats, int maxSpeed, Color bodyColour, double loadCapacityInTons, String registrationNumber) {
        super(numberOfWheels, numberOfSeats, maxSpeed, bodyColour, registrationNumber);
        this.loadCapacityInTons = loadCapacityInTons;
    }

    public double getLoadCapacityInTons() {
        return loadCapacityInTons;
    }

    public void setLoadCapacityInTons(double loadCapacityInTons) {
        this.loadCapacityInTons = loadCapacityInTons;
    }

    public void drive() {
        StringBuilder driveResult = new StringBuilder("I'm a truck driving with a ");
        driveResult.append(loadCapacityInTons);
        driveResult.append(" load capacity.");
        System.out.println(driveResult.toString());
    }
}

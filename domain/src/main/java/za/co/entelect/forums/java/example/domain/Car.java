package za.co.entelect.forums.java.example.domain;

import java.awt.*;

public class Car extends Vehicle {

    public Car(Car car) {
        super(car);
    }

    public Car(int numberOfWheels, int numberOfSeats, int maxSpeed, Color bodyColour, String registrationNumber) {
        super(numberOfWheels, numberOfSeats, maxSpeed, bodyColour, registrationNumber);
    }

    public void drive() {
        System.out.println("I'm a car driving.");
    }


}

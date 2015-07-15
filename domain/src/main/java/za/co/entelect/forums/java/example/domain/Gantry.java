package za.co.entelect.forums.java.example.domain;

import java.util.ArrayList;
import java.util.List;

public class Gantry {

    private String name;
    private Double toll;
    private List<Vehicle> vehicles;

    public Gantry(String name, Double toll) {
        this.name = name;
        this.toll = toll;
        vehicles = new ArrayList<Vehicle>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Double getToll() {
        return toll;
    }

    public void setToll(Double toll) {
        this.toll = toll;
    }
}

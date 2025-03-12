package com.example.powerhome;

import java.util.List;

public class Habitat {
    private final int id;
    private final String name;
    private final int floor;
    private final List<Appliance> appliances;

    public Habitat(int id, String nom, int floor, List<Appliance> appliances) {
        this.id = id;
        this.name = nom;
        this.floor = floor;
        this.appliances = appliances;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public int getFloor() {
        return floor;
    }

    public List<Appliance> getAppliances() {
        return appliances;
    }
}

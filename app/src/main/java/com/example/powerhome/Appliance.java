package com.example.powerhome;

public class Appliance {


    private final Integer id;
    private final String name;
    private final String reference;
    private final Integer wattage;
    private final Integer icon;



    public Appliance(Integer id, String name, String reference, Integer wattage, Integer icon) {
        this.id = id;
        this.name = name;
        this.reference = reference;
        this.wattage = wattage;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReference() {
        return reference;
    }

    public Integer getWattage() {
        return wattage;
    }

    public Integer getIcon() {
        return icon;
    }
}

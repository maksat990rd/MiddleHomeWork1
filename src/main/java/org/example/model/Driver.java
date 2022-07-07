package org.example.model;

import java.security.SecureRandom;

public class Driver {
    private int id;
    private String name;
    private Truck truck;

    public Driver() {
    }

    public Driver(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
    public boolean hasTruck(){
        return this.truck != null;
    }
}

package com.ihm.unice.incident_reporting.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UrgentType implements Serializable{
    private String name;
    private List<User> users;
    private int baseNumber;

    public UrgentType(String name, int baseNumber) {
        this.name = name;
        users = new ArrayList<>();
        this.baseNumber = baseNumber;
    }

    public int getBaseNumber() {
        return baseNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

package com.ihm.unice.incident_reporting.models;

public class UrgentType {
    private String name;

    public UrgentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

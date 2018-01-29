package com.ihm.unice.incident_reporting.models;

import java.util.ArrayList;
import java.util.List;

public class UrgentType {
    private String name;
    private List<User> users;

    public UrgentType(String name) {
        this.name = name;
        users = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

package com.ihm.unice.incident_reporting.models.viewmodels;

public class ParametersUser {
    private String name;
    private boolean isSelected;

    public ParametersUser(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public String getName() {
        return name;
    }
}

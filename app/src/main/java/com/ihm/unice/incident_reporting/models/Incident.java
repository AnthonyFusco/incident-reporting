package com.ihm.unice.incident_reporting.models;

public class Incident {
    private User user;

    public Incident(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}

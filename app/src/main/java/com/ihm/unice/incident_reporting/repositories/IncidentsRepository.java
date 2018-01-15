package com.ihm.unice.incident_reporting.repositories;

import com.ihm.unice.incident_reporting.models.Incident;
import com.ihm.unice.incident_reporting.models.User;

import java.util.ArrayList;
import java.util.List;

public class IncidentsRepository {

    private IncidentsRepository(){}

    public static List<Incident> getAllIncidents(){
        List<Incident> incidents = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            incidents.add(new Incident(new User("Roberto" + i)));

        }
        return incidents;
    }
}

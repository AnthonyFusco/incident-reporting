package com.ihm.unice.incident_reporting.repositories;

import com.ihm.unice.incident_reporting.models.Incident;
import com.ihm.unice.incident_reporting.models.User;
import com.ihm.unice.incident_reporting.models.viewmodels.ParametersUser;

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

    public static List<ParametersUser> getAllUsersParameters(){
        List<ParametersUser> users = new ArrayList<>();
        users.add(new ParametersUser("toto", true));
        users.add(new ParametersUser("titi", false));
        users.add(new ParametersUser("tata", true));
        return users;
    }
}

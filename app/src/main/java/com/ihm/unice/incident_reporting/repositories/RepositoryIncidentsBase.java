package com.ihm.unice.incident_reporting.repositories;

import com.ihm.unice.incident_reporting.models.Incident;
import com.ihm.unice.incident_reporting.models.UrgentType;
import com.ihm.unice.incident_reporting.models.User;
import com.ihm.unice.incident_reporting.models.viewmodels.ParametersUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class RepositoryIncidentsBase implements IncidentsRepository{
    private static List<User> users = Arrays.asList(
            new User("Matthieu"),
            new User("François "),
            new User("Lydie     ")
    );

    private static List<Incident> incidents = new ArrayList<>();

    public void addIncident(Incident incident) {
        incidents.add(0, incident);
    }

    private static List<UrgentType> urgentTypes = Arrays.asList(
            new UrgentType("Fuite de gaz",0),
            new UrgentType("Innondation",0),
            new UrgentType("Malaise",2),
            new UrgentType("Incendie",0),
            new UrgentType("Vol",1)
    );

    RepositoryIncidentsBase(){
//       incidents.addAll(Arrays.asList(
//                new Incident(users.get(0), "11/01/2018 - 12:56"),
//                new Incident(users.get(3), "15/01/2018 - 12:56"),
//                new Incident(users.get(1), "18/01/2018 - 12:56"),
//                new Incident(users.get(2), "19/01/2018 - 12:56"),
//                new Incident(users.get(4), "19/01/2018 - 12:56"),
//                new Incident(users.get(0), "20/01/2018 - 12:56")
//        ));
    }

    @Override
    public List<Incident> getAllIncidents(){
        return RepositoryIncidentsBase.incidents;
    }

    @Override
    public List<ParametersUser> getAllUsersParameters(){
        List<ParametersUser> users = new ArrayList<>();
        for (User user : getUsers()) {
            users.add(new ParametersUser(user, new Random().nextBoolean()));
        }
        return users;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public List<UrgentType> getUrgentTypes() {
        return urgentTypes;
    }

    @Override
    public List<ParametersUser> getAllUsersSelectNormal() {
        List<ParametersUser> users = new ArrayList<>();
        for (User user : getUsers()) {
            users.add(new ParametersUser(user, false));
        }
        return users;
    }
}

package com.ihm.unice.incident_reporting.repositories;

import com.ihm.unice.incident_reporting.models.Incident;
import com.ihm.unice.incident_reporting.models.UrgentType;
import com.ihm.unice.incident_reporting.models.User;
import com.ihm.unice.incident_reporting.models.viewmodels.ParametersUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RepositoryIncidentsBase implements IncidentsRepository{
    private static List<User> users = Arrays.asList(
            new User("Fernande"),
            new User("Roberto"),
            new User("Felicie"),
            new User("Lulu"),
            new User("Georges"),
            new User("Carole")
    ); // #5

    private static List<Incident> incidents = Arrays.asList(
            new Incident(users.get(0), "11/01/2018 - 12:56"),
            new Incident(users.get(3), "15/01/2018 - 12:56"),
            new Incident(users.get(1), "18/01/2018 - 12:56"),
            new Incident(users.get(2), "19/01/2018 - 12:56"),
            new Incident(users.get(4), "19/01/2018 - 12:56"),
            new Incident(users.get(0), "20/01/2018 - 12:56")
    );

    private static List<UrgentType> urgentTypes = Arrays.asList(
            new UrgentType("Fuite de gaz"),
            new UrgentType("Innondation"),
            new UrgentType("Vol")
    );

    public RepositoryIncidentsBase(){}

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

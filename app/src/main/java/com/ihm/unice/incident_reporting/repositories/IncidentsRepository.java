package com.ihm.unice.incident_reporting.repositories;

import com.ihm.unice.incident_reporting.models.Incident;
import com.ihm.unice.incident_reporting.models.UrgentType;
import com.ihm.unice.incident_reporting.models.User;
import com.ihm.unice.incident_reporting.models.viewmodels.ParametersUser;

import java.util.List;

public interface IncidentsRepository {
    List<Incident> getAllIncidents();
    List<ParametersUser> getAllUsersParameters();
    List<User> getUsers();
    List<UrgentType> getUrgentTypes();
    List<ParametersUser> getAllUsersSelectNormal();

    void addIncident(Incident incident);
}

package com.ihm.unice.incident_reporting.repositories;

public class RepositoryFactory {

    public static IncidentsRepository createRepository(){
        return new RepositoryIncidentsBase();
    }
}

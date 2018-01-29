package com.ihm.unice.incident_reporting.repositories;

public class RepositoryFactory {
    private static RepositoryIncidentsBase instance = new RepositoryIncidentsBase();

    public static IncidentsRepository createRepository(){
        return instance;
    }
}

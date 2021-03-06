package com.ihm.unice.incident_reporting.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Incident {
    private static final String DATE_PATTERN = "dd/MM - hh:mm";
    private static DateFormat format = new SimpleDateFormat(DATE_PATTERN, Locale.FRENCH);

    private User user;
    private Date date;
    private String type;

    public Incident(User user, String date) {
        try {
            this.date = format.parse(date);
        } catch (ParseException ignored) {
        }
        this.user = user;
    }

    public Incident(User user, Date date, String type) {
        this.date = date;
        this.user = user;
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public String getDateAsString() {
        return format.format(date);
    }

    public String getType() {
        return type;
    }
}

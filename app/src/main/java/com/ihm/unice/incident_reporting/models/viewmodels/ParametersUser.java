package com.ihm.unice.incident_reporting.models.viewmodels;

import com.ihm.unice.incident_reporting.models.User;

public class ParametersUser {
    private User user;
    private boolean isSelected;

    public ParametersUser(User user, boolean isSelected) {
        this.user = user;
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public User getUser() {
        return user;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }
}

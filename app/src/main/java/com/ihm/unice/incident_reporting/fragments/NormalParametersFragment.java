package com.ihm.unice.incident_reporting.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihm.unice.incident_reporting.R;

public class NormalParametersFragment extends AbstractFragment {

    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.normal_parameters_fragment, container, false);


        return rootView;
    }

    @Override
    public String getTitle() {
        return "Paramètres incident normal";
    }
}

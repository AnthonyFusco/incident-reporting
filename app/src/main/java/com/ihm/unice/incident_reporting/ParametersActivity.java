package com.ihm.unice.incident_reporting;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.ihm.unice.incident_reporting.fragments.SectionsPagerAdapter;


public class ParametersActivity extends MenuBaseActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parameters_activity);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager =  findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }
}

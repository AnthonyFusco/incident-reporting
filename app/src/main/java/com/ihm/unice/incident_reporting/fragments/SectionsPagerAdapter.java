package com.ihm.unice.incident_reporting.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractFragment> fragmentMap = new HashMap<>();

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentMap.put(0, new UrgentParametersFragment());
        fragmentMap.put(1, new NormalParametersFragment());
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentMap.get(position).getTitle();
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentMap.get(position);
    }

    @Override
    public int getCount() {
        return fragmentMap.size();
    }
}
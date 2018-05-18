package com.example.y.potographu.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.y.potographu.fragment.FragmentAppointment;
import com.example.y.potographu.fragment.FragmentMine;
import com.example.y.potographu.fragment.discovery.FragmentDiscoveryFirst;
import com.example.y.potographu.fragment.discovery.FragmentDiscoverySecond;

public class DiscoveryViewPagerAdapter extends FragmentPagerAdapter {
    private  Fragment fragment[]=new Fragment[]{new FragmentDiscoveryFirst(),
            new FragmentDiscoverySecond(),
            new FragmentAppointment(),
            new FragmentMine()};

    private String titles[]=new String[]{"精选","汗水操场","教学楼物语","更多"};
    public DiscoveryViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragment[position];
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

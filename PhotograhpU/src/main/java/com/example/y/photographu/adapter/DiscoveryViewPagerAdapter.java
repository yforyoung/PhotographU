package com.example.y.photographu.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.y.photographu.fragment.discovery.FragmentDiscoveryMyAttention;
import com.example.y.photographu.fragment.discovery.FragmentDiscoveryRecommend;

public class DiscoveryViewPagerAdapter extends FragmentPagerAdapter {
    private  Fragment fragment[]=new Fragment[]{new FragmentDiscoveryMyAttention(),
            new FragmentDiscoveryRecommend()
            };

    private String titles[]=new String[]{"我的关注","精选"};
    public DiscoveryViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragment[position];
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

package com.example.com.myapplication.PFCalculator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by yaucheukming on 14/9/2016.
 */

public class PFViewPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 6;

    public PFViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return PFFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}

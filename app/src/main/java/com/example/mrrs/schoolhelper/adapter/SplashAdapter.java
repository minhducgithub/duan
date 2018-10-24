package com.example.mrrs.schoolhelper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SplashAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 3;

    public SplashAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}

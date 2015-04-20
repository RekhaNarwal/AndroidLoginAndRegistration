package com.socialpresencenetwork.phodo;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Vaio on 4/19/2015.
 */
public class CustomPagerAdapter extends FragmentPagerAdapter {


    ArrayList<Fragment>fragments;

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments= new ArrayList<Fragment>();
        fragments.add(new FriendsActivity());
        fragments.add(new SearchFragment());
        fragments.add(new RequestFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

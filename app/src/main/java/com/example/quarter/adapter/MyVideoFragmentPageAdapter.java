package com.example.quarter.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 祝文 on 2017/11/27.
 */

public class MyVideoFragmentPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;
    private Context context;
    private String[] title=new String[]{"热门","附近"};
    public MyVideoFragmentPageAdapter(FragmentManager fm, ArrayList<Fragment> list, Context context) {
        super(fm);
        this.list = list;
        this.context = context;
    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}

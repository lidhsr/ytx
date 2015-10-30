package com.ytx.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.kymjs.kjframe.ui.SupportFragment;

import java.util.ArrayList;

/**
 * Created by Augustus on 15/10/29.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES = { "全部", "待付款", "待发货", "待收货", "待评价"};
    private ArrayList<SupportFragment> mListViews;

    public MyPagerAdapter(FragmentManager fm, ArrayList<SupportFragment> mListViews) {
        super(fm);
        this.mListViews = mListViews;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        return mListViews.get(position);
    }
}

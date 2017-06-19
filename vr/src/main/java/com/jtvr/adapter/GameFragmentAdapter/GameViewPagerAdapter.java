package com.jtvr.adapter.GameFragmentAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


/**
 * Created by yy on 2017/3/10.
 */
public class GameViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private String[] mTitle;

    public GameViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] mTitle) {
        super(fm);
        this.fragmentList = fragmentList;
        this.mTitle = mTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

}


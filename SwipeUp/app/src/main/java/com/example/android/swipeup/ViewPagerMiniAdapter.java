package com.example.android.swipeup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerMiniAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentListmini = new ArrayList<>();
    private final List<String> mFragmentTitleListmini = new ArrayList<>();
    public ViewPagerMiniAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentListmini.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentListmini.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentListmini.add(fragment);
        mFragmentTitleListmini.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleListmini.get(position);
    }
}

package com.example.myapplication2.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication2.fragment.ContactFragment;
import com.example.myapplication2.fragment.ListFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: ContactFragment contactFragment=new ContactFragment();
                return contactFragment;

            case 1:
                ListFragment listFragment=new ListFragment();
                return listFragment;

            default:return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Tab-1";
        }
        else if (position == 1)
        {
            title = "Tab-2";
        }
        return title;
    }
}

package com.example.myapplication2;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

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

            case 1:ListFragment listFragment=new ListFragment();
                return listFragment;

            default:return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}

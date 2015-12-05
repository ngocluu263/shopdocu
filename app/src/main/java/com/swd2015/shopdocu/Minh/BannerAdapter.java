package com.swd2015.shopdocu.Minh;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.viewpagerindicator.IconPagerAdapter;

import java.util.List;

/**
 * Created by Minh on 11/24/2015.
 */

public class BannerAdapter extends FragmentPagerAdapter implements IconPagerAdapter {

    List<Fragment> listFragments;

    public BannerAdapter(FragmentManager fragmentManager,List<Fragment> listFragments){
        super(fragmentManager);
        this.listFragments=listFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public int getIconResId(int index) {
        return index;
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }
}
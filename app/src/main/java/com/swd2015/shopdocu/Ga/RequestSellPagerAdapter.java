package com.swd2015.shopdocu.Ga;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by PhucLHSE61219 on 20/11/2015.
 */

public class RequestSellPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public RequestSellPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                RequestSellProductFragment productFragmentTab = new RequestSellProductFragment();
                return productFragmentTab;
            case 1:
                RequestSellCustomerFragment customerFragmentTab = new RequestSellCustomerFragment();
                return customerFragmentTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

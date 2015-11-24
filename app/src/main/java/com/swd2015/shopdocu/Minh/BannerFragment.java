package com.swd2015.shopdocu.Minh;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swd2015.shopdocu.R;
import com.viewpagerindicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BannerFragment extends Fragment  {
    ViewPager viewPager;
    BannerAdapter adapter;
    PageIndicator indicator;
    List<android.support.v4.app.Fragment> listFragments;
    public BannerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_banner, container, false);
        viewPager=(ViewPager) v.findViewById(R.id.viewPager);
        listFragments = new ArrayList<>();
        Fragment fragment1=ImageBannerFragment.newInstance(R.drawable.home_icon);
        Fragment fragment2=ImageBannerFragment.newInstance(R.drawable.heart_icon);
        Fragment fragment3=ImageBannerFragment.newInstance(R.drawable.purchase_icon);






        return v;
    }


}

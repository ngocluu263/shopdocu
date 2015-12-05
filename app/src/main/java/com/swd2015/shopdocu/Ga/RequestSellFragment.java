package com.swd2015.shopdocu.Ga;

import android.support.design.widget.TabLayout;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.swd2015.shopdocu.Minh.HomePageActivity;
import com.swd2015.shopdocu.R;

public class RequestSellFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                                                        Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_request_sell, container, false);

        //Initialization tab layout
        final TabLayout tabLayout = (TabLayout) view.findViewById(R.id.request_sell_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.request_sell_product_tab));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.request_sell_customer_tab));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.request_sell_pager);
        final RequestSellPagerAdapter adapter = new RequestSellPagerAdapter(
                                    ((AppCompatActivity)getActivity()).getSupportFragmentManager(),
                                    tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ImageView imageView = (ImageView) view.findViewById(R.id.rs_tab_layout_bar_image);
                if (viewPager.getCurrentItem() == 1 && tab.getPosition() == 1) {
                    imageView.setVisibility(View.INVISIBLE);
                    tabLayout.setVisibility(View.VISIBLE);
                    viewPager.setCurrentItem(1);
                } else if (tab.getPosition() == 0) {
                    tabLayout.setVisibility(View.INVISIBLE);
                    imageView.setVisibility(View.VISIBLE);
                    viewPager.setCurrentItem(0);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
}
package com.swd2015.shopdocu.Minh;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Banner;
import com.swd2015.shopdocu.Controller.Service.BannerService;
import com.swd2015.shopdocu.R;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BannerFragment extends Fragment  {
    String imageURL1="http://cdn.iphonehacks.com/wp-content/uploads/2013/11/black_friday_deals.jpg";
    String imageURL2="http://static.yourtango.com/cdn/farfuture/MIGuKjYqAkv1Wjl-RcIktf3MwIuZsuS21Wj5UXJQjyQ/mtime:1427908155/sites/default/files/styles/listing_big/public/image_blog/singlesday.jpg?itok=K8j6JTl3";
    String imageURL3="http://c.tadst.com/gfx/600x400/christmas.jpg?1";
    public ViewPager viewPager;
    public BannerAdapter adapter;
    public PageIndicator indicator;
    public List<JSON_Banner> listDataBanner;
    BannerService bannerService=new BannerService(this);
    public  List<android.support.v4.app.Fragment> listFragments;
    public FragmentActivity fragmentActivity;
    private Handler handler = new Handler();
    static  Runnable runnable=null;
    static int position=0;

    public BannerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        fragmentActivity= (FragmentActivity) activity;
        super.onAttach(fragmentActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_banner, container, false);
        viewPager=(ViewPager) v.findViewById(R.id.viewPager);
        indicator=(CirclePageIndicator) v.findViewById(R.id.indicator);
        listFragments = new ArrayList<>();
        bannerService.getAllBanner();

        runnable = new Runnable() {
            public void run() {
                if( position > listFragments.size()-1){
                    position = 0;
                }else{
                    position = position+1;
                }
                viewPager.setCurrentItem(position, true);
                handler.postDelayed(runnable, 3000);
            }
        };

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (handler!=null){
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (handler!=null){
            handler.postDelayed(runnable,3000);
        }
    }
}

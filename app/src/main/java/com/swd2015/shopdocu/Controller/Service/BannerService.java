package com.swd2015.shopdocu.Controller.Service;

import android.app.Fragment;
import android.os.Handler;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Banner;
import com.swd2015.shopdocu.Controller.JSON.Task.JSONBannerTask;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;
import com.swd2015.shopdocu.Minh.BannerAdapter;
import com.swd2015.shopdocu.Minh.BannerFragment;
import com.swd2015.shopdocu.Minh.ImageBannerFragment;

import java.util.ArrayList;

/**
 * Created by Minh on 11/28/2015.
 */
public class BannerService {
    Fragment fragment;

    //2 variable for slider function
    static  Runnable runnable=null;
    static int position=0;

    public BannerService(Fragment fragment){
        this.fragment=fragment;
    }

    public void getAllBanner(){
        JSONBannerTask jsonTask = new JSONBannerTask(this, JSONTask.GET_ALL_BANNER);
        jsonTask.execute();
    }

    public void setAllBanner(ArrayList<JSON_Banner> bannerList){
       final BannerFragment bannerFragment=(BannerFragment) fragment;
        final Handler handler = new Handler();
        bannerFragment.listDataBanner=bannerList;

        for (JSON_Banner banner:bannerFragment.listDataBanner) {
            android.support.v4.app.Fragment fragment1=
                    ImageBannerFragment.newInstance(banner.getImage());
            bannerFragment.listFragments.add(fragment1);
        }



        android.support.v4.app.FragmentManager fragmentManager=
                bannerFragment.fragmentActivity.getSupportFragmentManager();

        bannerFragment.adapter=new BannerAdapter(fragmentManager,bannerFragment.listFragments);
        bannerFragment.viewPager.setAdapter(bannerFragment.adapter);

        bannerFragment.indicator.setViewPager(bannerFragment.viewPager);


        // ((CirclePageIndicator) indicator).setSnap(true);

    }
}

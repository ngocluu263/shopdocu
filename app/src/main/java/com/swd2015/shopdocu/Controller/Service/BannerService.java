package com.swd2015.shopdocu.Controller.Service;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Handler;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Banner;
import com.swd2015.shopdocu.Controller.JSON.JSONTask.JSONBannerTask;
import com.swd2015.shopdocu.Controller.JSON.JSONUtil.JSONTask;
import com.swd2015.shopdocu.Controller.Adapter.BannerAdapter;
import com.swd2015.shopdocu.Controller.Fragment.BannerFragment;
import com.swd2015.shopdocu.Controller.Fragment.ImageBannerFragment;

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

    public void connectionError(){
        if(fragment != null) {
            new AlertDialog.Builder(fragment.getActivity()).
                    setMessage("Xin hãy kiểm tra lại kết nối của bạn!").
                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).
                    show();
        }

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

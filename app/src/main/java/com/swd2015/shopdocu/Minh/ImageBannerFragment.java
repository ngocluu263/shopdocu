package com.swd2015.shopdocu.Minh;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.swd2015.shopdocu.R;


public class ImageBannerFragment extends Fragment {
     int imgRes;
    ImageView imgView;

    public ImageBannerFragment(){

    }

    public static final ImageBannerFragment newInstance(int imgRes){
        ImageBannerFragment fragment=new ImageBannerFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("imgRes", imgRes);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_image_banner, container, false);
        imgView=(ImageView) v.findViewById(R.id.imgBanner);
        imgView.setImageResource(imgRes);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imgRes= getArguments().getInt("imgRes");

    }
}

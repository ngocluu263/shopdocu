package com.swd2015.shopdocu.Ga;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.R;

public class RequestSellProductFragment extends Fragment {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView addProductImageView;
    private Button btnContinue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                                                        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_sell_product, container, false);

        //Image View - click to take product picture
        addProductImageView = (ImageView) view.findViewById(R.id.add_product_images_icon);
        addProductImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakingPictureIntent();
            }
        });

        //Initialize View Pager to change tab
        final ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.request_sell_pager);

        //Button Continue - move to next tab
        btnContinue = (Button) view.findViewById(R.id.request_sell_product_button_continue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });

        return view;
    }

    //Function taking picture
    private void dispatchTakingPictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    //Function set taked picture to image view
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            addProductImageView.setImageBitmap(imageBitmap);
        }
    }
}

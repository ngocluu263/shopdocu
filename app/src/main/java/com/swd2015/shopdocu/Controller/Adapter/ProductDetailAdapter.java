package com.swd2015.shopdocu.Controller.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.swd2015.shopdocu.Controller.Activity.ProductDetailActivity;
import com.swd2015.shopdocu.R;

import java.util.List;

/**
 * Created by quangphuong on 11/26/15.
 */
public class ProductDetailAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater layoutInflater;
    public List<String> imageList;
    ProductDetailActivity productDetailActivity;
    View lastImageSelector;

    public ProductDetailAdapter(Context context, List<String> imageList){
        mContext = context;
        layoutInflater = LayoutInflater.from(mContext);
        this.imageList = imageList;
        productDetailActivity = (ProductDetailActivity)mContext;
    }

    @Override
    public int getCount() {
        if (imageList != null) {
            return imageList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View smallImageListView;

        if (convertView == null) {
            smallImageListView = layoutInflater.inflate(R.layout.adapter_product_details, null);
        } else {
            smallImageListView = convertView;
        }

        if (imageList != null){
            int pos = position;
            final String image = imageList.get(position);
            final ImageView productSmallImage = (ImageView) smallImageListView.findViewById(R.id.product_small_image);
            final View productSmallImageSelector = smallImageListView.findViewById(R.id.product_small_image_selector);

            // Load small Images
            Glide.with(mContext)
                    .load(image).override(100, 100).centerCrop()
                    .placeholder(R.drawable.ic_shopping_cart) // optional
                    .error(R.drawable.ic_close_search)         // optional
                    .into(productSmallImage);


            // Set Image click event
            productSmallImage.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (lastImageSelector != null) {
                        lastImageSelector.setVisibility(View.INVISIBLE);
                    }
                    Glide.with(mContext)
                            .load(image)
                            .into(productDetailActivity.productLargeImage);
                    productSmallImageSelector.setVisibility(View.VISIBLE);
                    lastImageSelector = productSmallImageSelector;
                }
            });

        }

        return smallImageListView;
    }
}

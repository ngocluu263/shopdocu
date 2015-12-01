package com.swd2015.shopdocu.Controller.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.swd2015.shopdocu.Controller.Activity.CartActivity;
import com.swd2015.shopdocu.Model.DTO.OrderedProduct;
import com.swd2015.shopdocu.R;

import java.util.List;

/**
 * Created by quangphuong on 11/30/15.
 */
public class CartAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater layoutInflater;
    public List<OrderedProduct> cartList;
    private CartActivity cartActivity;

    public CartAdapter(Context context, List<OrderedProduct> cartList){
        mContext = context;
        layoutInflater = LayoutInflater.from(mContext);
        this.cartList = cartList;
        cartActivity = (CartActivity)mContext;
    }

    @Override
    public int getCount() {
        if (cartList != null) {
            return cartList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return cartList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View cartListView;

        if (convertView == null) {
            cartListView = layoutInflater.inflate(R.layout.adapter_cart, null);
        } else {
            cartListView = convertView;
        }

        if(cartList != null){
            OrderedProduct orderedProduct = cartList.get(position);

            TextView productTitle = (TextView)cartListView.findViewById(R.id.product_name);
            TextView productPrice = (TextView)cartListView.findViewById(R.id.product_price);
            TextView productStatus = (TextView)cartListView.findViewById(R.id.product_status);
            ImageView productSmallImage = (ImageView) cartListView.findViewById(R.id.product_small_image);
            Spinner quantityDropdown = (Spinner)cartListView.findViewById(R.id.spinner_quantity);

            Glide.with(mContext)
                    .load(orderedProduct.getImage()).override(100, 100).centerCrop()
                    .placeholder(R.drawable.ic_shopping_cart) // optional
                    .error(R.drawable.ic_close_search)         // optional
                    .into(productSmallImage);

            productTitle.setText(orderedProduct.getName());
            productPrice.setText(String.valueOf(orderedProduct.getPrice()));
            productStatus.setText(orderedProduct.getStatus());


            String[] items = new String[]{"1", "2", "3" , "4", "5"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, items);
            quantityDropdown.setAdapter(adapter);

            // Maximum quantity is 5
            if (orderedProduct.getQuantity() <= 5) {
                quantityDropdown.setSelection(orderedProduct.getQuantity() - 1);
            } else {
                quantityDropdown.setSelection(4);
            }

            adjustFontSize(productTitle);

        }

        return cartListView;

    }

    public void adjustFontSize(TextView productTitle){
        Display display = cartActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        float textAdjustedSize = (float) ((float) convertPxToDp(width) * 0.045);
        productTitle.setTextSize(textAdjustedSize);
    }

    public int convertPxToDp(float px){
        Context context = cartActivity.getBaseContext();
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);

        return (int) dp;
    }
}

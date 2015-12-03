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
import com.swd2015.shopdocu.Controller.Activity.CheckoutMainActivity;
import com.swd2015.shopdocu.Model.DTO.CartProduct;
import com.swd2015.shopdocu.R;

import java.util.List;

/**
 * Created by quangphuong on 12/3/15.
 */
public class CartPreviewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater layoutInflater;
    public List<CartProduct> cartList;
    private CheckoutMainActivity checkoutMainActivity;

    public CartPreviewAdapter(Context context, List<CartProduct> cartList){
        mContext = context;
        layoutInflater = LayoutInflater.from(mContext);
        this.cartList = cartList;
        checkoutMainActivity = (CheckoutMainActivity)mContext;
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
            cartListView = layoutInflater.inflate(R.layout.adapter_cart_preview, null);
        } else {
            cartListView = convertView;
        }

        if(cartList != null){
            CartProduct cartProduct = cartList.get(position);

            TextView name = (TextView)cartListView.findViewById(R.id.name);
            TextView quantity = (TextView)cartListView.findViewById(R.id.quantity);
            TextView total = (TextView)cartListView.findViewById(R.id.total);
            ImageView productSmallImage = (ImageView) cartListView.findViewById(R.id.product_small_image);

            Glide.with(mContext)
                    .load(cartProduct.getImage()).override(100, 100).centerCrop()
                    .placeholder(R.drawable.ic_shopping_cart) // optional
                    .error(R.drawable.ic_close_search)         // optional
                    .into(productSmallImage);

            name.setText(cartProduct.getName());
            quantity.setText(String.valueOf(cartProduct.getQuantity()));
            total.setText(String.valueOf(cartProduct.getTotal()));

            adjustFontSize(name);

        }

        return cartListView;

    }

    public void adjustFontSize(TextView productTitle){
        Display display = checkoutMainActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        float textAdjustedSize = (float) ((float) convertPxToDp(width) * 0.045);
        productTitle.setTextSize(textAdjustedSize);
    }

    public int convertPxToDp(float px){
        Context context = checkoutMainActivity.getBaseContext();
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);

        return (int) dp;
    }
}

package com.swd2015.shopdocu.Controller.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.swd2015.shopdocu.Controller.Activity.CartActivity;
import com.swd2015.shopdocu.Controller.Service.CartService;
import com.swd2015.shopdocu.Model.DTO.CartProduct;
import com.swd2015.shopdocu.R;

import java.util.List;

/**
 * Created by quangphuong on 11/30/15.
 */
public class CartAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater layoutInflater;
    public List<CartProduct> cartList;
    private CartActivity cartActivity;
    AlertDialog.Builder comfirmRSBuilder;

    public CartAdapter(Context context, List<CartProduct> cartList){
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
        final View cartListView;

        if (convertView == null) {
            cartListView = layoutInflater.inflate(R.layout.adapter_cart, null);
        } else {
            cartListView = convertView;
        }

        if(cartList != null){
            comfirmRSBuilder = new AlertDialog.Builder(cartActivity);
            final CartProduct cartProduct = cartList.get(position);

            TextView productTitle = (TextView)cartListView.findViewById(R.id.product_name);
            TextView productPrice = (TextView)cartListView.findViewById(R.id.product_price);
            TextView productStatus = (TextView)cartListView.findViewById(R.id.product_status);
            ImageView productSmallImage = (ImageView) cartListView.findViewById(R.id.product_small_image);
            ImageButton deleteButton = (ImageButton)cartListView.findViewById(R.id.delete_button);
            Spinner quantityDropdown = (Spinner)cartListView.findViewById(R.id.spinner_quantity);

            Glide.with(mContext)
                    .load(cartProduct.getImage()).override(100, 100).centerCrop()
                    .placeholder(R.drawable.ic_shopping_cart) // optional
                    .error(R.drawable.ic_close_search)         // optional
                    .into(productSmallImage);

            productTitle.setText(cartProduct.getName());
            productPrice.setText(String.valueOf(cartProduct.getPrice()));
            productStatus.setText(cartProduct.getStatus());


            String[] items = new String[]{"1", "2", "3" , "4", "5", "6", "7", "8", "9", "10"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                    android.R.layout.simple_spinner_dropdown_item, items);
            quantityDropdown.setAdapter(adapter);

            // Maximum quantity is 5
            if (cartProduct.getQuantity() <= 5) {
                quantityDropdown.setSelection(cartProduct.getQuantity() - 1);
            } else {
                quantityDropdown.setSelection(4);
            }

            adjustFontSize(productTitle);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Set Confirm message when user click Request Sell button
                    comfirmRSBuilder.setMessage(R.string.delete_cart_confirm);

                    // Button Cancel request sell -> do nothing
                    comfirmRSBuilder.setPositiveButton(R.string.cancel_rs_button, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Do nothing
                        }
                    });
                    // Button Confirm request sell -> save Purchased Order to Database
                    comfirmRSBuilder.setNegativeButton(R.string.delete_confirm_button, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            cartActivity.cartService.deleteCart(cartProduct.getID());
                        }
                    });

                    AlertDialog dialog = comfirmRSBuilder.create();
                    dialog.show();
                    }
                });

            quantityDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                           int position, long id) {
                    int quantity = position + 1;
                    cartActivity.cartService.updateQuantityCart(cartProduct.getID(),
                            quantity);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });

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

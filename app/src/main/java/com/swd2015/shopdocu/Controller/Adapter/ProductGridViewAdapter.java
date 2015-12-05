package com.swd2015.shopdocu.Controller.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.swd2015.shopdocu.Controller.Activity.FavoriteProductActivity;
import com.swd2015.shopdocu.Controller.Activity.ProductDetailActivity;
import com.swd2015.shopdocu.Controller.Activity.SeenProductActivity;
import com.swd2015.shopdocu.Model.DTO.Product;
import com.swd2015.shopdocu.R;

import java.util.List;

/**
 * Created by quangphuong on 12/4/15.
 */
public class ProductGridViewAdapter extends BaseAdapter {
    Activity activity;
    FavoriteProductActivity favoriteProductActivity;
    List<Product> productList;
    private LayoutInflater layoutInflater;

    public ProductGridViewAdapter(Activity activity, List<Product> productList){
        this.activity = activity;
        this.layoutInflater = LayoutInflater.from(activity);
        this.productList = productList;
    }

    @Override
    public int getCount() {
        if(productList != null){
            return productList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View productGridView;
        if (convertView == null) {
            productGridView = layoutInflater.inflate(R.layout.adapter_product_gridview, null);
        } else {
            productGridView = convertView;
        }
        TextView productName = (TextView)productGridView.findViewById(R.id.product_name);
        TextView productPrice = (TextView)productGridView.findViewById(R.id.product_price);
        TextView productStatus = (TextView)productGridView.findViewById(R.id.product_status);
        ImageView productImage = (ImageView)productGridView.findViewById(R.id.product_image);

        final Product product = productList.get(position);

        Glide.with(activity)
                .load(product.getImage()).override(170, 120).centerCrop()
                .placeholder(R.drawable.ic_shopping_cart) // optional
                .error(R.drawable.ic_close_search)         // optional
                .into(productImage);
        productName.setText(product.getName());
        productPrice.setText(String.valueOf(product.getPrice()));
        productStatus.setText(product.getStatus());

        productName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProductDetail(product.getID());
            }
        });

        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProductDetail(product.getID());
            }
        });

        return productGridView;
    }

    public void goToProductDetail(int productID){
        Intent intent = new Intent(activity, ProductDetailActivity.class);
        intent.putExtra("productID", productID);
        activity.startActivity(intent);
    }
}

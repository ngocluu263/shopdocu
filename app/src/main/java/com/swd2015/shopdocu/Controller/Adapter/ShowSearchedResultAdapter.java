package com.swd2015.shopdocu.Controller.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.swd2015.shopdocu.Controller.Activity.ProductDetailActivity;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Product;
import com.swd2015.shopdocu.Controller.Util.FormatNameAndPrice;
import com.swd2015.shopdocu.R;

import java.util.ArrayList;

/**
 * Created by PhucLHSE61219 on 21/11/2015.
 */
public class ShowSearchedResultAdapter extends BaseAdapter {
    Activity activity;
    private Context mContext;
    private LayoutInflater layoutInflater;
    public ArrayList<JSON_Product> searchedProductList;

    public ShowSearchedResultAdapter(Context c,
                                     ArrayList<JSON_Product> searchedProductList, Activity activity) {
        this.activity = activity;
        mContext = c;
        layoutInflater = LayoutInflater.from(mContext);
        this.searchedProductList = searchedProductList;
    }

    public int getCount() {
        if (searchedProductList != null) {
            return searchedProductList.size();
        }
        return 0;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View searchGridView;

        if (convertView == null) {
            searchGridView = layoutInflater.inflate(R.layout.searched_result_grid_item_layout, null);
            searchGridView.setPadding(0,0,0,20);
        } else {
            searchGridView = convertView;
        }

        if (searchedProductList != null) {
            final JSON_Product product = searchedProductList.get(position);
            //Set searched Product Image to Grid View
            ImageView productImageView = (ImageView) searchGridView.findViewById(
                                                                R.id.search_results_product_image);
            Glide.with(mContext)
                 .load(searchedProductList.get(position).getImage().get(0).toString())
                 .placeholder(R.drawable.logo)
                 .error(R.drawable.ic_close_search)
                 .into(productImageView);

            //Set searched Product Name to Grid View
            TextView productNameTextView = (TextView) searchGridView.findViewById(
                                                                R.id.search_results_product_name);
            productNameTextView.setText
                    (FormatNameAndPrice.FormatName(searchedProductList.get(position).getName(), 20));

            //Set searched Product Price to Grid View
            TextView productPriceTextView = (TextView) searchGridView.findViewById(
                                                                R.id.search_results_product_price);
            productImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToProductDetail(product.getID());
                }
            });

            productPriceTextView.setText
                    (FormatNameAndPrice.FormatPrice(searchedProductList.get(position).getPrice()));
        } else {
            searchGridView = convertView;
        }

        return searchGridView;
    }

    public void goToProductDetail(int productID){
        Intent intent = new Intent(activity, ProductDetailActivity.class);
        intent.putExtra("productID", productID);
        activity.startActivity(intent);
    }
}


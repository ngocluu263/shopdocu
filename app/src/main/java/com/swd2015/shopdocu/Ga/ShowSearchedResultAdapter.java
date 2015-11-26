package com.swd2015.shopdocu.Ga;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Product;
import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.R;
import java.util.ArrayList;

/**
 * Created by PhucLHSE61219 on 21/11/2015.
 */
public class ShowSearchedResultAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater layoutInflater;
    public ArrayList<JSON_Product> searchedProductList;
    public String searchedProductImageURL;
    public String searchedProductName;
    public String searchedProductPrice;


    public ShowSearchedResultAdapter(Context c, ArrayList<JSON_Product> searchedProductList) {
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
        System.out.println("dfghjkl");
//        ImageView productImageView;
//        TextView productNameTextView;
//        TextView productPriceTextView;

//        ProductService productService = new ProductService(this);
//        productService.getSearchedProducts("a", 27);

        if (convertView == null) {
            searchGridView = layoutInflater.inflate(R.layout.search_result_gridlayout, null);
        } else {
            searchGridView = convertView;
        }

        if (searchedProductList != null) {
            for (int i = 0; i < searchedProductList.size(); ++i) {
                ImageView productImageView = (ImageView) searchGridView.findViewById(R.id.search_results_product_image);
                Glide.with(mContext)
                        .load(searchedProductList.get(i).getImage().get(i).toString())
                        .placeholder(R.drawable.ic_shopping_cart) // optional
                        .error(R.drawable.ic_close_search)         // optional
                        .into(productImageView);

                TextView productNameTextView = (TextView) searchGridView.findViewById(R.id.search_results_product_name);
                productNameTextView.setText(searchedProductList.get(i).getName());

                TextView productPriceTextView = (TextView) searchGridView.findViewById(R.id.search_results_product_price);
                productPriceTextView.setText(String.valueOf(searchedProductList.get(i).getPrice()));
            }
        } else {
            TextView productNotFoundTextView = (TextView) searchGridView.findViewById(R.id.search_results_product_name);
            productNotFoundTextView.setText("PRODUCT NOT FOUND!");
            System.out.println("NOTTTTT");
            return searchGridView;
        }

        return searchGridView;
    }
}

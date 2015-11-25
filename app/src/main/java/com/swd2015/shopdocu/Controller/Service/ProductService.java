package com.swd2015.shopdocu.Controller.Service;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.swd2015.shopdocu.Controller.Activity.MainActivity;
import com.swd2015.shopdocu.Controller.Activity.ProductDetailActivity;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Product;
import com.swd2015.shopdocu.Controller.JSON.Task.JSONImageTask;
import com.swd2015.shopdocu.Controller.JSON.Task.JSONProductTask;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quang on 20/11/2015.
 */
public class ProductService {
    Activity activity;

    public ProductService(Activity activity){
        this.activity = activity;
    }

    public void getAllProduct(){
        JSONProductTask jsonTask = new JSONProductTask(this, JSONTask.GET_ALL_PRODUCT);
        jsonTask.execute();
    }

    public void setAllProduct(ArrayList<JSON_Product> productList){
        switch (activity.getClass().getSimpleName()){
            case "MainActivity":
                MainActivity mainActivity = (MainActivity) activity;

                // Ví dụ: get product đầu tiên và set Description của nó vào MainActivity
                mainActivity.example = productList.get(0).getDescription();
                break;
        }
    }

    public void getProductByID(int ID){
        JSONProductTask jsonTask = new JSONProductTask(this,JSONTask.GET_PRODUCT_BY_ID, String.valueOf(ID));
        jsonTask.execute();
    }

    public void setProduct(JSON_Product product){
        switch (activity.getClass().getSimpleName()){
            case "ProductDetailActivity":
                ProductDetailActivity productDetailActivity = (ProductDetailActivity) activity;

                productDetailActivity.product = product;
                productDetailActivity.productTitle.setText(product.getName());

                Glide.with(productDetailActivity)
                        .load(product.getImage().get(1))
                        .into(productDetailActivity.productLargeImage);
                Glide.with(productDetailActivity)
                        .load(product.getImage().get(1)).override(100, 100).centerCrop()
                        .into(productDetailActivity.productSmallImage1);
                Glide.with(productDetailActivity)
                        .load(product.getImage().get(2)).override(100, 100).centerCrop()
                        .into(productDetailActivity.productSmallImage2);
                break;
        }
    }
}

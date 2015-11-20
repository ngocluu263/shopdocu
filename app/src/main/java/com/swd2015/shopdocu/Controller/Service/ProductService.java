package com.swd2015.shopdocu.Controller.Service;

import android.app.Activity;

import com.swd2015.shopdocu.Controller.Activity.MainActivity;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Product;
import com.swd2015.shopdocu.Controller.JSON.Task.JSONProductTask;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;

import java.util.ArrayList;

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
        JSONProductTask jsonTask = new JSONProductTask(this,JSONTask.GET_PRODUCT, ID);
        jsonTask.execute();
    }

    public void setProduct(JSON_Product product){
        switch (activity.getClass().getSimpleName()){
            case "MainActivity":
                MainActivity mainActivity = (MainActivity) activity;
                mainActivity.product = product;
                break;
        }
    }
}

package com.swd2015.shopdocu.Controller.JSON.Task;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Product;

import com.google.gson.Gson;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONParser;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;
import com.swd2015.shopdocu.Controller.Service.ProductService;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Quang on 20/11/2015.
 */
public class JSONProductTask extends JSONParser {
    ProductService productService;

    public JSONProductTask(ProductService productService, JSONTask task){
        this.productService = productService;
        this.API = task;
    }

    public JSONProductTask(ProductService productService, JSONTask task, String... params){
        this.productService = productService;
        this.API = task;
        StringBuilder sb = new StringBuilder();
        for (String param:params) {
            sb.append("/" + param);
        }
        this.ID = String.valueOf(sb.toString());
    }

    @Override
    protected void onPostExecute(String json) {
        Gson gson = new Gson();
        switch (this.API){
            case GET_ALL_PRODUCT:
                JSON_Product[] products = gson.fromJson(json, JSON_Product[].class);
                if(products != null) {
                    productService.setAllProduct(new ArrayList<>(Arrays.asList(products)));
                }
                break;
            case GET_PRODUCT_BY_ID:
                JSON_Product p = gson.fromJson(json, JSON_Product.class);
                if(p != null){
                    productService.setProduct(p);
                }
                break;
        }
    }
}

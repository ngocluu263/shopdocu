package com.swd2015.shopdocu.Controller.JSON.Task;

import com.google.gson.Gson;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Product;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONParser;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;
import com.swd2015.shopdocu.Controller.Service.CustomerService;

/**
 * Created by PhucLHSE61219 on 24/11/2015.
 */
public class JSONCustomerTask extends JSONParser {
    CustomerService customerService;

    public JSONCustomerTask(CustomerService customerService, JSONTask task){
        this.customerService = customerService;
        this.API = task;
    }

//    public JSONProductTask(ProductService productService, JSONTask task, int ID){
//        this.productService = productService;
//        this.API = task;
//        this.ID = String.valueOf(ID);
//    }

    public JSONCustomerTask(CustomerService customerService, JSONTask task, String... params){
        this.customerService = customerService;
        this.API = task;
        StringBuilder sb = new StringBuilder();
        for (String param:params) {
            sb.append("/" + param);
            this.ID = sb.toString();
        }
    }

    @Override
    protected void onPostExecute(String json) {
        Gson gson = new Gson();
        switch (this.API){

            case GET_ALL_PRODUCT:
                JSON_Product[] products = gson.fromJson(json, JSON_Product[].class);
              //  customerService.setAllProduct(new ArrayList<>(Arrays.asList(products)));
                break;

        }
    }

    @Override
    protected String doInBackground(String... params) {
        return super.doInBackground(params);
    }
}

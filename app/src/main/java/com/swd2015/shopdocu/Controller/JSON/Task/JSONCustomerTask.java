package com.swd2015.shopdocu.Controller.JSON.Task;

import com.google.gson.Gson;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Customer;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONParser;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;
import com.swd2015.shopdocu.Controller.Service.CustomerService;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by PhucLHSE61219 on 24/11/2015.
 */
public class JSONCustomerTask extends JSONParser {
    CustomerService customerService;

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
            case GET_CUSTOMER_BY_ID:
                JSON_Customer customer = gson.fromJson(json, JSON_Customer.class);
                customerService.setCustomer(customer);
                break;
        }
    }
}

package com.swd2015.shopdocu.Controller.JSON.Task;

import com.google.gson.Gson;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Customer;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONParserPost;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;
import com.swd2015.shopdocu.Controller.Service.CustomerService;

/**
 * Created by Minh on 12/4/2015.
 */
public class JSONPostTask extends JSONParserPost {
    CustomerService customerService;

    public JSONPostTask(CustomerService customerService, JSONTask task){
        this.customerService = customerService;
        this.API = task;
    }

    public JSONPostTask(CustomerService customerService, JSONTask task, String... params){
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
            case CHECK_LOGIN:
                JSON_Customer customer = gson.fromJson(json, JSON_Customer.class);
                customerService.setCheckLogin(customer);
                break;
            case CREATE_ACCOUNT:
                JSON_Customer createAccount = gson.fromJson(json, JSON_Customer.class);
                customerService.setCreateAccount(createAccount);
                break;
        }

    }
}

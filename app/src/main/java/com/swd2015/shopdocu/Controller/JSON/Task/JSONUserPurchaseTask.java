package com.swd2015.shopdocu.Controller.JSON.Task;

import com.google.gson.Gson;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_User_Purchase;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_User_Sold;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONParser;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;
import com.swd2015.shopdocu.Controller.Service.UserPurchaseService;
import com.swd2015.shopdocu.Controller.Service.UserSoldService;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by SherHolmes
 */
public class JSONUserPurchaseTask extends JSONParser {
    UserPurchaseService userPurchaseService;

    public JSONUserPurchaseTask(UserPurchaseService userPurchaseService, JSONTask task){
        this.userPurchaseService = userPurchaseService;
        this.API = task;
    }

    public JSONUserPurchaseTask(UserPurchaseService userPurchaseService, JSONTask task, int ID){
        this.userPurchaseService = userPurchaseService;
        this.API = task;
        this.ID = String.valueOf(ID);
    }

    @Override
    protected void onPostExecute(String json){
        Gson gson = new Gson();

        switch(this.API){
            case GET_USER_PURCHASE:
                JSON_User_Purchase[] user_purchase = gson.fromJson(json, JSON_User_Purchase[].class);
                userPurchaseService.setAllOrder(new ArrayList<>(Arrays.asList(user_purchase)));
                break;
        }
    }
}

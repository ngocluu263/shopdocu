package com.swd2015.shopdocu.Controller.JSON.Task;

import com.google.gson.Gson;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_User_Sold;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONParser;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;
import com.swd2015.shopdocu.Controller.Service.UserSoldService;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by SherHolmes
 */
public class JSONUserSoldTask extends JSONParser {
    UserSoldService userSoldService;

    public JSONUserSoldTask(UserSoldService userSoldService, JSONTask task){
        this.userSoldService = userSoldService;
        this.API = task;
    }

    public JSONUserSoldTask(UserSoldService userSoldService, JSONTask task, int ID){
        this.userSoldService = userSoldService;
        this.API = task;
        this.ID = String.valueOf(ID);
    }

    @Override
    protected void onPostExecute(String json){
        Gson gson = new Gson();

        switch(this.API){
            case GET_USER_SOLD:
                JSON_User_Sold[] user_sold = gson.fromJson(json, JSON_User_Sold[].class);
                userSoldService.setAllOrder(new ArrayList<>(Arrays.asList(user_sold)));
                break;
        }
    }
}

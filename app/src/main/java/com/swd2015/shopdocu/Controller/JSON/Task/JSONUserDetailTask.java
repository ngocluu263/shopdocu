package com.swd2015.shopdocu.Controller.JSON.Task;

import com.google.gson.Gson;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_UserDetail;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONParser;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;
import com.swd2015.shopdocu.Controller.Service.UserDetailService;

/**
 * Created by SherHolmes
 */
public class JSONUserDetailTask extends JSONParser {
    UserDetailService userDetailService;

    public JSONUserDetailTask(UserDetailService userDetailService, JSONTask task){
        this.userDetailService = userDetailService;
        this.API = task;
    }

    public  JSONUserDetailTask(UserDetailService userDetailService, JSONTask task, int ID){
        this.userDetailService = userDetailService;
        this.API = task;
        this.ID = String.valueOf(ID);
    }

    @Override
    protected void onPostExecute(String json){
        Gson gson = new Gson();

        switch (this.API){
            case GET_USER_DETAIL:
                JSON_UserDetail u = gson.fromJson(json, JSON_UserDetail.class);
                userDetailService.setUserDetail(u);
                break;
        }
    }
}

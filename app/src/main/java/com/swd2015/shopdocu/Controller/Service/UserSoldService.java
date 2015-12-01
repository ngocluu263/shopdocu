package com.swd2015.shopdocu.Controller.Service;

import android.app.Activity;

import com.swd2015.shopdocu.Controller.Activity.UserSoldActitity;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_User_Sold;
import com.swd2015.shopdocu.Controller.JSON.Task.JSONUserSoldTask;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;

import java.util.ArrayList;

/**
 * Created by SherHolmes
 */
public class UserSoldService {
    Activity activity;

    public UserSoldService(Activity activity){
        this.activity = activity;
    }

    public void getUserDetail(int ID){
        JSONUserSoldTask jsonTask = new JSONUserSoldTask(this, JSONTask.GET_USER_SOLD, ID);
        jsonTask.execute();
    }

    public void setAllOrder(ArrayList<JSON_User_Sold> userSoldList){
        switch (activity.getClass().getSimpleName()){
            case "UserSoldActivity":
                UserSoldActitity userSoldActitity = (UserSoldActitity) activity;





                break;
        }
    }
}

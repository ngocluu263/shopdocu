package com.swd2015.shopdocu.Controller.Service;

import android.app.Activity;

import com.swd2015.shopdocu.Controller.Activity.UserDetailActivity;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_UserDetail;
import com.swd2015.shopdocu.Controller.JSON.Task.JSONProductTask;
import com.swd2015.shopdocu.Controller.JSON.Task.JSONUserDetailTask;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;
import com.swd2015.shopdocu.Khiem.UserDetail;

import java.util.ArrayList;

/**
 * Created by SherHolmes
 */
public class UserDetailService {
    Activity activity;

    public UserDetailService(Activity activity){
        this.activity = activity;
    }

    public void getUserDetail(int ID){
        JSONUserDetailTask jsonTask = new JSONUserDetailTask(this, JSONTask.GET_USER_DETAIL, ID);
        jsonTask.execute();
    }

    public void setUserDetail(JSON_UserDetail userDetail){
        switch (activity.getClass().getSimpleName()){
            case "UserDetailActivity":
                UserDetailActivity userDetailActivity = (UserDetailActivity) activity;
                userDetailActivity.userDetail = userDetail;
                userDetailActivity.txtFullname.setText(userDetail.getFullname());
                userDetailActivity.edTxtEmail.setText(userDetail.getEmail());
                userDetailActivity.edTxtPhone.setText(userDetail.getPhone());
                userDetailActivity.edTxtDOB.setText(userDetail.getDOB());
                userDetailActivity.edTxtAddress.setText(userDetail.getAddress());
                userDetailActivity.gender = userDetail.getGender();
                if (userDetailActivity.gender.equalsIgnoreCase("Male")){
                    userDetailActivity.rdMale.setChecked(true);
                } else if (userDetailActivity.gender.equalsIgnoreCase("Female")){
                    userDetailActivity.rdFemale.setChecked(true);
                } else {
                    userDetailActivity.rdOther.setChecked(true);
                }
                break;
        }
    }
}
package com.swd2015.shopdocu.Controller.Service;

import android.app.Activity;
import android.content.DialogInterface;

import com.bumptech.glide.Glide;
import com.swd2015.shopdocu.Controller.Activity.UserDetailActivity;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSONUserObject;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_UserDetail;
import com.swd2015.shopdocu.Controller.JSON.JSONTask.JSONUserDetailTask;
import com.swd2015.shopdocu.Controller.JSON.JSONUtil.JSONPost;
import com.swd2015.shopdocu.Controller.JSON.JSONUtil.JSONTask;

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

                String jsonDOB = userDetail.getDOB();
                String dob = "";
                if (jsonDOB == null){
                    dob = "";
                } else if (jsonDOB.length() == 17){
                    dob = userDetail.getDOB().substring(0, 8);
                } else if (jsonDOB.length() == 19){
                    dob = userDetail.getDOB().substring(0,10);
                }

                String image = userDetail.getImage();
                Glide.with(activity.getApplicationContext())
                        .load(image)
//                        .placeholder(R.drawable.loading2)
                        .into(userDetailActivity.imageView);

                userDetailActivity.edTxtName.setText(userDetail.getFullname());
                userDetailActivity.txtFullname.setText(userDetail.getFullname());
                userDetailActivity.edTxtEmail.setText(userDetail.getEmail());
                userDetailActivity.edTxtPhone.setText(userDetail.getPhone());
                userDetailActivity.edTxtDOB.setText(dob);
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

    public void updateUser(JSONUserObject jsonUserObject) {
        JSONPost jsonPost = new JSONPost(this, JSONTask.UPDATE_CUSTOMER, jsonUserObject);
        jsonPost.execute();
    }

    public void httpPostResponse(String response) {
        UserDetailActivity userDetailActivity = (UserDetailActivity) activity;
        if (response.trim().length() != 0) {
            // Set Confirm message when user click Request Sell button
            userDetailActivity.builder.setMessage("Update successful");

            // Button Cancel request sell -> do nothing
            userDetailActivity.builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Do nothing
                }
            });
            userDetailActivity.builder.show();

        } else {
            // Set Confirm message when user click Request Sell button
            userDetailActivity.builder.setMessage("Something happened");

            // Button Cancel request sell -> do nothing
            userDetailActivity.builder.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Do nothing
                }
            });
            userDetailActivity.builder.show();
        }
    }
}

package com.swd2015.shopdocu.Controller.Service;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.swd2015.shopdocu.Controller.Activity.UserSoldActivity;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_User_Sold;
import com.swd2015.shopdocu.Controller.JSON.JSONTask.JSONUserSoldTask;
import com.swd2015.shopdocu.Controller.JSON.JSONUtil.JSONTask;
import com.swd2015.shopdocu.Controller.Adapter.UserSoldAdapter;
import com.swd2015.shopdocu.Controller.Util.Object.User_SoldObj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SherHolmes
 */
public class UserSoldService {
    Activity activity;
    List<User_SoldObj> listChild;

    public UserSoldService(Activity activity){
        this.activity = activity;
    }

    public void getUserSoldDetail(int ID){
        JSONUserSoldTask jsonTask = new JSONUserSoldTask(this, JSONTask.GET_USER_SOLD, ID);
        jsonTask.execute();
    }

    public void connectionError(){
        if (this.activity != null) {
            new AlertDialog.Builder(activity).
                    setMessage("Xin hãy kiểm tra lại kết nối của bạn!").
                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).
                    show();
        }

    }


    public void setAllOrder(ArrayList<JSON_User_Sold> userSoldList){
        switch (activity.getClass().getSimpleName()){
            case "UserSoldActivity":
                UserSoldActivity userSoldActivity = (UserSoldActivity) activity;
                userSoldActivity.soldListView.setAdapter(
                        new UserSoldAdapter(userSoldActivity, setSoldOrderListAdapter(userSoldList)));
                break;
        }
    }

    public List<User_SoldObj> setSoldOrderListAdapter(ArrayList<JSON_User_Sold> parents){
        User_SoldObj child = new User_SoldObj();
        listChild = new ArrayList<User_SoldObj>();
        for (JSON_User_Sold parent: parents) {

            if (parent.getOrderID() == child.getID()) {
                child.addSoldList(parent.getProductName());
            } else {
                child = new User_SoldObj();
                child.setID(parent.getOrderID());
                child.addSoldList(parent.getProductName());
                child.setDate(parent.getCreateDate());
                child.setPrice(parent.getTotal());
                child.setStatus(parent.getOrderStatus());
                listChild.add(child);
            }
        }
        return listChild;
    }
}

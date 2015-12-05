package com.swd2015.shopdocu.Controller.Service;

import android.app.Activity;

import com.swd2015.shopdocu.Controller.Activity.UserPurchaseActivity;
import com.swd2015.shopdocu.Controller.Activity.UserSoldActivity;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_User_Purchase;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_User_Sold;
import com.swd2015.shopdocu.Controller.JSON.Task.JSONUserPurchaseTask;
import com.swd2015.shopdocu.Controller.JSON.Task.JSONUserSoldTask;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;
import com.swd2015.shopdocu.Khiem.UserPurchaseAdapter;
import com.swd2015.shopdocu.Khiem.UserSoldAdapter;
import com.swd2015.shopdocu.Khiem.User_PurchaseObj;
import com.swd2015.shopdocu.Khiem.User_SoldObj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SherHolmes
 */
public class UserPurchaseService {
    Activity activity;
    List<User_PurchaseObj> listChild;

    public UserPurchaseService(Activity activity){
        this.activity = activity;
    }

    public void getUserPurchaseDetail(int ID){
        JSONUserPurchaseTask jsonTask = new JSONUserPurchaseTask(this, JSONTask.GET_USER_PURCHASE, ID);
        jsonTask.execute();
    }

    public void setAllOrder(ArrayList<JSON_User_Purchase> userPurchaseList){
        switch (activity.getClass().getSimpleName()){
            case "UserPurchaseActivity":
                UserPurchaseActivity userPurchaseActivity = (UserPurchaseActivity) activity;



                userPurchaseActivity.purchaseListView.setAdapter(
                        new UserPurchaseAdapter(userPurchaseActivity, setPurchaseOrderListAdapter(userPurchaseList)));

                break;
        }
    }

    public List<User_PurchaseObj> setPurchaseOrderListAdapter(ArrayList<JSON_User_Purchase> parents){
        User_PurchaseObj child = new User_PurchaseObj();
        listChild = new ArrayList<User_PurchaseObj>();
        for (JSON_User_Purchase parent: parents) {

            if (parent.getOrderID() == child.getID()) {
                child.addPurchaseList(parent.getProductName());
            } else {
                child = new User_PurchaseObj();
//                listChild = new ArrayList<User_PurchaseObj>();
                child.setID(parent.getOrderID());
                child.addPurchaseList(parent.getProductName());
                child.setDate(parent.getCreateDate());
                child.setPrice(parent.getTotal());
                child.setStatus(parent.getOrderStatus());
                listChild.add(child);
            }
        }
        return listChild;
    }
}

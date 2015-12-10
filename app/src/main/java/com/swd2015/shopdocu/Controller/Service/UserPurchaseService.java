package com.swd2015.shopdocu.Controller.Service;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.swd2015.shopdocu.Controller.Activity.UserPurchaseActivity;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_User_Purchase;
import com.swd2015.shopdocu.Controller.JSON.JSONTask.JSONUserPurchaseTask;
import com.swd2015.shopdocu.Controller.JSON.JSONUtil.JSONTask;
import com.swd2015.shopdocu.Controller.Adapter.UserPurchaseAdapter;
import com.swd2015.shopdocu.Controller.Util.Object.User_PurchaseObj;

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

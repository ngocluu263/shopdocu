package com.swd2015.shopdocu.Controller.Service;


import android.support.v4.app.Fragment;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_PurchasedOrder;
import com.swd2015.shopdocu.Controller.JSON.JSONUtil.JSONPost;
import com.swd2015.shopdocu.Controller.JSON.JSONUtil.JSONTask;

/**
 * Created by PhucLHSE61219 on 03/12/2015.
 */
public class PurchasedOrderService {
    Fragment fragment;

    public PurchasedOrderService(Fragment fragment){
        this.fragment = fragment;
    }

    public void insertPurchasedOrder(JSON_PurchasedOrder purchasedOrder){
        JSONPost jsonPost = new JSONPost(JSONTask.POST_INSERT_PURCHASED_ORDER, purchasedOrder);
        jsonPost.execute();
    }
}

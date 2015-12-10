package com.swd2015.shopdocu.Controller.Service;


import android.content.DialogInterface;
import android.support.v4.app.Fragment;

import com.swd2015.shopdocu.Controller.Fragment.RequestSellCustomerFragment;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSONRequestSellObject;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_PurchasedOrder;
import com.swd2015.shopdocu.Controller.JSON.JSONUtil.JSONPost;
import com.swd2015.shopdocu.Controller.JSON.JSONUtil.JSONTask;
import com.swd2015.shopdocu.R;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by PhucLHSE61219 on 03/12/2015.
 */
public class PurchasedOrderService {
    Fragment fragment;

    public PurchasedOrderService(Fragment fragment) {
        this.fragment = fragment;
    }

    public void requestSell(JSONRequestSellObject requestSellObject) {
        JSONPost jsonPost = new JSONPost(this, JSONTask.POST_INSERT_PURCHASED_ORDER, requestSellObject);
        jsonPost.execute();
    }

    public void httpPostResponse(String response) {
        RequestSellCustomerFragment rsCustomerFragment = (RequestSellCustomerFragment) fragment;
        if (response.trim().length() != 0) {
            // Set Confirm message when user click Request Sell button
            rsCustomerFragment.showMsgBuilder.setMessage(R.string.confirm_rs_msg_is_success);

            // Button Cancel request sell -> do nothing
            rsCustomerFragment.showMsgBuilder.setPositiveButton(R.string.cancel_rs_button,
                                                            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Do nothing
                }
            });
            rsCustomerFragment.showMsgBuilder.show();

        } else {
            // Set Confirm message when user click Request Sell button
            rsCustomerFragment.showMsgBuilder.setMessage(R.string.confirm_rs_msg_is_fail);

            // Button Cancel request sell -> do nothing
            rsCustomerFragment.showMsgBuilder.setPositiveButton(R.string.cancel_rs_button,
                                                            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Do nothing
                }
            });
            rsCustomerFragment.showMsgBuilder.show();
        }
    }
}


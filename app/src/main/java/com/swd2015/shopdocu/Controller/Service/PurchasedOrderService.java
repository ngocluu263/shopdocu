package com.swd2015.shopdocu.Controller.Service;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.swd2015.shopdocu.Controller.Activity.SellConfirmationActivity;
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

    public void connectionError(){
        if(fragment != null) {
            new AlertDialog.Builder(fragment.getActivity()).
                    setMessage("Xin hãy kiểm tra lại kết nối của bạn!").
                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).
                    show();
        }

    }

    public void httpPostResponse(String response) {
        RequestSellCustomerFragment rsCustomerFragment = (RequestSellCustomerFragment) fragment;
        if (response.trim().length() != 0) {
            // Set Confirm message when user click Request Sell button
            rsCustomerFragment.progressDialog.dismiss();
            rsCustomerFragment.showMsgBuilder.setMessage(R.string.confirm_rs_msg_is_success);

            // Button Cancel request sell -> do nothing
            rsCustomerFragment.showMsgBuilder.setPositiveButton(R.string.cancel_rs_button,
                                                            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // Do nothing
                    Intent intent = new Intent(fragment.getActivity(), SellConfirmationActivity.class);
                    fragment.getActivity().startActivity(intent);
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


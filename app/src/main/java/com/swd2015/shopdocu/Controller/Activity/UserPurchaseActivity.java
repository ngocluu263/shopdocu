package com.swd2015.shopdocu.Controller.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.swd2015.shopdocu.Controller.Service.UserPurchaseService;
import com.swd2015.shopdocu.Controller.Service.UserSoldService;
import com.swd2015.shopdocu.R;

/**
 * Created by SherHolmes
 */
public class UserPurchaseActivity extends AppCompatActivity {
    //public ArrayList<JSON_User_Purchase> listUserPurchase;
    public ListView purchaseListView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fragment__user_buy);

        purchaseListView = (ListView) findViewById(R.id.listView4Buy);

        UserPurchaseService userPurchaseService = new UserPurchaseService(this);
        userPurchaseService.getUserPurchaseDetail(3);



    }
}

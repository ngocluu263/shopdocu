package com.swd2015.shopdocu.Controller.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_User_Sold;
import com.swd2015.shopdocu.Controller.Service.UserSoldService;
import com.swd2015.shopdocu.Khiem.UserSoldAdapter;
import com.swd2015.shopdocu.R;

import java.util.ArrayList;

/**
 * Created by SherHolmes
 */
public class UserSoldActivity extends AppCompatActivity {
    //public ArrayList<JSON_User_Sold> listUserSold;
    public ListView soldListView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fragment__user_sell);

        soldListView = (ListView) findViewById(R.id.listView4Sell);

        UserSoldService userSoldService = new UserSoldService(this);
        userSoldService.getUserSoldDetail(3);



    }
}

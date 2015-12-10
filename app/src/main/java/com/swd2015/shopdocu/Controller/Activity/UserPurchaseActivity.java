package com.swd2015.shopdocu.Controller.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.swd2015.shopdocu.Controller.Service.UserPurchaseService;
import com.swd2015.shopdocu.R;

/**
 * Created by SherHolmes
 */
public class UserPurchaseActivity extends AppCompatActivity {
    public ListView purchaseListView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fragment__user_buy);

        purchaseListView = (ListView) findViewById(R.id.listView4Buy);

        UserPurchaseService userPurchaseService = new UserPurchaseService(this);
        int userID=4;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userID = extras.getInt("UserID");
        }
        userPurchaseService.getUserPurchaseDetail(userID);

        addSoldButtonListener();
        addBuyButtonListener();
    }

    public void addSoldButtonListener(){
        Button soldButton = (Button) findViewById(R.id.Buy);

        soldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buyView = new Intent(UserPurchaseActivity.this, UserPurchaseActivity.class);
                startActivity(buyView);
                finish();
            }
        });

    }

    public  void addBuyButtonListener(){
        Button buyButton = (Button) findViewById(R.id.Sell);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent soldView = new Intent(v.getContext(), UserSoldActivity.class);
                startActivity(soldView);
                finish();
            }
        });
    }
}

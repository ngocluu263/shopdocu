package com.swd2015.shopdocu.Controller.Activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.swd2015.shopdocu.Controller.Service.UserSoldService;
import com.swd2015.shopdocu.R;

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
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));

        soldListView = (ListView) findViewById(R.id.listView4Sell);

        UserSoldService userSoldService = new UserSoldService(this);
        userSoldService.getUserSoldDetail(3);


        addSoldButtonListener();
        addBuyButtonListener();
    }

    public void addSoldButtonListener(){
        Button soldButton = (Button) findViewById(R.id.Sell);

        soldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent soldView = new Intent(UserSoldActivity.this, UserSoldActivity.class);
                startActivity(soldView);
                finish();
            }
        });
    }

    public  void addBuyButtonListener(){
        Button buyButton = (Button) findViewById(R.id.Buy);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buyView = new Intent(v.getContext(), UserPurchaseActivity.class);
                startActivity(buyView);
                finish();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        return true;
    }
}

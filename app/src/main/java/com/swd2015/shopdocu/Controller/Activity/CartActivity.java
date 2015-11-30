package com.swd2015.shopdocu.Controller.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.swd2015.shopdocu.R;

/**
 * Created by quangphuong on 11/29/15.
 */
public class CartActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            String value = extras.getString("GoToCart");
//            try {
//                Intent intent = new Intent(getBaseContext(), Class.forName(value));
//                startActivity(intent);
//                finish();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        return true;
    }

}

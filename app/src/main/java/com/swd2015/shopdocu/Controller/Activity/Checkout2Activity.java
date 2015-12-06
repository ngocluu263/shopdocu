package com.swd2015.shopdocu.Controller.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by quangphuong on 12/2/15.
 */
public class Checkout2Activity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        finish();
        return true;
    }


}

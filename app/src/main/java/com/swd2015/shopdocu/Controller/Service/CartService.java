package com.swd2015.shopdocu.Controller.Service;

import android.app.Activity;

import com.swd2015.shopdocu.Controller.JSON.Task.CartTask;

/**
 * Created by quangphuong on 11/30/15.
 */
public class CartService {
    Activity activity;

    public CartService(Activity activity){
        this.activity = activity;
    }

    public void getCartList(){
        CartTask cartTask = new CartTask(this);
        cartTask.execute();
    }
}

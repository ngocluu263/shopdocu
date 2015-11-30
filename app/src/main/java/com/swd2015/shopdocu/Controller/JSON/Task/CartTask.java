package com.swd2015.shopdocu.Controller.JSON.Task;

import android.os.AsyncTask;

import com.swd2015.shopdocu.Controller.Service.CartService;

/**
 * Created by quangphuong on 11/30/15.
 */
public class CartTask extends AsyncTask {
    CartService cartService;


    public CartTask(CartService cartService){
        this.cartService = cartService;
    }


    @Override
    protected Object doInBackground(Object[] params) {



        return null;
    }
}

package com.swd2015.shopdocu.Controller.Service;

import android.app.Activity;

import com.swd2015.shopdocu.Controller.Activity.CartActivity;
import com.swd2015.shopdocu.Controller.JSON.Task.CartTask;
import com.swd2015.shopdocu.Model.DTO.OrderedProduct;

import java.util.ArrayList;

/**
 * Created by quangphuong on 11/30/15.
 */
public class CartService {
    public Activity activity;

    public CartService(Activity activity){
        this.activity = activity;
    }

    public void getCartList(){
        CartTask cartTask = new CartTask(this);
        cartTask.execute();
    }

    public void setCartList(ArrayList<OrderedProduct> cartList){
        switch (activity.getClass().getSimpleName()){
            case "CartActivity":
                CartActivity cartActivity = (CartActivity) activity;
                cartActivity.
                break;
        }
    }
}

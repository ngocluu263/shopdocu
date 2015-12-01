package com.swd2015.shopdocu.Controller.Service;

import android.app.Activity;
import android.view.View;

import com.swd2015.shopdocu.Controller.Activity.CartActivity;
import com.swd2015.shopdocu.Controller.Adapter.CartAdapter;
import com.swd2015.shopdocu.Controller.JSON.Task.CartTask;
import com.swd2015.shopdocu.Model.DTO.OrderedProduct;

import java.util.ArrayList;

/**
 * Created by quangphuong on 11/30/15.
 */
public class CartService {
    public Activity activity;
    public int totalQuantity;
    public int totalPayment;

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
                cartActivity.cartListView.setVisibility(View.VISIBLE);
                cartActivity.cartListView.setAdapter(new CartAdapter(cartActivity, cartList));
                cartActivity.cartList = cartList;
                calculatePayment(cartList);
                cartActivity.totalQuantity.setText(String.valueOf(totalQuantity));
                cartActivity.totalPayment.setText(String.valueOf(totalPayment));
                break;
        }
    }

    public void calculatePayment(ArrayList<OrderedProduct> cartList){
        for (OrderedProduct product:cartList) {
            totalQuantity += product.getQuantity();
            totalPayment += totalQuantity * product.getPrice();
        }
    }
}

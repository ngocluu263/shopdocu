package com.swd2015.shopdocu.Controller.Service;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;

import com.swd2015.shopdocu.Controller.Activity.CartActivity;
import com.swd2015.shopdocu.Controller.Adapter.CartAdapter;
import com.swd2015.shopdocu.Controller.Adapter.CartPreviewAdapter;
import com.swd2015.shopdocu.Controller.Fragment.Confirmation1Fragment;
import com.swd2015.shopdocu.Controller.Task.CartTask;
import com.swd2015.shopdocu.Controller.Util.DBTask;
import com.swd2015.shopdocu.Model.DTO.CartProduct;

import java.util.ArrayList;

/**
 * Created by quangphuong on 11/30/15.
 */
public class CartService {
    public Activity activity;
    public Fragment fragment;
    public int totalQuantity;
    public int totalPayment;

    public CartService(Activity activity) {
        this.activity = activity;
    }

    public CartService(Fragment fragment) {
        this.fragment = fragment;
        this.activity = fragment.getActivity();
    }

    public void getCartList() {
        CartTask cartTask = new CartTask(this, DBTask.GET_ALL_CART);
        cartTask.execute();
    }

    public void setCartList(ArrayList<CartProduct> cartList) {
        if (fragment == null) {
            switch (activity.getClass().getSimpleName()) {
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
        } else {
            switch (fragment.getClass().getSimpleName()) {
                case "Confirmation1Fragment":
                    Confirmation1Fragment confirmation1Fragment = (Confirmation1Fragment)fragment;
//                    confirmation1Fragment.cartListPreview.setVisibility(View.VISIBLE);
                    confirmation1Fragment.cartListPreview.setAdapter(new CartPreviewAdapter(fragment.getActivity(), cartList));
                    confirmation1Fragment.cartProducts = cartList;
                    calculatePayment(cartList);
                    confirmation1Fragment.totalPayment.setText(String.valueOf(totalPayment));
                    break;
            }
        }
    }

    public void calculatePayment(ArrayList<CartProduct> cartList) {
        for (CartProduct product : cartList) {
            totalQuantity += product.getQuantity();
            totalPayment += totalQuantity * product.getPrice();
        }
    }

    public void deleteCart(int productID){
        CartTask cartTask = new CartTask(productID, DBTask.DELETE_CART);
        cartTask.execute();
    }
}

package com.swd2015.shopdocu.Controller.Service;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import android.widget.BaseAdapter;

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
    public BaseAdapter adapter;
    public int totalQuantity;
    public int totalPayment;

    public CartService(BaseAdapter adapter) {
        this.adapter = adapter;
    }

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

    public void setCartList(final ArrayList<CartProduct> cartList) {
        if (fragment == null) {
            switch (activity.getClass().getSimpleName()) {
                case "CartActivity":
                    final CartActivity cartActivity = (CartActivity) activity;
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            cartActivity.cartListView.setVisibility(View.VISIBLE);
                            cartActivity.cartListView.setAdapter(new CartAdapter(cartActivity, cartList));
                            cartActivity.cartList = cartList;
                            calculatePayment(cartList);
                            cartActivity.totalQuantity.setText(String.valueOf(totalQuantity));
                            cartActivity.totalPayment.setText(String.valueOf(totalPayment));
                        }
                    });
                    break;
            }
        } else {
            switch (fragment.getClass().getSimpleName()) {
                case "Confirmation1Fragment":
                    final Confirmation1Fragment confirmation1Fragment = (Confirmation1Fragment) fragment;
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            confirmation1Fragment.cartListPreview.setAdapter(new CartPreviewAdapter(fragment.getActivity(), cartList));
                            confirmation1Fragment.cartProducts = cartList;
                            calculatePayment(cartList);
                            confirmation1Fragment.totalPayment.setText(String.valueOf(totalPayment));
                        }
                    });
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

    public void deleteCart(BaseAdapter adapter, int productID) {
        this.adapter = adapter;
        CartTask cartTask = new CartTask(this, productID, DBTask.DELETE_CART);
        cartTask.execute();

    }

    public void resetListView() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void updateQuantityCart(BaseAdapter adapter, int productID, int quantity) {
        this.adapter = adapter;
        CartTask cartTask = new CartTask(this, productID, quantity, DBTask.UPDATE_CART);
        cartTask.execute();
    }
	public boolean cartHasProduct(){
        CartProductDAO cartProductDAO = new CartProductDAO(activity.getApplicationContext());
        if (cartProductDAO.numberOfRecord() != 0) {
            return true;
        }
        return false;
    }
	
}

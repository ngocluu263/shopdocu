package com.swd2015.shopdocu.Controller.JSON.Task;

import android.os.AsyncTask;

import com.swd2015.shopdocu.Controller.Service.CartService;
import com.swd2015.shopdocu.Model.DAO.CartProductDAO;

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
        CartProductDAO dao;
        if(cartService.activity != null){
            dao = new CartProductDAO(cartService.activity.getBaseContext());
        } else {
            dao = new CartProductDAO(cartService.fragment.getActivity());
        }
        cartService.setCartList(dao.getAllCart());
        return null;
    }
}

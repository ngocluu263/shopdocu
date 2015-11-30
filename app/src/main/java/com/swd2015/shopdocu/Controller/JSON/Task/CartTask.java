package com.swd2015.shopdocu.Controller.JSON.Task;

import android.os.AsyncTask;
import android.os.Bundle;
import com.swd2015.shopdocu.Controller.Service.CartService;
import com.swd2015.shopdocu.Model.DAO.OrderedProductDAO;

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
        OrderedProductDAO dao = new OrderedProductDAO(cartService.activity.getBaseContext());
        cartService.setCartList(dao.getAllCart());

        return null;
    }
}

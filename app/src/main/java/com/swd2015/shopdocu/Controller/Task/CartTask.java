package com.swd2015.shopdocu.Controller.Task;

import android.os.AsyncTask;

import com.swd2015.shopdocu.Controller.Service.CartService;
import com.swd2015.shopdocu.Controller.Util.DBTask;
import com.swd2015.shopdocu.Model.DAO.CartProductDAO;

/**
 * Created by quangphuong on 11/30/15.
 */
public class CartTask extends AsyncTask {
    CartService cartService;
    DBTask dbTask;
    int productID;

    public CartTask(CartService cartService, DBTask dbTask){
        this.cartService = cartService;
        this.dbTask = dbTask;
    }

    public CartTask(int productID, DBTask dbTask){
        this.productID = productID;
        this.dbTask = dbTask;
    }


    @Override
    protected Object doInBackground(Object[] params) {
        CartProductDAO dao;
        switch (dbTask) {
            case GET_ALL_CART:
                dao = new CartProductDAO(cartService.activity.getBaseContext());
                cartService.setCartList(dao.getAllCart());
                break;
            case DELETE_CART:
                dao = new CartProductDAO(cartService.activity.getBaseContext());
                dao.deleteCart(productID);
                break;
        }
        return null;
    }
}

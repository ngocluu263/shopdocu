package com.swd2015.shopdocu.Controller.JSON.Util;

/**
 * Created by Quang on 20/11/2015.
 */
public enum JSONTask {
    GET_ALL_PRODUCT("http://swd2015api.azurewebsites.net/api/product"),
    GET_PRODUCT_BY_ID("http://swd2015api.azurewebsites.net/api/product/getproductbyid"),
    GET_SEARCH_PRODUCTS("http://swd2015api.azurewebsites.net/api/product/SearchProduct"),
    GET_NEW_PRODUCTS("http://swd2015api.azurewebsites.net/api/product/getnewproducts"),
    GET_CUSTOMER_BY_ID("http://swd2015api.azurewebsites.net/api/customer/getcustomerbyid"),
    POST_INSERT_PURCHASED_ORDER(
                        "http://swd2015api.azurewebsites.net/api/PurchasedOrder/AddPurchasedOrder")
    ;

    private final String text;

    JSONTask(final String text){
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

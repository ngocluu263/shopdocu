package com.swd2015.shopdocu.Controller.JSON.Util;

/**
 * Created by Quang on 20/11/2015.
 */
public enum JSONTask {
    GET_ALL_PRODUCT("http://swd2015-001-site1.1tempurl.com/api/product"),
    GET_PRODUCT_BY_ID("http://swd2015-001-site1.1tempurl.com/api/product/getproductbyid"),
    GET_SEARCHED_PRODUCTS("http://swd2015api.azurewebsites.net/api/product/SearchProduct"),
    GET_NEW_PRODUCTS("http://swd2015-001-site1.1tempurl.com/api/product/getnewproducts"),
    GET_CUSTOMER_BY_ID("http://swd2015-001-site1.1tempurl.com/api/customer/getcustomerbyid"),
    GET_ALL_BANNER("http://swd2015-001-site1.1tempurl.com/api/Image/Banner/GetAllBanners"),
    GET_HOT_PRODUCTS("http://swd2015api.azurewebsites.net/api/product/GetHotProducts"),
    CHECK_LOGIN("")
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

package com.swd2015.shopdocu.Controller.JSON.Util;

/**
 * Created by Quang on 20/11/2015.
 */
public enum JSONTask {
    GET_ALL_PRODUCT("http://swd2015-001-site1.1tempurl.com/api/product"),
    GET_PRODUCT("http://swd2015-001-site1.1tempurl.com/api/product/getproductbyid/"),

    //Customer detail
    GET_USER_DETAIL("http://swd2015-001-site1.1tempurl.com/api/customer/GetCustomerByID/"),

    //Customer order
    GET_USER_SOLD("http://swd2015-001-site1.1tempurl.com/api/OrderDetail/GetAllSoldOrderDetailsByCustomerID/")
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

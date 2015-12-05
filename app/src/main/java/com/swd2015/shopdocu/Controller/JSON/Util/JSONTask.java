package com.swd2015.shopdocu.Controller.JSON.Util;

/**
 * Created by Quang on 20/11/2015.
 */
public enum JSONTask {
<<<<<<< HEAD
    GET_ALL_PRODUCT("http://swd2015api.azurewebsites.net/api/product"),
    GET_PRODUCT_BY_ID("http://swd2015api.azurewebsites.net/api/product/getproductbyid"),
    GET_SEARCH_PRODUCTS("http://swd2015api.azurewebsites.net/api/product/SearchProduct"),
    GET_NEW_PRODUCTS("http://swd2015api.azurewebsites.net/api/product/getnewproducts"),
    GET_CUSTOMER_BY_ID("http://swd2015api.azurewebsites.net/api/customer/getcustomerbyid"),
    POST_INSERT_PURCHASED_ORDER(
                        "http://swd2015api.azurewebsites.net/api/PurchasedOrder/AddPurchasedOrder")
    GET_SEARCHED_PRODUCTS("http://swd2015api.azurewebsites.net/api/product/SearchProduct"), 
    GET_ALL_BANNER("http://swd2015api.azurewebsites.net/api/Image/Banner/GetAllBanners"),
    GET_HOT_PRODUCTS("http://swd2015api.azurewebsites.net/api/product/GetHotProducts"),
    CHECK_LOGIN("http://swd2015api.azurewebsites.net/api/Customer/CheckLogin"),
    CREATE_ACCOUNT("http://swd2015api.azurewebsites.net/api/Customer/CreateAccount"),

    //Customer detail
    GET_USER_DETAIL("http://swd2015-001-site1.1tempurl.com/api/customer/GetCustomerByID/"),

    //Customer order
    GET_USER_SOLD("http://swd2015-001-site1.1tempurl.com/api/OrderDetail/GetAllSoldOrderDetailsByCustomerID/"),
    GET_USER_PURCHASE("http://swd2015-001-site1.1tempurl.com/api/PurchasedOrder/GetAllPurchasedOrderDetailsByCustomerID/")
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

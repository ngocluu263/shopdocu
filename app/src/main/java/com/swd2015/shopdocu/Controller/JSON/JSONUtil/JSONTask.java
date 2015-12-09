package com.swd2015.shopdocu.Controller.JSON.JSONUtil;

/**
 * Created by Quang on 20/11/2015.
 */

public enum JSONTask {
    GET_ALL_PRODUCT("api/product"),
    GET_PRODUCT_BY_ID("api/product/getproductbyid"),
    GET_SEARCHED_PRODUCTS("api/product/SearchProduct"),
    GET_NEW_PRODUCTS("api/product/getnewproducts"),
    GET_CUSTOMER_BY_ID("api/customer/getcustomerbyid"),
    POST_INSERT_PURCHASED_ORDER(
                        "api/PurchasedOrder/AddPurchasedOrder"),
    GET_ALL_BANNER("api/Image/Banner/GetAllBanners"),
    GET_HOT_PRODUCTS("api/product/GetHotProducts"),
    CHECK_LOGIN("api/Customer/CheckLogin"),
    CREATE_ACCOUNT("api/Customer/CreateAccount"),

    //Customer detail
    GET_USER_DETAIL("api/customer/GetCustomerByID/"),

    //Customer order
    GET_USER_SOLD("api/OrderDetail/GetAllSoldOrderDetailsByCustomerID/"),
    GET_USER_PURCHASE("api/PurchasedOrder/GetAllPurchasedOrderDetailsByCustomerID/"),
    ADD_SOLD_PRODUCT("api/OrderDetail/CreateSoldOrderDetail"),
    ADD_CUSTOMER("api/customer/CreateAccount");

    private static String API = "http://www.shopdocu.info/";

    private final String text;

    JSONTask(final String text){
        this.text = text;
    }

    @Override
    public String toString() {
        return API + text;
    }
}

package com.swd2015.shopdocu.Model.Util;

/**
 * Created by Quang on 14-Nov-15.
 */
public abstract class DBConfig {
    public static final String DATABASE_NAME = "ShopDoCu";
    public static final int DATABASE_VERSION = 1;
    
    public static final String TABLE_CART_PRODUCT = "CartProduct";
    public static final String TABLE_FAVORITE = "Favorite";
    public static final String TABLE_SEEN_PRODUCT = "SeenProduct";
    public static final String TABLE_USER = "User";

    public static final String ORDER_ID = "OrderID";
    public static final String PRODUCT_ID = "ProductID";
    public static final String PRODUCT_NAME = "Name";
    public static final String PRODUCT_PRICE = "Price";
    public static final String PRODUCT_DESCRIPTION = "Description";
    public static final String PRODUCT_CATEGORY = "Category";
    public static final String PRODUCT_STATUS = "Status";
    public static final String PRODUCT_CREATEDATE = "CreateDate";
    public static final String PRODUCT_IMAGE = "Image";
    public static final String ORDER_QUANTITY = "Quantity";

    public static final String FAVORITE_ID = "ID";

    public static final String SEEN_PRODUCT_ID = "ID";

    public static final String USER_ID = "ID";
    public static final String USER_NAME = "FullName";
    public static final String USER_GENDER = "Gender";
    public static final String USER_EMAIL = "Email";
    public static final String USER_ADDRESS = "Address";
    public static final String USER_PHONE = "PhoneNumber";
    public static final String USER_PASSWORD = "Password";
}

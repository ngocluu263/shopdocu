package com.swd2015.shopdocu.Model.Util;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Quang on 14-Nov-15.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final String CREATE_CART_TABLE = "CREATE TABLE " +
            DBConfig.TABLE_CART_PRODUCT + "(" +
            DBConfig.ORDER_ID + " INTEGER PRIMARY KEY," +
            DBConfig.PRODUCT_ID + " INTEGER," +
            DBConfig.PRODUCT_NAME + " TEXT," +
            DBConfig.PRODUCT_PRICE + " INTEGER," +
            DBConfig.PRODUCT_DESCRIPTION + "  TEXT," +
            DBConfig.PRODUCT_CATEGORY + " TEXT," +
            DBConfig.PRODUCT_STATUS + " TEXT," +
            DBConfig.PRODUCT_CREATEDATE + " DATE," +
            DBConfig.PRODUCT_IMAGE + " TEXT," +
            DBConfig.ORDER_QUANTITY + " INTEGER" +
            ")";

    private static final String CREATE_FAVORITE_TABLE = "CREATE TABLE " +
            DBConfig.TABLE_FAVORITE + " (" +
            DBConfig.FAVORITE_ID + " INTEGER PRIMARY KEY," +
            DBConfig.PRODUCT_ID + " INTEGER," +
            DBConfig.PRODUCT_NAME + " TEXT," +
            DBConfig.PRODUCT_PRICE + " INTEGER," +
            DBConfig.PRODUCT_DESCRIPTION + "  TEXT," +
            DBConfig.PRODUCT_CATEGORY + " TEXT," +
            DBConfig.PRODUCT_STATUS + " TEXT," +
            DBConfig.PRODUCT_CREATEDATE + " DATE," +
            DBConfig.PRODUCT_IMAGE + " TEXT" +
            ")";

    private static final String CREATE_SEEN_TABLE = "CREATE TABLE " +
            DBConfig.TABLE_SEEN_PRODUCT + " (" +
            DBConfig.SEEN_PRODUCT_ID + " INTEGER PRIMARY KEY," +
            DBConfig.PRODUCT_ID + " INTEGER," +
            DBConfig.PRODUCT_NAME + " TEXT," +
            DBConfig.PRODUCT_PRICE + " INTEGER," +
            DBConfig.PRODUCT_DESCRIPTION + "  TEXT," +
            DBConfig.PRODUCT_CATEGORY + " TEXT," +
            DBConfig.PRODUCT_STATUS + " TEXT," +
            DBConfig.PRODUCT_CREATEDATE + " DATE," +
            DBConfig.PRODUCT_IMAGE + " TEXT" +
            ")";

    private static final String CREATE_USER_TABLE = "CREATE TABLE " +
            DBConfig.TABLE_USER + " (" +
            DBConfig.USER_ID + " INTEGER PRIMARY KEY," +
            DBConfig.USER_NAME + " TEXT," +
            DBConfig.USER_GENDER + " TEXT," +
            DBConfig.USER_EMAIL + " TEXT," +
            DBConfig.USER_ADDRESS + "  TEXT," +
            DBConfig.USER_PHONE + " TEXT," +
            DBConfig.USER_PASSWORD + " TEXT" +
            ")";

    public DBHandler(Context context) {
        super(context, DBConfig.DATABASE_NAME, null, DBConfig.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CART_TABLE);
        db.execSQL(CREATE_FAVORITE_TABLE);
        db.execSQL(CREATE_SEEN_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBConfig.TABLE_CART_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + DBConfig.TABLE_FAVORITE);
        db.execSQL("DROP TABLE IF EXISTS " + DBConfig.TABLE_SEEN_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + DBConfig.TABLE_USER);
        onCreate(db);
    }
}

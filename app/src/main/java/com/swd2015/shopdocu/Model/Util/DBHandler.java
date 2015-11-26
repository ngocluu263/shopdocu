package com.swd2015.shopdocu.Model.Util;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Quang on 14-Nov-15.
 */
public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBConfig.DATABASE_NAME, factory, DBConfig.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ORDER_TABLE = "CREATE TABLE " +
                DBConfig.TABLE_ORDERED_PRODUCT + "(" +
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

        String CREATE_FAVORITE_TABLE = "CREATE TABLE " +
                DBConfig.TABLE_FAVORITE + " (" +
                DBConfig.FAVORITE_ID + " INTEGER PRIMARY KEY," +
                DBConfig.FAVORITE_PRODUCT_ID + " INTEGER" +
                ")";

        String CREATE_SEEN_TABLE = "CREATE TABLE " +
                DBConfig.TABLE_SEEN_PRODUCT + " (" +
                DBConfig.SEEN_PRODUCT_ID + " INTEGER PRIMARY KEY," +
                DBConfig.SEEN_PRODUCT_PRODUCT_ID + " INTEGER" +
                ")";

        db.execSQL(CREATE_ORDER_TABLE);
        db.execSQL(CREATE_FAVORITE_TABLE);
        db.execSQL(CREATE_SEEN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBConfig.TABLE_ORDERED_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + DBConfig.TABLE_FAVORITE);
        db.execSQL("DROP TABLE IF EXISTS " + DBConfig.TABLE_SEEN_PRODUCT);
        onCreate(db);
    }
}

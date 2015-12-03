package com.swd2015.shopdocu.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.swd2015.shopdocu.Model.DTO.Product;
import com.swd2015.shopdocu.Model.Util.DBConfig;
import com.swd2015.shopdocu.Model.Util.DBHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by quangphuong on 12/2/15.
 */
public class SeenDAO extends DBHandler {
    public SeenDAO(Context context) {
        super(context);
    }

    public int numberOfRecord() {
        String query = "SELECT  * FROM " + DBConfig.TABLE_SEEN_PRODUCT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public boolean isProductExist(int productID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + DBConfig.TABLE_SEEN_PRODUCT +
                " where " + DBConfig.PRODUCT_ID + " = " + productID;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


    public void addSeenProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();

        if (isProductExist(product.getID())){
            db.close();
            return;
        }

        int incrementingID = numberOfRecord();
        if(incrementingID != 0){
            incrementingID++;
        }

        ContentValues values = new ContentValues();
        values.put(DBConfig.SEEN_PRODUCT_ID, incrementingID);
        values.put(DBConfig.PRODUCT_ID, product.getID());
        values.put(DBConfig.PRODUCT_NAME, product.getName());
        values.put(DBConfig.PRODUCT_CATEGORY, product.getCategory());
        values.put(DBConfig.PRODUCT_DESCRIPTION, product.getDescription());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String date = df.format(product.getCreateDate());
        values.put(DBConfig.PRODUCT_CREATEDATE, date);
        values.put(DBConfig.PRODUCT_PRICE, product.getPrice());
        values.put(DBConfig.PRODUCT_STATUS, product.getStatus());
        values.put(DBConfig.PRODUCT_IMAGE, product.getImage());

        db.insert(DBConfig.TABLE_SEEN_PRODUCT, null, values);
        db.close();
    }
}

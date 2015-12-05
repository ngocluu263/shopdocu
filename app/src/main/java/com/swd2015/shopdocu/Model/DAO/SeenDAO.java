package com.swd2015.shopdocu.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.swd2015.shopdocu.Model.DTO.CartProduct;
import com.swd2015.shopdocu.Model.DTO.Product;
import com.swd2015.shopdocu.Model.Util.DBConfig;
import com.swd2015.shopdocu.Model.Util.DBHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        ContentValues values = new ContentValues();

        values.put(DBConfig.PRODUCT_NAME, product.getName());
        values.put(DBConfig.PRODUCT_CATEGORY, product.getCategory());
        values.put(DBConfig.PRODUCT_DESCRIPTION, product.getDescription());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String date = df.format(product.getCreateDate());
        values.put(DBConfig.PRODUCT_CREATEDATE, date);
        values.put(DBConfig.PRODUCT_PRICE, product.getPrice());
        values.put(DBConfig.PRODUCT_STATUS, product.getStatus());
        values.put(DBConfig.PRODUCT_IMAGE, product.getImage());

        if (isProductExist(product.getID())){
            db.update(DBConfig.TABLE_SEEN_PRODUCT, values,
                    DBConfig.PRODUCT_ID + "=" + product.getID(), null);
        } else {
            int incrementingID = numberOfRecord();
            if(incrementingID != 0){
                incrementingID++;
            }
            values.put(DBConfig.SEEN_PRODUCT_ID, incrementingID);
            values.put(DBConfig.PRODUCT_ID, product.getID());
            db.insert(DBConfig.TABLE_SEEN_PRODUCT, null, values);
        }

        db.close();
    }

    public ArrayList<Product> getAllSeenProduct(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        ArrayList<Product> seenProductList = new ArrayList<>();
        try {
            cursor = db.rawQuery("SELECT *" +
                    " FROM " + DBConfig.TABLE_SEEN_PRODUCT, null);
            if (cursor.moveToFirst()){
                Product product = new Product();
                while (cursor.isAfterLast() == false){
                    product.setID(cursor.getInt(cursor.getColumnIndex(DBConfig.SEEN_PRODUCT_ID)));
                    product.setID(cursor.getInt(cursor.getColumnIndex(DBConfig.PRODUCT_ID)));
                    product.setName(cursor.getString(cursor.getColumnIndex(DBConfig.PRODUCT_NAME)));
                    product.setCategory(cursor.getInt(cursor.getColumnIndex(DBConfig.PRODUCT_CATEGORY)));
                    product.setStatus(cursor.getString(cursor.getColumnIndex(DBConfig.PRODUCT_STATUS)));
                    product.setImage(cursor.getString(cursor.getColumnIndex(DBConfig.PRODUCT_IMAGE)));
                    product.setPrice(cursor.getFloat(cursor.getColumnIndex(DBConfig.PRODUCT_PRICE)));
                    product.setDescription(cursor.getString(cursor.getColumnIndex(DBConfig.PRODUCT_DESCRIPTION)));
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                    Date date = df.parse(cursor.getString(cursor.getColumnIndex(DBConfig.PRODUCT_CREATEDATE)));
                    product.setCreateDate(date);
                    seenProductList.add(product);
                    cursor.moveToNext();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            cursor.close();
            db.close();
        }
        return seenProductList;
    }
}

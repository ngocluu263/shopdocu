package com.swd2015.shopdocu.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.swd2015.shopdocu.Model.DTO.Favorite;
import com.swd2015.shopdocu.Model.DTO.Product;
import com.swd2015.shopdocu.Model.Util.DBConfig;
import com.swd2015.shopdocu.Model.Util.DBHandler;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Quang on 14-Nov-15.
 */
public class FavoriteDAO extends DBHandler {

    public FavoriteDAO(Context context) {
        super(context);
    }

    public int numberOfRecord() {
        String query = "SELECT  * FROM " + DBConfig.TABLE_FAVORITE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public boolean isProductExist(int productID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + DBConfig.TABLE_FAVORITE +
                " where " + DBConfig.PRODUCT_ID + " = " + productID;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        try {
            db.close();
        } catch (Exception e){
            System.out.println("Connection already close 1!");
        }
        return true;
    }

    public void addFavorite(Product product){
        SQLiteDatabase db = null;

        if (isProductExist(product.getID())){
            deleteFavorite(product.getID());
        } else {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
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
            int incrementingID = numberOfRecord();
            if(incrementingID != 0){
                incrementingID++;
            }
            values.put(DBConfig.FAVORITE_ID, incrementingID);
            db.insert(DBConfig.TABLE_FAVORITE, null, values);
        }
        try {
            db.close();
        } catch (Exception e){
            System.out.println("Connection already close 2!");
        }
    }

    public boolean deleteFavorite(int productID){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = db.delete(DBConfig.TABLE_FAVORITE, DBConfig.PRODUCT_ID + "=" + productID, null) > 0;
        return result;
    }

    public ArrayList<Product> getAllFavoriteProduct(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        ArrayList<Product> favoriteProductList = new ArrayList<>();
        try {
            cursor = db.rawQuery("SELECT *" +
                    " FROM " + DBConfig.TABLE_FAVORITE, null);
            if (cursor.moveToFirst()){
                while (cursor.isAfterLast() == false){
                    Product product = new Product();
                    product.setID(cursor.getInt(cursor.getColumnIndex(DBConfig.FAVORITE_ID)));
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
                    favoriteProductList.add(product);
                    cursor.moveToNext();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            cursor.close();
            db.close();
        }
        return favoriteProductList;
    }
}

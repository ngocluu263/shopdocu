package com.swd2015.shopdocu.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.swd2015.shopdocu.Model.DTO.Favorite;
import com.swd2015.shopdocu.Model.DTO.Product;
import com.swd2015.shopdocu.Model.Util.DBConfig;
import com.swd2015.shopdocu.Model.Util.DBHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

    public void addFavorite(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(DBConfig.FAVORITE_ID, favorite.getID());
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

        db.insert(DBConfig.TABLE_FAVORITE, null, values);
        db.close();
    }
}

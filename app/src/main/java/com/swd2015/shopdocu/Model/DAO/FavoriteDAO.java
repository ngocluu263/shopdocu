package com.swd2015.shopdocu.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.swd2015.shopdocu.Model.DTO.Favorite;
import com.swd2015.shopdocu.Model.Util.DBConfig;
import com.swd2015.shopdocu.Model.Util.DBHandler;

/**
 * Created by Quang on 14-Nov-15.
 */
public class FavoriteDAO extends DBHandler {

    public FavoriteDAO(Context context) {
        super(context);
    }

    public void addFavorite(Favorite favorite){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConfig.FAVORITE_ID, favorite.getID());
        values.put(DBConfig.FAVORITE_PRODUCT_ID, favorite.getProductID());

        db.insert(DBConfig.TABLE_FAVORITE, null, values);
        db.close();
    }
}

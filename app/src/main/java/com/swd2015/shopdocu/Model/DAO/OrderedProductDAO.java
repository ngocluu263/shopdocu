package com.swd2015.shopdocu.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.swd2015.shopdocu.Model.DTO.OrderedProduct;
import com.swd2015.shopdocu.Model.Util.DBConfig;
import com.swd2015.shopdocu.Model.Util.DBHandler;

/**
 * Created by Quang on 14-Nov-15.
 */
public class OrderedProductDAO extends DBHandler {

    public OrderedProductDAO(Context context, String name,
                             SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBConfig.DATABASE_NAME, factory, DBConfig.DATABASE_VERSION);
    }

    public void addOrderedProduct(OrderedProduct order){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConfig.ORDER_ID, order.getOrderID());
        values.put(DBConfig.PRODUCT_ID, order.getID());
        values.put(DBConfig.PRODUCT_NAME, order.getName());
        values.put(DBConfig.PRODUCT_PRICE, order.getPrice());
        values.put(DBConfig.PRODUCT_DESCRIPTION, order.getDescription());
        values.put(DBConfig.PRODUCT_CATEGORY, order.getCategory());
        values.put(DBConfig.PRODUCT_STATUS, order.getStatus());
        values.put(DBConfig.PRODUCT_CREATEDATE, order.getCategory());
        values.put(DBConfig.PRODUCT_IMAGE, order.getImage());
        values.put(DBConfig.ORDER_QUANTITY, order.getQuantity());

        db.insert(DBConfig.TABLE_ORDERED_PRODUCT, null, values);
        db.close();
    }
}

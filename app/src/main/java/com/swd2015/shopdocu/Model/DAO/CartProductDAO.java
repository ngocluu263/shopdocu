package com.swd2015.shopdocu.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.swd2015.shopdocu.Model.DTO.CartProduct;
import com.swd2015.shopdocu.Model.Util.DBConfig;
import com.swd2015.shopdocu.Model.Util.DBHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Quang on 14-Nov-15.
 */
public class CartProductDAO extends DBHandler {

    public CartProductDAO(Context context) {
        super(context);
    }

    public int numberOfRecord() {
        String query = "SELECT  * FROM " + DBConfig.TABLE_CART_PRODUCT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public boolean isOrderExist(int productID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + DBConfig.TABLE_CART_PRODUCT +
                " where " + DBConfig.PRODUCT_ID + " = " + productID;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public void addOrderedProduct(CartProduct order){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();



//        values.put(DBConfig.ORDER_ID, incrementingID);
//        values.put(DBConfig.PRODUCT_ID, order.getID());
        values.put(DBConfig.PRODUCT_NAME, order.getName());
        values.put(DBConfig.PRODUCT_PRICE, order.getPrice());
        values.put(DBConfig.PRODUCT_DESCRIPTION, order.getDescription());
        values.put(DBConfig.PRODUCT_CATEGORY, order.getCategory());
        values.put(DBConfig.PRODUCT_STATUS, order.getStatus());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String date = df.format(order.getCreateDate());
        values.put(DBConfig.PRODUCT_CREATEDATE, date);
        values.put(DBConfig.PRODUCT_IMAGE, order.getImage());


        if (isOrderExist(order.getID())){
            values.put(DBConfig.ORDER_QUANTITY,getQuantity(order.getID()) + 1);
            db.update(DBConfig.TABLE_CART_PRODUCT, values,
                    DBConfig.PRODUCT_ID + "=" + order.getID(), null);
        } else {
            int incrementingID = numberOfRecord();
            if(incrementingID != 0){
                incrementingID++;
            }
            values.put(DBConfig.ORDER_ID, incrementingID);
            values.put(DBConfig.PRODUCT_ID, order.getID());
            values.put(DBConfig.ORDER_QUANTITY, 1);
            db.insert(DBConfig.TABLE_CART_PRODUCT, null, values);
        }

        db.close();
    }

    public int getQuantity(int productID) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        int quantity = 0;
        try {
            cursor = db.rawQuery("SELECT " + DBConfig.ORDER_QUANTITY +
                    " FROM " + DBConfig.TABLE_CART_PRODUCT +
                    " WHERE " + DBConfig.PRODUCT_ID + "=?", new String[] {productID + ""});
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                quantity = cursor.getInt(cursor.getColumnIndex(DBConfig.ORDER_QUANTITY));
            }
            return quantity;
        } finally {
            cursor.close();
//            db.close();
        }
    }

    public ArrayList<CartProduct> getAllCart(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        ArrayList<CartProduct> cartProductList = new ArrayList<CartProduct>();
        try {
            cursor = db.rawQuery("SELECT *" +
                    " FROM " + DBConfig.TABLE_CART_PRODUCT, null);
            if (cursor.moveToFirst()){
                CartProduct product = new CartProduct();
                while (cursor.isAfterLast() == false){
                    product.setOrderID(cursor.getInt(cursor.getColumnIndex(DBConfig.ORDER_ID)));
                    product.setID(cursor.getInt(cursor.getColumnIndex(DBConfig.PRODUCT_ID)));
                    product.setName(cursor.getString(cursor.getColumnIndex(DBConfig.PRODUCT_NAME)));
                    product.setCategory(cursor.getInt(cursor.getColumnIndex(DBConfig.PRODUCT_CATEGORY)));
                    product.setStatus(cursor.getString(cursor.getColumnIndex(DBConfig.PRODUCT_STATUS)));
                    product.setImage(cursor.getString(cursor.getColumnIndex(DBConfig.PRODUCT_IMAGE)));
                    product.setPrice(cursor.getFloat(cursor.getColumnIndex(DBConfig.PRODUCT_PRICE)));
                    product.setQuantity(cursor.getInt(cursor.getColumnIndex(DBConfig.ORDER_QUANTITY)));
                    cartProductList.add(product);
                    cursor.moveToNext();
                }
            }
        } finally {
            cursor.close();
            db.close();
        }
        return cartProductList;
    }

    public boolean deleteCart(int productID){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = db.delete(DBConfig.TABLE_CART_PRODUCT, DBConfig.PRODUCT_ID + "=" + productID, null) > 0;
        db.close();
        return result;
    }

    public void updateCartQuantity(int productID, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();

        if (isOrderExist(productID)){
            ContentValues values = new ContentValues();
            values.put(DBConfig.ORDER_QUANTITY, quantity);
            db.update(DBConfig.TABLE_CART_PRODUCT, values,
                    DBConfig.PRODUCT_ID + "=" + productID, null);
        }
        db.close();
    }
}

package com.swd2015.shopdocu.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.swd2015.shopdocu.Model.DTO.Customer;
import com.swd2015.shopdocu.Model.Util.DBConfig;
import com.swd2015.shopdocu.Model.Util.DBHandler;

/**
 * Created by quangphuong on 12/3/15.
 */
public class UserDAO extends DBHandler {
    public UserDAO(Context context) {
        super(context);
    }

    public int numberOfRecord() {
        String query = "SELECT  * FROM " + DBConfig.TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }

    public boolean isLogin(){
        if(numberOfRecord() > 0){
            return true;
        }
        return false;
    }

    public void addUserInfo(Customer customer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConfig.USER_ID,customer.getID());
        values.put(DBConfig.USER_NAME,customer.getName());
        values.put(DBConfig.USER_EMAIL,customer.getEmail());
        db.insert(DBConfig.TABLE_USER, null, values);
        db.close();
    }

    public Customer getUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        Customer customer = null;
        try {
            cursor = db.rawQuery("SELECT *" +
                    " FROM " + DBConfig.TABLE_USER, null);
            if (cursor.moveToFirst()){
                while (cursor.isAfterLast() == false){
                    customer = new Customer();
                    customer.setID(cursor.getInt(cursor.getColumnIndex(DBConfig.USER_ID)));
                    customer.setName(cursor.getString(cursor.getColumnIndex(DBConfig.USER_NAME)));
                    //customer.setAddress(cursor.getString(cursor.getColumnIndex(DBConfig.USER_ADDRESS)));
                   // customer.setBirthday(cursor.getString(cursor.getColumnIndex(DBConfig.USER_BIRTHDAY)));
                   // customer.setGender(cursor.getString(cursor.getColumnIndex(DBConfig.USER_GENDER)));
                   // customer.setEmail(cursor.getString(cursor.getColumnIndex(DBConfig.USER_EMAIL)));
                   // customer.setPhone(cursor.getString(cursor.getColumnIndex(DBConfig.USER_PHONE)));
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
            db.close();
        }
        return customer;
    }

    //remove user when logout
    public boolean deleteUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = db.delete(DBConfig.TABLE_USER,null,null) >0;
        db.close();
        return result;
    }
//    public boolean isUserExist(int userID) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "Select * from " + DBConfig.TABLE_USER +
//                " where " + DBConfig.USER_ID + " = " + userID;
//        Cursor cursor = db.rawQuery(query, null);
//        if(cursor.getCount() <= 0){
//            cursor.close();
//            return false;
//        }
//        cursor.close();
//        return true;
//    }
//
//    public void updateCartQuantity(Customer customer){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        if (isUserExist(customer.getID())){
//            ContentValues values = new ContentValues();
//            values.put(DBConfig.ORDER_QUANTITY, quantity);
//            db.update(DBConfig.TABLE_CART_PRODUCT, values,
//                    DBConfig.USER_ID + "=" + customer.getID(), null);
//        }
//        db.close();
//    }
}

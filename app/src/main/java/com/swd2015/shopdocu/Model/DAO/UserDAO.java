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
        values.put(DBConfig.USER_GENDER,customer.getGender());
        values.put(DBConfig.USER_EMAIL,customer.getEmail());
        values.put(DBConfig.USER_ADDRESS,customer.getAddress());
        values.put(DBConfig.USER_PHONE,customer.getPhone());
        values.put(DBConfig.USER_PASSWORD,customer.getPassword());

        db.insert(DBConfig.TABLE_USER, null, values);
        db.close();
    }
}

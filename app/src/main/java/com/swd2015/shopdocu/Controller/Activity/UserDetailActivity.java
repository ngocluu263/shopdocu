package com.swd2015.shopdocu.Controller.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_UserDetail;
import com.swd2015.shopdocu.Controller.Service.UserDetailService;
import com.swd2015.shopdocu.R;

import org.apache.http.client.utils.URLEncodedUtilsHC4;

import java.util.ArrayList;

/**
 * Created by SherHolmes
 */
public class UserDetailActivity extends AppCompatActivity {
    public ImageView imageView;
    public TextView txtFullname;
    public EditText edTxtEmail;
    public EditText edTxtPhone;
    public EditText edTxtDOB;
    public EditText edTxtAddress;
    public String gender;
    public RadioButton rdMale;
    public RadioButton rdFemale;
    public RadioButton rdOther;

    public JSON_UserDetail userDetail;

    public String UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fragment__user_detail);
        UserDetailService userDetailService = new UserDetailService(this);
        userDetailService.getUserDetail(3);
        ControlEvent();
    }

    protected void ControlEvent(){
        //imageView = (ImageView) findViewById(R.id.imageView_avatar);

        txtFullname = (TextView) findViewById(R.id.FullName);
        //txtFullname.setText(userDetail.getFullname());
        edTxtEmail = (EditText) findViewById(R.id.editText_Email);
        //edTxtEmail.setText(userDetail.getEmail());
        edTxtPhone = (EditText) findViewById(R.id.editText_Phone);
       // edTxtPhone.setText(userDetail.getPhone());
        edTxtDOB = (EditText) findViewById(R.id.editText_DOB);
        //edTxtDOB.setText(userDetail.getDOB());
        edTxtAddress = (EditText) findViewById(R.id.editText_Address);
       // edTxtAddress.setText(userDetail.getAddress());
        rdMale = (RadioButton) findViewById(R.id.rdMale);
        rdFemale = (RadioButton) findViewById(R.id.rdFemale);
        rdOther = (RadioButton) findViewById(R.id.rdOther);
        //gender = userDetail.getGender();

//        if (gender.equalsIgnoreCase("Male")){
//            rdMale.isChecked();
//        } else if (gender.equalsIgnoreCase("Female")){
//            rdFemale.isChecked();
//        } else rdOther.isChecked();
    }
}

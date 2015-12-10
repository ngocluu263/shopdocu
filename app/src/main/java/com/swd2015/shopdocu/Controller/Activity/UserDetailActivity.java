package com.swd2015.shopdocu.Controller.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_UserDetail;
import com.swd2015.shopdocu.Controller.Service.UserDetailService;
import com.swd2015.shopdocu.Controller.Util.Validator;
import com.swd2015.shopdocu.R;

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

        int userID=1;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             userID = extras.getInt("UserID");
        }
        userDetailService.getUserDetail(userID);
        ControlEvent();

    }

    protected void ControlEvent(){
        //imageView = (ImageView) findViewById(R.id.imageView_avatar);

        txtFullname = (TextView) findViewById(R.id.FullName);

        edTxtEmail = (EditText) findViewById(R.id.editText_Email);
//        if (edTxtEmail.getText().toString().length() == 0 && edTxtEmail.getText().toString().length() == 10){
//            edTxtEmail.setError("Ngu nhu cho");
//        }
        edTxtPhone = (EditText) findViewById(R.id.editText_Phone);
        edTxtDOB = (EditText) findViewById(R.id.editText_DOB);
        edTxtAddress = (EditText) findViewById(R.id.editText_Address);
        rdMale = (RadioButton) findViewById(R.id.rdMale);
        rdFemale = (RadioButton) findViewById(R.id.rdFemale);
        rdOther = (RadioButton) findViewById(R.id.rdOther);

        Validator v = new Validator();
        System.out.println(v.checkAddress("abcbksjhdkh09808098"));
        System.out.println(v.checkAddress("ajshd askd 098 !#@#"));
        System.out.println(v.checkAddress("jjhhgjh gjhgjh"));
        System.out.println(v.checkAddress("!@#$%"));
        System.out.println(v.checkAddress(""));
        System.out.println(v.checkAddress("  kjshkhd  kjshkj 9873947"));
        System.out.println(v.checkAddress(" kjhskdf kjh "));
        System.out.println(v.checkAddress("aksjhdkah "));
        System.out.println(v.checkAddress("1723676218 "));
        System.out.println(v.checkAddress("2323802984"));
        System.out.println(v.checkAddress("dfsdfsdffg"));

    }


}

package com.swd2015.shopdocu.Controller.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cloudinary.Cloudinary;
import com.cloudinary.android.Utils;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSONUserObject;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_UserDetail;
import com.swd2015.shopdocu.Controller.JSON.JSONUtil.JSONPost;
import com.swd2015.shopdocu.Controller.JSON.JSONUtil.JSONTask;
import com.swd2015.shopdocu.Controller.Service.UserDetailService;
import com.swd2015.shopdocu.Controller.Service.ValidateService;
import com.swd2015.shopdocu.Controller.Task.UploadTask;
import com.swd2015.shopdocu.Controller.Util.Validator;
import com.swd2015.shopdocu.R;

import android.net.Uri;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

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
    public EditText edTxtName;
    public Button btn_Update;
    private Uri imageUri;
    private static final int PICK_IMAGE = 2;
    public ImageView avatar;

    public JSON_UserDetail userDetail;

    public UserDetailActivity userDetailActivity;

    public Context context;

    int userID=36;

    public static AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fragment__user_detail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userID = extras.getInt("UserID");
        }
        UserDetailService userDetailService = new UserDetailService(this);

        int userID=1;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             userID = extras.getInt("UserID");
        }

        userDetailService.getUserDetail(userID);
        builder = new AlertDialog.Builder(this);
        ControlEvent();

        addUpdateButtonListener();
        addBackButtonListener();
        checkUpdate();
        updateAvatar();
    }

    protected void ControlEvent(){
        imageView = (ImageView) findViewById(R.id.imageView_avatar);

        avatar = (ImageView) findViewById(R.id.imageView_avatar);

        txtFullname = (TextView) findViewById(R.id.FullName);

        edTxtName = (EditText) findViewById(R.id.editText_Name);
        edTxtEmail = (EditText) findViewById(R.id.editText_Email);

        edTxtPhone = (EditText) findViewById(R.id.editText_Phone);
        edTxtDOB = (EditText) findViewById(R.id.editText_DOB);
        edTxtAddress = (EditText) findViewById(R.id.editText_Address);
        rdMale = (RadioButton) findViewById(R.id.rdMale);
        rdFemale = (RadioButton) findViewById(R.id.rdFemale);
        rdOther = (RadioButton) findViewById(R.id.rdOther);

        btn_Update = (Button) findViewById(R.id.bt_Update);
    }

    public void addUpdateButtonListener(){
        Button updateButton = (Button) findViewById(R.id.bt_Update);
        final UserDetailActivity that = this;
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateService val = new ValidateService();
                String name = edTxtName.getText().toString();
                String email = edTxtEmail.getText().toString();
                String phone = edTxtPhone.getText().toString();
                String dob = edTxtDOB.getText().toString();
                String dd = "";
                String mm = "";
                String yy = "";
                if (dob.length() == 10) {
                    dd = dob.substring(0, 3);
                    mm = dob.substring(3, 6);
                    yy = dob.substring(6, 10);
                } else if (dob.length() == 8) {
                    dd = dob.substring(0, 2);
                    mm = dob.substring(2, 4);
                    yy = dob.substring(4, 8);
                }
                String address = edTxtAddress.getText().toString();
                String gender = "";
                if (rdMale.isChecked()) {
                    gender = "Male";
                } else if (rdFemale.isChecked()) {
                    gender = "Female";
                } else gender = "Other";

                if (val.checkName(name).equalsIgnoreCase("Name is valid") &&
                        val.checkEmail(email).equalsIgnoreCase("Email is valid") &&
                        val.checkPhone(phone).equalsIgnoreCase("Phone number is valid") &&
                        val.checkDateOfBirth(mm + dd + yy).equalsIgnoreCase("Date of birth is valid") &&
                        val.checkAddress(address).equalsIgnoreCase("Address is valid")) {

                    Cloudinary cloudinary = new Cloudinary(Utils.cloudinaryUrlFromContext(getApplicationContext()));
                    String imageName = cloudinary.randomPublicId();
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(imageUri);
                        UploadTask uploadTask = new UploadTask(getApplicationContext(), inputStream);
                        uploadTask.execute(imageName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String imageURL = "";
                    imageURL = cloudinary.url().generate(imageName);
                    JSON_UserDetail userDetail = new JSON_UserDetail(userID, name, email, phone, dob, address, gender, imageURL);
                    JSONUserObject jsonUserObject = new JSONUserObject(userDetail, imageURL);
                    UserDetailService userDetailService = new UserDetailService(that);
                    userDetailService.updateUser(jsonUserObject);
                } else {
                    btn_Update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent reView = new Intent(v.getContext(), UserDetailActivity.class);
                            startActivity(reView);
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(userDetailActivity);
                            builder1.setMessage("Wrong on some fields");

                            builder1.setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                            builder1.create().show();
                            finish();
                        }
                    });
                }
            }
        });
    }

    public void addBackButtonListener() {
        ImageButton backButton = (ImageButton) findViewById(R.id.bt_Back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeView = new Intent(v.getContext(), HomePageActivity.class);
                startActivity(homeView);
                finish();
            }
        });
    }

    public void checkUpdate(){
        final ValidateService val = new ValidateService();
        edTxtName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = edTxtName.getText().toString();
                if (!hasFocus && val.checkName(value).equalsIgnoreCase("Name is invalid")){
                    edTxtName.setError("Name contains 1-50 letters(0-9, a-z) without space");
                    btn_Update.setEnabled(false);
                } else
                btn_Update.setEnabled(true);
            }
        });

        edTxtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = edTxtEmail.getText().toString();
                if (!hasFocus && val.checkEmail(value).equalsIgnoreCase("Email is invalid")){
                    edTxtEmail.setError("Email contains 1-50 letters (0-9, a-z): example@example.exp");
                    btn_Update.setEnabled(false);
                } else
                    btn_Update.setEnabled(true);
            }
        });

        edTxtPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = edTxtPhone.getText().toString();
                if (!hasFocus && val.checkPhone(value).equalsIgnoreCase("Phone number is invalid")){
                    edTxtPhone.setError("Phone is number and 1-20 numbers");
                    btn_Update.setEnabled(false);
                } else
                    btn_Update.setEnabled(true);
            }
        });

        edTxtDOB.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = edTxtDOB.getText().toString();
                if (!hasFocus && val.checkDateOfBirth(value).equalsIgnoreCase("Date of birth is invalid")){
                    edTxtDOB.setError("Date of birth form is dd/MM/YYYY");
                    btn_Update.setEnabled(false);
                } else
                btn_Update.setEnabled(true);
            }
        });

        edTxtAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String value = edTxtAddress.getText().toString();
                if (!hasFocus && val.checkAddress(value).equalsIgnoreCase("Address is invalid")) {
                    edTxtAddress.setError("Address is required and contains 1-250 letters");
                    btn_Update.setEnabled(false);
                } else
                    btn_Update.setEnabled(true);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        imageUri = data.getData();
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Glide.with(getApplicationContext()).load(imageUri).override(70, 70).centerCrop()
                    .placeholder(R.drawable.ic_shopping_cart) // optional
                    .error(R.drawable.ic_close_search)         // optional
                    .into(avatar);
        }
    }

    private void selectImageFromGallery() {
        // Create intent to Open Image applications like Gallery, Google Photos
        try {
            Intent intent = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(),
                    Toast.LENGTH_LONG).show();
            Log.e(e.getClass().getName(), e.getMessage(), e);
        }
    }

    public void updateAvatar(){
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImageFromGallery();
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}



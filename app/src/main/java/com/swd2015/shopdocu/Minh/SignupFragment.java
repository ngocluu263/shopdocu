package com.swd2015.shopdocu.Minh;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.swd2015.shopdocu.Controller.Security.MD5Encrypt;
import com.swd2015.shopdocu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {
    EditText edtName,edtEmail,edtPass;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_signup, container, false);
        edtName=(EditText)v.findViewById(R.id.edtName);
        edtEmail=(EditText)v.findViewById(R.id.edtEmail);
        edtPass=(EditText)v.findViewById(R.id.edtPassword);

        AppCompatActivity activity=(AppCompatActivity)getActivity();
        android.support.v7.app.ActionBar actionBar=activity.getSupportActionBar();

        actionBar.setTitle("Đăng kí tài khoản");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);

        edtPass.setText(MD5Encrypt.encryptMD5(edtPass.getText().toString()));


        return v;
    }


}

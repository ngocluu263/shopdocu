package com.swd2015.shopdocu.Controller.Fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Customer;
import com.swd2015.shopdocu.Controller.Service.CustomerService;
import com.swd2015.shopdocu.Controller.Activity.HomePageActivity;
import com.swd2015.shopdocu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {
    EditText edtName,edtEmail,edtPass;
    Button btnCreateAccount;
    public JSON_Customer customer;
    public CustomerService customerService=new CustomerService(this);
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
        edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());


       // getActivity().getClass().getSimpleName();
        HomePageActivity activity=(HomePageActivity)getActivity();
        android.support.v7.app.ActionBar actionBar=activity.actionBar;
        ActionBarDrawerToggle toggle=activity.actionBarDrawerToggle;
        toggle.setDrawerIndicatorEnabled(false);

        actionBar.setTitle("Đăng kí tài khoản");
        actionBar.setHomeButtonEnabled(true);


        btnCreateAccount = (Button) v.findViewById(R.id.btnSignup);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                customerService.createAccount(edtEmail.getText().toString(),
                        edtPass.getText().toString(), edtName.getText().toString().replace(' ', '_'));
            }
        });


        //actionBar.setDisplayHomeAsUpEnabled(true);

       // edtPass.setText(MD5Encrypt.encryptMD5(edtPass.getText().toString()));


        return v;
    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
//        builder.setTitle("Thông báo");
//        // builder.setIcon(R.drawable.koala );
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                FragmentManager fragmentManager= getActivity().getFragmentManager();
//                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//                LoginFragment loginFragment = new LoginFragment();
//                Bundle bundle=new Bundle();
//                bundle.putString("email",customer.getEmail());
//                loginFragment.setArguments(bundle);
//
//                fragmentTransaction.replace(R.id.main,loginFragment).commit();
//
//            }
//        });
//        builder.setMessage("Bạn đã đăng nhập thành công");
//        return builder.create();
//    }
}
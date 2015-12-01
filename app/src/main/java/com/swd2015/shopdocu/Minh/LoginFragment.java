package com.swd2015.shopdocu.Minh;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Customer;
import com.swd2015.shopdocu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    EditText edtEmail,edtPassword;
    CheckBox checkPass;
    TextView resetPass,signup;
    public JSON_Customer user;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_login, container, false);
        //region Hide/Unhide password
        edtEmail=(EditText)v.findViewById(R.id.edtEmail);
        edtPassword=(EditText)v.findViewById(R.id.edtPassword);
        edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        checkPass=(CheckBox) v.findViewById(R.id.chkShowPassword);
        checkPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //show pass
                if (isChecked) {
                    edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                //hide password
                else {
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        //endregion

        signup=(TextView) v.findViewById(R.id.txtSignUp);
        resetPass = (TextView) v.findViewById(R.id.txtForgetPass);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                SignupFragment signupFragment= new SignupFragment();
                fragmentTransaction.replace(R.id.loginFragment, signupFragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return v;
    }
}

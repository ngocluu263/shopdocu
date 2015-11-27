package com.swd2015.shopdocu.Minh;


import android.app.Fragment;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.swd2015.shopdocu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    EditText edtEmail,edtPassword;
    CheckBox checkPass;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_login, container, false);
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


        return v;
    }
}

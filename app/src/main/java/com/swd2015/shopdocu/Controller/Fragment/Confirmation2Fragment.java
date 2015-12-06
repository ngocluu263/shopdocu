package com.swd2015.shopdocu.Controller.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.swd2015.shopdocu.R;

/**
 * Created by quangphuong on 12/3/15.
 */
public class Confirmation2Fragment extends Fragment {
    public static Button backToHomeButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View confirmation2View = inflater.inflate(R.layout.fragment_confirmation_2, container, false);

        backToHomeButton = (Button)confirmation2View.findViewById(R.id.back_to_home_button);

        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return confirmation2View;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}

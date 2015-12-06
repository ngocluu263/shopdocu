package com.swd2015.shopdocu.Controller.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swd2015.shopdocu.R;

/**
 * Created by quangphuong on 11/28/15.
 */
public class ShoppingCartFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View shoppingCartView = inflater.inflate(R.layout.fragment_shoppingcart, container, false);

        return shoppingCartView;
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

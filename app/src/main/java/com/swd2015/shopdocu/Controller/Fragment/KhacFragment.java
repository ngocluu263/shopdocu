package com.swd2015.shopdocu.Controller.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.Controller.Util.CategoryEnum;
import com.swd2015.shopdocu.Controller.Util.Object.CategoryID;
import com.swd2015.shopdocu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KhacFragment extends android.support.v4.app.Fragment {

    public GridView gridResult;
    public KhacFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_ban_ghe_caphe, container, false);
        gridResult=(GridView)v.findViewById(R.id.grvSearchResult);
        android.support.v7.app.ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Danh mục khác");
        ProductService productService=new ProductService(this);
        productService.getSearchedProducts(CategoryID.GetCategoryID(CategoryEnum.KHAC));
        return v;
    }



}

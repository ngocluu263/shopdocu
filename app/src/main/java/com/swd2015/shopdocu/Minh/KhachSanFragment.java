package com.swd2015.shopdocu.Minh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.R;


public class KhachSanFragment extends android.support.v4.app.Fragment {
    public GridView gridResult;
    public KhachSanFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_ban_ghe_caphe, container, false);
        gridResult=(GridView)v.findViewById(R.id.grvSearchResult);
        android.support.v7.app.ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Đồ khách sạn");
        ProductService productService=new ProductService(this);
        productService.getSearchedProducts(CategoryID.GetCategoryID(CategoryEnum.DO_KHACH_SAN));
        return v;
    }
}

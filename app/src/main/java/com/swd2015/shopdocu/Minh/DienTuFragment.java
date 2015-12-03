package com.swd2015.shopdocu.Minh;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DienTuFragment extends android.support.v4.app.Fragment {
    public GridView gridResult;

    public DienTuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_ban_ghe_caphe, container, false);
        gridResult=(GridView)v.findViewById(R.id.grvSearchResult);

        ProductService productService=new ProductService(this);
        productService.getSearchedProducts(CategoryID.GetCategoryID(CategoryEnum.DIENTU_DIENMAY));
        return v;
    }


}

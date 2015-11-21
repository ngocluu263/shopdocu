package com.swd2015.shopdocu.Minh;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swd2015.shopdocu.Modal.DTO.Product;
import com.swd2015.shopdocu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePage_Fragment extends Fragment {


    public HomePage_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_home_page, container, false);
        RecyclerView recyclerView= (RecyclerView) v.findViewById(R.id.recyclerView);
        RecyclerView recyclerViewHotProduct= (RecyclerView) v.findViewById(R.id.viewHotProduct);

        //listProduct tra ve tu JSON
        List<Product> listProduct=new ArrayList<Product>();

        List<ProductForAdapter> listData= new ArrayList<ProductForAdapter>();
        ProductForAdapter productForAdapter;
        for(Product product:listProduct ){
            productForAdapter=new ProductForAdapter(product.getName(), changePrice(product.getPrice()),R.drawable.home_icon);
            listData.add(productForAdapter);
        }

        //New product
        ProductAdapter productAdapter=new ProductAdapter(getActivity().getApplicationContext(),listData);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(productAdapter);

        //Hot product
        recyclerViewHotProduct.setLayoutManager(layoutManager);
        recyclerView.setAdapter(productAdapter);

        return v;
    }

    //ham them dau . cho 1 chuoi so dai
    private String changePrice(Float price) {
        String sPrice=String.valueOf(price);

        return sPrice;
    }


}

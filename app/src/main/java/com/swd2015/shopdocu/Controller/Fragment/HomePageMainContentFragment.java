package com.swd2015.shopdocu.Controller.Fragment;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swd2015.shopdocu.Controller.Adapter.ProductAdapter;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Product;
import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.Controller.Util.Object.ProductForAdapter;
import com.swd2015.shopdocu.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageMainContentFragment extends android.support.v4.app.Fragment {
    public List<JSON_Product> listProduct;
    public ProductService productService=new ProductService(this);
    public LinearLayoutManager layoutManager;
    public LinearLayoutManager layoutManager2;
    public ProductForAdapter productForAdapter;
    public ProductForAdapter hotProductForAdapter;
    public ProductAdapter newProductAdapter;
    public  ProductAdapter hotProductAdapter;
    public List<ProductForAdapter> listDataHotProduct;
    public List<ProductForAdapter> listDataNewProduct;
    public RecyclerView recyclerView;
    public RecyclerView recyclerViewHotProduct;
    private static View v;
    public FragmentActivity fragmentActivity;
    public HomePageMainContentFragment() {
        // Required empty public constructor
    }
    public void onAttach(Activity activity) {
        fragmentActivity= (FragmentActivity) activity;
        super.onAttach(fragmentActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
         v=inflater.inflate(R.layout.fragment_home_page_main_content, container, false);
        recyclerView= (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerViewHotProduct= (RecyclerView) v.findViewById(R.id.viewHotProduct);

        BannerFragment bannerFragment=new BannerFragment();
        FragmentManager fragmentManager= fragmentActivity.getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.banner,bannerFragment);
        fragmentTransaction.commit();

        android.support.v7.app.ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("SHOPDOCU.VN");

        productService.getNewProducts();
        productService.getHotProduct();
        return v;
    }


}

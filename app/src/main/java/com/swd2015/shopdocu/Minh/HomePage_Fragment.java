package com.swd2015.shopdocu.Minh;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Product;
import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePage_Fragment extends Fragment {
    public List<JSON_Product> listProduct;
    public ProductService productService=new ProductService(this);
    public LinearLayoutManager layoutManager;
    public LinearLayoutManager layoutManager2;
    public ProductForAdapter productForAdapter;
    public  ProductAdapter newProductAdapter;
    public  ProductAdapter hotProductAdapter;
    public List<ProductForAdapter> listDataHotProduct;
    public List<ProductForAdapter> listDataNewProduct;
    public RecyclerView recyclerView;
    public RecyclerView recyclerViewHotProduct;
    public HomePage_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_home_page, container, false);
        recyclerView= (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerViewHotProduct= (RecyclerView) v.findViewById(R.id.viewHotProduct);

        //listProduct tra ve tu JSON
        productService.getNewProducts();
        //Product p=new Product(1,"Ban ghe",100000.0F,"a",1,null,"");
      //  listDataNewProduct= new ArrayList<ProductForAdapter>();
       // for(JSON_Product product:listProduct ){
       //     productForAdapter=new ProductForAdapter(product.getName(), changePrice(product.getPrice()),product.getImage().get(0).toString());
      //      listDataNewProduct.add(productForAdapter);
      //  }

        //New product
       // newProductAdapter=new ProductAdapter(getActivity().getApplicationContext(),listDataNewProduct);
       // layoutManager=new LinearLayoutManager(getActivity());
       // layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
      //  recyclerView.setLayoutManager(layoutManager);
      //  recyclerView.setAdapter(newProductAdapter);

        //Hot product
       // hotProductAdapter=new ProductAdapter(getActivity().getApplicationContext(),listDataNewProduct);
        //layoutManager2=new LinearLayoutManager(getActivity());
       // layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
       // recyclerViewHotProduct.setLayoutManager(layoutManager2);
       // recyclerView.setAdapter(hotProductAdapter);

        return v;
    }

    //ham them dau . cho 1 chuoi so dai
    private String changePrice(int price) {
        String sPrice=String.valueOf(price);

        return sPrice + " đ";
    }


}

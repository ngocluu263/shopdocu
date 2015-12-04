package com.swd2015.shopdocu.Controller.Task;

import android.os.AsyncTask;

import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.Model.DAO.SeenDAO;
import com.swd2015.shopdocu.Model.DTO.Product;

/**
 * Created by quangphuong on 12/2/15.
 */
public class SeenTask extends AsyncTask {
    ProductService productService;
    Product product;

    public SeenTask(ProductService productService, Product product){
        this.productService = productService;
        this.product = product;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        SeenDAO seenDAO = new SeenDAO(productService.activity.getBaseContext());
        seenDAO.addSeenProduct(product);
        return null;
    }
}

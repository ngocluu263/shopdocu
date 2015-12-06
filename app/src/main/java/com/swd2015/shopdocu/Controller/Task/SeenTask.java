package com.swd2015.shopdocu.Controller.Task;

import android.os.AsyncTask;

import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.Controller.Util.DBTask;
import com.swd2015.shopdocu.Model.DAO.SeenDAO;
import com.swd2015.shopdocu.Model.DTO.Product;

import java.util.List;

/**
 * Created by quangphuong on 12/2/15.
 */
public class SeenTask extends AsyncTask {
    ProductService productService;
    Product product;
    DBTask dbTask;

    public SeenTask(ProductService productService, DBTask dbTask) {
        this.productService = productService;
        this.dbTask = dbTask;
    }

    public SeenTask(ProductService productService, Product product, DBTask dbTask) {
        this.productService = productService;
        this.product = product;
        this.dbTask = dbTask;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        SeenDAO seenDAO = new SeenDAO(productService.activity.getBaseContext());
        switch (dbTask) {
            case ADD_SEEN_PRODUCT:
                seenDAO.addSeenProduct(product);
                break;
            case GET_SEEN_PRODUCT:
                List<Product> productList = seenDAO.getAllSeenProduct();
                productService.setAllSeenProduct(productList);
                break;
        }
        return null;
    }
}

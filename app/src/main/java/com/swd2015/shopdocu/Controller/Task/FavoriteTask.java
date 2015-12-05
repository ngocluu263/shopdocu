package com.swd2015.shopdocu.Controller.Task;

import android.os.AsyncTask;

import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.Controller.Util.DBTask;
import com.swd2015.shopdocu.Model.DAO.FavoriteDAO;
import com.swd2015.shopdocu.Model.DAO.SeenDAO;
import com.swd2015.shopdocu.Model.DTO.Product;

import java.util.List;

/**
 * Created by Quang on 5/12/2015.
 */
public class FavoriteTask extends AsyncTask {
    ProductService productService;

    Product product;
    DBTask dbTask;

    public FavoriteTask(ProductService productService, DBTask dbTask) {
        this.productService = productService;
        this.dbTask = dbTask;
    }

    public FavoriteTask(ProductService productService, Product product, DBTask dbTask) {
        this.productService = productService;
        this.product = product;
        this.dbTask = dbTask;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        FavoriteDAO favoriteDAO = new FavoriteDAO(productService.activity.getBaseContext());
        switch (dbTask) {
            case TOGGLE_FAVORITE:
                favoriteDAO.addFavorite(product);
                break;
            case GET_FAVORITE_PRODUCT:
                List<Product> productList = favoriteDAO.getAllFavoriteProduct();
                productService.setAllFavoriteProduct(productList);
                break;
        }
        return null;
    }
}

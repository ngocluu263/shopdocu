package com.swd2015.shopdocu.Controller.Activity;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.GridView;
import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.R;

/**
 * Created by quangphuong on 12/4/15.
 */
public class SeenProductActivity extends AppCompatActivity {
    ProductService productService = new ProductService(this);
    public static GridView gridView;

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_product_gridview);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));

        gridView = (GridView)findViewById(R.id.seen_product_list_view);
        productService.getAllSeenProduct();
    }

}

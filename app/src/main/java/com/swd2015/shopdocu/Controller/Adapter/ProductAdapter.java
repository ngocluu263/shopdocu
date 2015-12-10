package com.swd2015.shopdocu.Controller.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.swd2015.shopdocu.Controller.Activity.ProductDetailActivity;
import com.swd2015.shopdocu.Controller.Util.Object.ProductForAdapter;
import com.swd2015.shopdocu.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Minh on 11/21/2015.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    LayoutInflater inflater;
    Activity activity;
    private Context mContext;
    List<ProductForAdapter> listProduct= Collections.emptyList();

    public ProductAdapter(Context context, List<ProductForAdapter> data){
        listProduct=data;
        mContext=context;
        activity = (Activity) mContext;
        inflater=LayoutInflater.from(activity.getApplicationContext());
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= inflater.inflate(R.layout.product_homepage_layout,parent,false);
        ProductViewHolder productViewHolder = new ProductViewHolder(v);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final ProductForAdapter product = listProduct.get(position);

        ImageView productImageView = (ImageView) holder.image;
        Glide.with(mContext)
                .load(product.getImage())
                .placeholder(R.drawable.logo)
                .error(R.drawable.ic_close_search)
                .into(productImageView);
        productImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.price.setText(product.getPrice());
        holder.name.setText(product.getName());

        productImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProductDetail(product.getID());
            }
        });

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProductDetail(product.getID());
            }
        });
    }

    public void goToProductDetail(int productID){
        Intent intent = new Intent(mContext, ProductDetailActivity.class);
        intent.putExtra("productID",productID);
        activity.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        TextView price;

        public ProductViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.productImage);
            name= (TextView) itemView.findViewById(R.id.productName);
            price= (TextView) itemView.findViewById(R.id.productPrice);
        }
    }

}
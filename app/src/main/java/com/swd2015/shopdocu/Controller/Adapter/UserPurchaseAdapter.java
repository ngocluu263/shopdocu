package com.swd2015.shopdocu.Controller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.swd2015.shopdocu.Controller.Activity.UserPurchaseActivity;
import com.swd2015.shopdocu.Controller.Util.Object.User_PurchaseObj;
import com.swd2015.shopdocu.R;

import java.util.List;

/**
 * Created by SherHolmes
 */
public class UserPurchaseAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    List<User_PurchaseObj> user_purchaseObj;
    UserPurchaseActivity activity;
    UserPurchaseAdapterSon adapterSon;


    public UserPurchaseAdapter(Context context, List<User_PurchaseObj> user_purchaseObj){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.user_purchaseObj = user_purchaseObj;
        activity = (UserPurchaseActivity) context;
    }

    public int getCount() {
        if (user_purchaseObj != null) {
            return user_purchaseObj.size();
        }
        return 0;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v;


        if (convertView == null){
            v = layoutInflater.inflate(R.layout.layout_4listview_buy, null);

        } else {
            v = convertView;
        }

        TextView txtID = (TextView) v.findViewById((R.id.BuyID));

        ListView sellList = (ListView) v.findViewById(R.id.ProductBuyList);

        TextView txtSellDate = (TextView) v.findViewById(R.id.BuyDate);
        TextView txtOrderSellPrice = (TextView) v.findViewById(R.id.OrderBuyPrice);
        TextView txtOrderSellStatus = (TextView) v.findViewById(R.id.OrderBuyStatus);

            txtID.setText(String.valueOf(user_purchaseObj.get(position).getID()));

            String orderDate = user_purchaseObj.get(position).getDate().substring(0, 10);

            txtSellDate.setText(orderDate);
            txtOrderSellPrice.setText(user_purchaseObj.get(position).getPrice());

            String st = user_purchaseObj.get(position).getStatus();
            if (st.equalsIgnoreCase("từ chối thu mua")){
                st = "Giao dịch bị từ chối";
            } else if (st.equalsIgnoreCase("khách bán hủy yêu cầu")){
                st = "Người dùng đã hủy";
            } else if (st.equalsIgnoreCase("khách hàng đăng bán")){
                st = "Đang xử lý";
            } else if (st.equalsIgnoreCase("xác nhận thu mua")){
                st = "Đã xác nhận";
            } else if (st.equalsIgnoreCase("đã thu mua")){
                st = "Hoàn thành";
            }
            txtOrderSellStatus.setText(st);

//        if (adapterSon == null){

        adapterSon = new UserPurchaseAdapterSon(activity,user_purchaseObj.get(position).getProductName());
//        adapterSon.ProductName = user_soldsObj.get(position).getProductName()
        sellList.setAdapter(adapterSon);
//        }
//        else {
//
//            sellList.setAdapter(adapterSon);
//        }
//        Utility.setListViewHeightBasedOnChild(sellList);

        return v;
    }
}

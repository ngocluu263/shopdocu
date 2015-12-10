package com.swd2015.shopdocu.Controller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.swd2015.shopdocu.Controller.Activity.UserSoldActivity;
import com.swd2015.shopdocu.Controller.Util.FormatNameAndPrice;
import com.swd2015.shopdocu.Controller.Util.Object.User_SoldObj;
import com.swd2015.shopdocu.R;

import java.text.DecimalFormat;
import java.util.List;

import static com.swd2015.shopdocu.Controller.Util.FormatNameAndPrice.FormatPrice;

/**
 * Created by khiem on 12/2/2015.
 */
public class UserSoldAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    List<User_SoldObj> user_soldsObj;
    UserSoldActivity activity;
    UserSoldAdapterSon adapterSon;


    public UserSoldAdapter(Context context, List<User_SoldObj> user_soldsObj){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.user_soldsObj = user_soldsObj;
        activity = (UserSoldActivity) context;
    }

    public int getCount() {
        if (user_soldsObj != null) {
            return user_soldsObj.size();
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
            v = layoutInflater.inflate(R.layout.layout_4listview_sell, null);

        } else {
            v = convertView;
        }

        TextView txtID = (TextView) v.findViewById((R.id.SellID));

        ListView sellList = (ListView) v.findViewById(R.id.ProductSellList);

        TextView txtSellDate = (TextView) v.findViewById(R.id.SellDate);
        TextView txtOrderSellPrice = (TextView) v.findViewById(R.id.OrderSellPrice);
        TextView txtOrderSellStatus = (TextView) v.findViewById(R.id.OrderSellStatus);

            txtID.setText(String.valueOf(user_soldsObj.get(position).getID()));

            String orderDate = user_soldsObj.get(position).getDate().substring(0, 10);

            txtSellDate.setText(orderDate);

            String price = user_soldsObj.get(position).getPrice();
            DecimalFormat formatter = new DecimalFormat("#,###");
            String productPrice = formatter.format(Double.parseDouble(price));
            productPrice=productPrice.replace(',','.');
            txtOrderSellPrice.setText(productPrice);

            String st = user_soldsObj.get(position).getStatus();
            if (st.equalsIgnoreCase("từ chối bán")){
                st = "Hết hàng";
            } else if (st.equalsIgnoreCase("khách mua hủy yêu cầu")){
                st = "Đã hủy";
            } else if (st.equalsIgnoreCase("khách đặt mua")){
                st = "Đang xử lý";
            } else if (st.equalsIgnoreCase("Xác nhận bán")){
                st = "Đã xác nhận đơn hàng";
            } else if (st.equalsIgnoreCase("Đã bán")){
                st = "Hoàn thành";
            }

            txtOrderSellStatus.setText(st);

//        if (adapterSon == null){

        adapterSon = new UserSoldAdapterSon(activity,user_soldsObj.get(position).getProductName());
//        adapterSon.ProductName = user_soldsObj.get(position).getProductName()
        sellList.setAdapter(adapterSon);
//        }
//        Utility.setListViewHeightBasedOnChild(sellList);

        return v;
    }
}

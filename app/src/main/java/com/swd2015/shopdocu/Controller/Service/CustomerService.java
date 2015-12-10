package com.swd2015.shopdocu.Controller.Service;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.swd2015.shopdocu.Controller.Activity.HomePageActivity;
import com.swd2015.shopdocu.Controller.Activity.NavigationActivity;
import com.swd2015.shopdocu.Controller.Activity.UserPurchaseActivity;
import com.swd2015.shopdocu.Controller.Activity.UserSoldActivity;
import com.swd2015.shopdocu.Controller.Fragment.HomePage_Fragment;
import com.swd2015.shopdocu.Controller.Fragment.LoginFragment;
import com.swd2015.shopdocu.Controller.Fragment.RequestSellCustomerFragment;
import com.swd2015.shopdocu.Controller.Fragment.RequestSellFragment;
import com.swd2015.shopdocu.Controller.Fragment.SignupFragment;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Customer;
import com.swd2015.shopdocu.Controller.JSON.JSONTask.JSONCustomerTask;
import com.swd2015.shopdocu.Controller.JSON.JSONTask.JSONPostTask;
import com.swd2015.shopdocu.Controller.JSON.JSONUtil.JSONTask;
import com.swd2015.shopdocu.Controller.Util.Object.NavigationItem;
import com.swd2015.shopdocu.Model.DAO.UserDAO;
import com.swd2015.shopdocu.Model.DTO.Customer;
import com.swd2015.shopdocu.R;

/**
 * Created by Minh on 11/28/2015.
 */
public class CustomerService {
    Fragment fragment;
    android.support.v4.app.Fragment fragmentv4;
    Activity activity;

    /**
     * Constructor: CustomerService(Fragment fragment)
     *
     * Constructor
     *
     * ++PhucLHSE61219_20151203
     */
    public CustomerService(Fragment fragment) {
        this.fragment = fragment;
    }

    public CustomerService(android.support.v4.app.Fragment fragmentv4) {
        this.fragmentv4 = fragmentv4;
    }

    public CustomerService(Activity activity) {
        this.activity = activity;
    }
    /**
     * Method: getCustomerById(int ID)
     *
     * Get customer information by ID
     *
     * ++PhucLHSE61219_20151203
     */
    public void getCustomerById(int ID){
        JSONCustomerTask jsonCustomerTask = new JSONCustomerTask(this, JSONTask.GET_CUSTOMER_BY_ID,
                                                                                String.valueOf(ID));
        jsonCustomerTask.execute();
    }
    public void connectionError(){
        if (this.activity != null) {
            new AlertDialog.Builder(activity).
                    setMessage("Xin hãy kiểm tra lại kết nối của bạn!").
                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).
                    show();
        }else if(fragment != null) {
            new AlertDialog.Builder(fragment.getActivity()).
                    setMessage("Xin hãy kiểm tra lại kết nối của bạn!").
                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).
                    show();
        }  else {
            activity.finish();
        }

    }


    public void setCustomer(JSON_Customer customer) {
        if (fragmentv4!=null) {
            switch (fragmentv4.getClass().getSimpleName()) {
                case "RequestSellCustomerFragment":
                    if (customer != null) {
                        RequestSellCustomerFragment requestSellCustomerFragment =
                                (RequestSellCustomerFragment) fragmentv4;

                        //Get customer information and set to view (RequestSellCustomerFragment)
                        requestSellCustomerFragment.customerNameEditText
                                .setText(customer.getCustomerName());
                        requestSellCustomerFragment.customerAddressEditText
                                .setText(customer.getCustomerAddress());
                        requestSellCustomerFragment.customerEmailEditText
                                .setText(customer.getCustomerEmail());
                        requestSellCustomerFragment.customerPhoneNumberEditText
                                .setText(customer.getCustomerPhoneNumber());
								
								// Set cursor in the end of Edit Text
                    requestSellCustomerFragment.customerNameEditText.setSelection(customer.getCustomerName().length());
                    }
            }
        }
        else if(activity!=null){
            String activityName=activity.getClass().getSimpleName();
            //trang homepage dang goi
            if (activityName.equals("HomePageActivity")){

            if (customer!=null){// get user tu json thanh cong
                HomePageActivity homePageActivity=(HomePageActivity)activity;
                homePageActivity.userName.setText(customer.getCustomerName());

               // ImageView productImageView = (ImageView) holder.image;
                if (customer.getCustomerImageURL()!=null){
                    homePageActivity.avartar.setBackgroundResource(0);
                    Glide.with(homePageActivity.getBaseContext())
                            .load(customer.getCustomerImageURL())
                            .placeholder(R.drawable.logo)
                            .error(R.drawable.ic_close_search)
                            .into(homePageActivity.avartar);
                    homePageActivity.avartar.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
                //trang product detail goi
            }else {
                NavigationActivity navigationActivity = (NavigationActivity) activity;
                navigationActivity.userName.setText(customer.getCustomerName());

                // ImageView productImageView = (ImageView) holder.image;
                if (customer.getCustomerImageURL() != null) {
                    navigationActivity.avatar.setBackgroundResource(0);
                    Glide.with(navigationActivity.getBaseContext())
                            .load(customer.getCustomerImageURL())
                            .placeholder(R.drawable.logo)
                            .error(R.drawable.ic_close_search)
                            .into(navigationActivity.avatar);
                    navigationActivity.avatar.setScaleType(ImageView.ScaleType.FIT_XY);
                }

            }

        }
    }

    public void checkLogin(String email, String password){
        JSONPostTask jsonTask = new JSONPostTask(this, JSONTask.CHECK_LOGIN,email,password);
        jsonTask.execute();
    }

    public void setCheckLogin(JSON_Customer customer1){
        final JSON_Customer customer=customer1;
       final LoginFragment loginFragment=(LoginFragment) fragment;
        loginFragment.user=customer;
        //dang nhap thanh cong
        if (customer!=null){
            //add customer to DB local
            new AlertDialog.Builder(loginFragment.getActivity()).
                    setMessage("Đăng nhập thành công").
                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            UserDAO userDAO=new UserDAO(loginFragment.getActivity().getBaseContext());
                            int ID=customer.getCustomerID();
                            String email=customer.getCustomerEmail();
                            String name = customer.getCustomerName();
                            String avatarURL=customer.getCustomerImageURL();
                            Customer customerDBLocal=
                                    new Customer(ID,name,email);
                            userDAO.addUserInfo(customerDBLocal);

                            if (loginFragment.action!=null){
                                //ve trang dang ban

                                FragmentManager fragmentManager= loginFragment.getActivity().getFragmentManager();
                                String action=loginFragment.action;
                                switch (loginFragment.action){
                                    case "RequestSell":{
                                        Bundle bundle=new Bundle();
                                        bundle.putInt("UserID",ID);
                                        RequestSellFragment requestSellFragment = new RequestSellFragment();
                                        requestSellFragment.setArguments(bundle);
                                        fragmentManager.beginTransaction().replace(R.id.main,
                                                requestSellFragment).commit();
                                        break;
                                    }
                                    case "Homepage":{
                                        //Chinh sua lai thong tin user tren profilebox
                                        HomePageActivity homePageActivity=
                                                (HomePageActivity)loginFragment.getActivity();
                                        if (homePageActivity.listNavItems.size()<=5){
                                            homePageActivity.listNavItems.
                                                    add(new NavigationItem("Đăng xuất", R.drawable.ic_signout));
                                        }
                                        homePageActivity.userName.setText(name);
                                        if (avatarURL!=null){
                                            Glide.with(homePageActivity.getBaseContext())
                                                    .load(customer.getCustomerImageURL())
                                                    .placeholder(R.drawable.logo)
                                                    .error(R.drawable.ic_close_search)
                                                    .into(homePageActivity.avartar);
                                            homePageActivity.avartar.setScaleType(ImageView.ScaleType.FIT_XY);
                                        }
                                        //chuyen fragment
                                        fragmentManager.beginTransaction().replace(R.id.main,
                                                new HomePage_Fragment()).commit();
                                        break;
                                    }
                                    case "UserPurchase":{
                                        Intent intent = new Intent(loginFragment.getActivity(), UserPurchaseActivity.class);
                                        intent.putExtra("UserID", ID);
                                        loginFragment.getActivity().startActivity(intent);
                                        break;
                                    }
                                    case "UserSold":{
                                        Intent intent = new Intent(loginFragment.getActivity(), UserSoldActivity.class);
                                        intent.putExtra("UserID", ID);
                                        loginFragment.getActivity().startActivity(intent);
                                        break;
                                    }
                                    default:{ //ve trang chu
                                        HomePageActivity homePageActivity=
                                                (HomePageActivity)loginFragment.getActivity();
                                        if (homePageActivity.listNavItems.size()<=5){
                                            homePageActivity.listNavItems.
                                                    add(new NavigationItem("Đăng xuất", R.drawable.ic_signout));
                                        }
                                        homePageActivity.userName.setText(name);
                                        if (avatarURL!=null){
                                            Glide.with(homePageActivity.getBaseContext())
                                                    .load(customer.getCustomerImageURL())
                                                    .placeholder(R.drawable.logo)
                                                    .error(R.drawable.ic_close_search)
                                                    .into(homePageActivity.avartar);
                                            homePageActivity.avartar.setScaleType(ImageView.ScaleType.FIT_XY);
                                        }
                                        //chuyen fragment
                                        fragmentManager.beginTransaction().replace(R.id.main,
                                                new HomePage_Fragment()).commit();
                                        break;
                                    }
                                }
                            }
                        }
                    }).
                    show();
        }
        //TH dang nhap ko thanh cong
        else
        {
            //pop up bao loi
            new AlertDialog.Builder(loginFragment.getActivity()).
                    setMessage("Sai tài khoản mật khẩu. Xin thử lại").
                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).
                    show();
        }
    }

    public void createAccount(String email, String password, String fullname){
        JSONPostTask jsonTask = new JSONPostTask(this, JSONTask.CREATE_ACCOUNT,email,password,fullname);
        jsonTask.execute();


    }

    public void setCreateAccount(JSON_Customer customerJSON){
        final JSON_Customer customer=customerJSON;
        final SignupFragment signupFragment= (SignupFragment) fragment;
        signupFragment.customer=customer;
        if (customer!=null){
        //tao tai khoan thanh cong
            new AlertDialog.Builder(signupFragment.getActivity()).
                    setMessage("Đăng kí tài khoản thành công").
                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FragmentManager fragmentManager = fragment.getActivity().getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            LoginFragment loginFragment = new LoginFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("email", customer.getCustomerEmail());
                            bundle.putString("action",signupFragment.action);
                            loginFragment.setArguments(bundle);
                            fragmentTransaction.replace(R.id.main, loginFragment).commit();
                        }
                    }).
                    show();
        }
        else{
            new AlertDialog.Builder(signupFragment.getActivity()).
                    setMessage("Tạo tài khoản thất bại").
                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).
                    show();
        }



    }




}

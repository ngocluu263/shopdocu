package com.swd2015.shopdocu.Controller.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.swd2015.shopdocu.Controller.Adapter.NavListAdapter;
import com.swd2015.shopdocu.Controller.Fragment.HomePage_Fragment;
import com.swd2015.shopdocu.Controller.Fragment.LoginFragment;
import com.swd2015.shopdocu.Controller.Fragment.SearchFragment;
import com.swd2015.shopdocu.Controller.Service.CustomerService;
import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.Controller.Util.Object.NavigationItem;
import com.swd2015.shopdocu.Model.DAO.UserDAO;
import com.swd2015.shopdocu.Model.DTO.Customer;
import com.swd2015.shopdocu.R;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    final String greenColor="#7CD175";
    DrawerLayout drawerLayout;
    RelativeLayout drawerPane;
    ListView listNav;
    RelativeLayout profileBox;
    GridView newProductGrid;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
   public List<NavigationItem> listNavItems;
    List<Fragment> listFragments;
    public ActionBar actionBar;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public android.support.v7.widget.Toolbar toolbar;
    private     ProgressDialog              progressDialog;
    public ImageView avartar;
    public TextView userName;
    public String previousActivity;
    //public android.support.v7.widget.Toolbar toolbar;
    ProductService productService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //handle exception
      //  Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));

        final UserDAO userDAO=new UserDAO(getBaseContext());

        avartar= (ImageView)findViewById(R.id.avatar);
        userName= (TextView) findViewById((R.id.avartarName));

        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(greenColor)));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerPane=(RelativeLayout) findViewById(R.id.drawer_pane);
        listNav = (ListView) findViewById(R.id.nav_list);
        listNavItems=new ArrayList<NavigationItem>();
        listNavItems.add(new NavigationItem("Trang chủ", R.drawable.home_icon));
        listNavItems.add(new NavigationItem("Sản phẩm yêu thích", R.drawable.heart_icon));
        listNavItems.add(new NavigationItem("Sản phẩm đã xem", R.drawable.ic_eye));
        listNavItems.add(new NavigationItem("Đơn hàng đã mua", R.drawable.purchase_icon));
        listNavItems.add(new NavigationItem("Đơn hàng đã bán", R.drawable.sell_icon));
        NavListAdapter navListAdapter = new NavListAdapter(getApplicationContext()
                ,R.layout.navigation_list,listNavItems);
        listNav.setAdapter(navListAdapter);


        final SearchFragment searchFragment=new SearchFragment();
        final HomePage_Fragment homePageFragment=new HomePage_Fragment();
        final LoginFragment loginFragment = new LoginFragment();

        //regionXu ly code khi chuyen nhan yeu cau chuyen fragment tu cac activity khac
        HomePage_Fragment homePage_fragment=new HomePage_Fragment();
        fragmentManager = getFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String action = extras.getString("Action");
            switch (action){
                case "Search":{
                    fragmentManager.beginTransaction().replace(R.id.main,searchFragment).commit();
                    break;
                }
                case "HomePage":{
                    fragmentManager.beginTransaction().replace(R.id.main,homePage_fragment).commit();
                    break;
                }
                case "Login":{
                    fragmentManager.beginTransaction().replace(R.id.main,loginFragment).commit();
                    break;
                }
                case "UserPurchase":{
                    Bundle bundle=new Bundle();
                    bundle.putString("action","UserPurchase");
                    loginFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.main, loginFragment).commit();
                    break;
                }
                case "UserSold":{
                    Bundle bundle=new Bundle();
                    bundle.putString("action","UserSold");
                    loginFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.main, loginFragment).commit();
                    break;
                }
            }
        }
        else{
            fragmentManager.beginTransaction().replace(R.id.main,homePage_fragment).commit();
        }
        //endregion


        listNav.setItemChecked(0, true);
        drawerLayout.closeDrawer(drawerPane);
        final Activity activity = this;

        //regionsetlistener for navigation item (slide-in menu)
        listNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //replace the fragment with the selection corresponding
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //region switch position
                Intent intent;
                switch (position) {
                    case 0: {//trang chu
                        fragmentTransaction.replace(R.id.main, homePageFragment).commit();
                        actionBar.setHomeButtonEnabled(false);
                        actionBar.setDisplayHomeAsUpEnabled(true);
                        actionBar.setTitle("ShopDoCu.vn");
                        break;
                    }
                    case 1: { //san pham yeu thich
                        intent = new Intent(activity, FavoriteProductActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 2: { //san pham da xem
                        intent = new Intent(activity, SeenProductActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 3: { //don hang da mua
                        Customer customer=userDAO.getUser();
                        if (customer!=null){//user da dang nhap
                            intent = new Intent(activity, UserPurchaseActivity.class);
                            intent.putExtra("UserID", customer.getID());
                            startActivity(intent);
                            break;
                        }
                        else { //user chua dang nhap thi qua trang dang
                            fragmentManager = getFragmentManager();
                            fragmentTransaction=fragmentManager.beginTransaction();
                            Bundle bundle=new Bundle();
                            bundle.putString("action","UserPurchase");
                            loginFragment.setArguments(bundle);
                            fragmentTransaction.replace(R.id.main, loginFragment).commit();
                            break;
                        }
                    }
                    case 4: { //don hang da ban
                        Customer customer=userDAO.getUser();
                        if (customer!=null){//user da dang nhap
                            intent = new Intent(activity, UserSoldActivity.class);
                            intent.putExtra("UserID", customer.getID());
                            startActivity(intent);
                            break;
                        }
                        else { //user chua dang nhap thi qua trang dang nhap
                            fragmentManager = getFragmentManager();
                            fragmentTransaction=fragmentManager.beginTransaction();
                            Bundle bundle=new Bundle();
                            bundle.putString("action","UserSold");
                            loginFragment.setArguments(bundle);
                            fragmentTransaction.replace(R.id.main, loginFragment).commit();
                            break;
                        }
                    }
                    case 5: { // dang xuat
                        // xoa user khoi db local
                        new AlertDialog.Builder(loginFragment.getActivity()).
                                setMessage("Đăng xuất thành công").
                                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        userDAO.deleteUser();
                                        listNavItems.remove(5);
                                        avartar.setImageResource(R.drawable.blank_icon);
                                        userName.setText("Đăng nhập/Đăng ký");
                                    }
                                }).
                                show();

                        break;
                    }
                }
                //endregion

                drawerLayout.closeDrawer(drawerPane);
            }
        });
        //endregion

        //create listener for drawer  layout
        actionBarDrawerToggle = new ActionBarDrawerToggle
                (this,drawerLayout,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
                //Toast.makeText(getBaseContext(),"Close",Toast.LENGTH_SHORT).show();
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }
        };
        //final HomePageActivity homePageActivity=this;

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //region Profile Box click listener
        profileBox = (RelativeLayout) findViewById(R.id.profile_box);

        Customer customer=userDAO.getUser();
        if (customer!=null){
            if (listNavItems.size()<=5){
                listNavItems.add(new NavigationItem("Đăng xuất", R.drawable.ic_signout));
            }
            CustomerService customerService=new CustomerService(activity);
            customerService.getCustomerById(customer.getID());
        }

        profileBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check login
                Customer customer=userDAO.getUser();
                if (customer!=null){ //user da dang nhap
                    //Them nut dang xuat tren navigation item
                    Intent intent = new Intent(activity, UserDetailActivity.class);
                    intent.putExtra("UserID", customer.getID());
                    startActivity(intent);
                }
                else{ //user chua dang nhap
                    actionBar.setTitle("Đăng nhập");
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    Bundle bundle=new Bundle();
                    bundle.putString("action","Homepage");
                    loginFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.main, loginFragment).commit();
                }
                drawerLayout.closeDrawer(drawerPane);
            }
        });
        //endregion
    }


    //set listenr for menu , search and cart icon
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        else{
            switch (item.getItemId()){
                //back button
                case android.R.id.home:
                {
                    Bundle extras = getIntent().getExtras();
                    if (extras!=null) {
                        previousActivity = extras.getString("PreviousActivity");
                    }
                    if (previousActivity==null) {
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.main, new HomePage_Fragment()).commit();
                        //onBackPressed();
                        break;
                    }
                    else
                    {
                        onBackPressed();
                    }
                }
                //search icon
                case 0:{
                    progressDialog = ProgressDialog.show(this,
                                        getResources().getString(R.string.progress_dialog_infor),
                                        getResources().getString(R.string.progress_dialog_msg));
                    new Thread(new Runnable() {
                        @Override
                        public void run()
                        {
                            try{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        FragmentManager fragmentManager = getFragmentManager();
                                        fragmentManager.beginTransaction().replace(R.id.main,
                                                new SearchFragment()).commit();
                                    }
                                });
                                Thread.sleep(500);
                                progressDialog.dismiss();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    return true;
                }
                //cart icon
                case 1:{
                    progressDialog = ProgressDialog.show(this,
                                        getResources().getString(R.string.progress_dialog_infor),
                                        getResources().getString(R.string.progress_dialog_msg));
                    new Thread(new Runnable() {
                        @Override
                        public void run()
                        {
                            try{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        CartService cartService = new CartService(this);
                    if(cartService.cartHasProduct()) {
                        Intent intent = new Intent(this, CartActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getBaseContext(), getResources().getText(R.string.cart_has_no_product), Toast.LENGTH_SHORT).show();
                    }
                                    }
                                });
                                Thread.sleep(500);
                                progressDialog.dismiss();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    return true;
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    //region Navigation

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        CreateMenu(menu);
        return true;
    }
    //add menu item
    private void CreateMenu(Menu menu) {
        MenuItem menuItem1=menu.add(0,0,0,"Search");
        {
            menuItem1.setIcon(R.drawable.search_icon);
            menuItem1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

        MenuItem menuItem2=menu.add(0,1,1,"Cart");
        {
            menuItem2.setIcon(R.drawable.cart_icon);
            menuItem2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
    }

    //


    //endregion menu



}
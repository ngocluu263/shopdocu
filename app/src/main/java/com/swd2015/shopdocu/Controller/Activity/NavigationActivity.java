package com.swd2015.shopdocu.Controller.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
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
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.swd2015.shopdocu.Controller.Adapter.NavListAdapter;
import com.swd2015.shopdocu.Controller.Util.Object.NavigationItem;
import com.swd2015.shopdocu.Model.DAO.UserDAO;
import com.swd2015.shopdocu.Model.DTO.Customer;
import com.swd2015.shopdocu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minh on 12/8/2015.
 */
public class NavigationActivity extends AppCompatActivity {
    public final String greenColor="#7CD175";
    public  DrawerLayout drawerLayout;
    public  RelativeLayout drawerPane;
    public ListView listNav;
    public  RelativeLayout profileBox;
    public GridView newProductGrid;
    public  FragmentManager fragmentManager;
    public  List<NavigationItem> listNavItems;
    public List<Fragment> listFragments;
    public ActionBar actionBar;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    public void createNavigation(){
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

        //This code is useful when you just use 1 activity and manu fragment
//        listFragments=  new ArrayList<Fragment>();
//        final HomePage_Fragment homePageFragment=new HomePage_Fragment();
//        final LoginFragment loginFragment = new LoginFragment();
//        AboutFragment AboutFragment =new AboutFragment();
//
//        listFragments.add(new LoginFragment());
//        HomePage_Fragment homePage_fragment=new HomePage_Fragment();
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.main,homePage_fragment).commit();

        // setTitle(listNavItems.get(2).getTitle());


        drawerLayout.closeDrawer(drawerPane);
        final Activity activity = this;
        //set listener for navigation item (slide-in menu)
        listNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserDAO userDAO=new UserDAO(activity.getBaseContext());
                Intent intent;
                switch (position) {
                    case 0: {
//                        fragmentTransaction.replace(R.id.main, homePageFragment).commit();
                        intent=new Intent(activity,HomePageActivity.class);
                        intent.putExtra("Action","HomePage");
                        startActivity(intent);
                        actionBar.setHomeButtonEnabled(false);
                        actionBar.setDisplayHomeAsUpEnabled(true);
                        actionBar.setTitle("ShopDoCu.vn");
                        break;
                    }
                    case 1: {
                        intent = new Intent(activity, FavoriteProductActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 2: {
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
                            intent=new Intent(activity,HomePageActivity.class);
                            intent.putExtra("Action","UserPurchase");
                            startActivity(intent);
                            actionBar.setHomeButtonEnabled(false);
                            actionBar.setDisplayHomeAsUpEnabled(true);
                            actionBar.setTitle("ShopDoCu.vn");
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
                            intent=new Intent(activity,HomePageActivity.class);
                            intent.putExtra("Action","UserSold");
                            startActivity(intent);
                            actionBar.setHomeButtonEnabled(false);
                            actionBar.setDisplayHomeAsUpEnabled(true);
                            actionBar.setTitle("ShopDoCu.vn");
                            break;
                        }


                    }
                    case 5: { // dang xuat
                        // xoa user khoi db local
                        userDAO.deleteUser();
                        listNavItems.remove(5);
                        break;
                    }
                }
                //endregion
                drawerLayout.closeDrawer(drawerPane);
            }
        });

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

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //region Profile Box click listener
        profileBox = (RelativeLayout) findViewById(R.id.profile_box);
        profileBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check login
                Intent intent=new Intent(activity,HomePageActivity.class);
                intent.putExtra("Action","Login");
                startActivity(intent);
//
                drawerLayout.closeDrawer(drawerPane);
            }
        });
    }

    //add menu item
    public void CreateMenu(Menu menu) {
        MenuItem menuItem1=menu.add(0, 0, 0, "Search");
        {
            menuItem1.setIcon(R.drawable.search_icon);
            menuItem1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

        MenuItem menuItem2=menu.add(0, 1, 1, "Cart");
        {
            menuItem2.setIcon(R.drawable.cart_icon);
            menuItem2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
    }

    //search icon and cart icon
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        else{
            switch (item.getItemId()){
                case 0:{
                    Intent intent = new Intent(this, HomePageActivity.class);
                    intent.putExtra("Action","Search");
                    startActivity(intent);
                    return true;
                }
                case 1:{
                    Intent intent = new Intent(this, CartActivity.class);
                    startActivity(intent);
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
}

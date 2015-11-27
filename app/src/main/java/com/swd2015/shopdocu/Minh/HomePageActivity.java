package com.swd2015.shopdocu.Minh;

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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.swd2015.shopdocu.Ga.SearchActivity;
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

    List<NavigationItem> listNavItems;
    List<Fragment> listFragments;
    private Toolbar toolbar;

    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_page);

        //toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        //setSupportActionBar(toolbar);

        //toolbar.setBackground(new ColorDrawable(Color.parseColor("#7CD175")));


        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(greenColor)));
        actionBar.setDisplayShowTitleEnabled(false);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerPane=(RelativeLayout) findViewById(R.id.drawer_pane);
        listNav = (ListView) findViewById(R.id.nav_list);

        listNavItems=new ArrayList<NavigationItem>();
        listNavItems.add(new NavigationItem("Trang chủ", R.drawable.home_icon));
        listNavItems.add(new NavigationItem("Đơn hàng đã mua", R.drawable.purchase_icon));
        listNavItems.add(new NavigationItem("Đơn hàng đã bán", R.drawable.sell_icon));
       // listNavItems.add(new NavigationItem("Sản phẩm vừa xem", R.drawable.blank_avatar));
        listNavItems.add(new NavigationItem("Sản phẩm yêu thích", R.drawable.heart_icon));
        listNavItems.add(new NavigationItem("Hỗ trợ", R.drawable.setting_icon));

        NavListAdapter navListAdapter = new NavListAdapter(getApplicationContext()
                ,R.layout.navigation_list,listNavItems);
        listNav.setAdapter(navListAdapter);

        //This code is useful when you just use 1 activity and manu fragment
        listFragments=  new ArrayList<Fragment>();
        listFragments.add(new fragment_about());
        listFragments.add(new LoginFragment());
        HomePage_Fragment homePage_fragment=new HomePage_Fragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main,homePage_fragment).commit();

       // setTitle(listNavItems.get(2).getTitle());

        listNav.setItemChecked(0, true);
        drawerLayout.closeDrawer(drawerPane);

        //set listener for navigation item
        listNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //replace the fragment with the selection corresponding
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main, listFragments.get(position)).commit();
                setTitle("");
                listNav.setItemChecked(position, true);
                // listNav.setItemChecked();

                //region switch position
                switch (position) {
                    case 0: {
                        Toast.makeText(getBaseContext(), "You have click Trang chủ", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 1: {
                        Toast.makeText(getBaseContext(), "You have click Đơn hàng đã mua", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 2: {
                        Toast.makeText(getBaseContext(), "You have click Đơn hàng đã bán", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 3: {
                        Toast.makeText(getBaseContext(), "You have click Sản phẩm yêu thích", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case 4: {
                        Toast.makeText(getBaseContext(), "You have click Hỗ trợ", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getBaseContext(), "Profile", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(drawerPane);
            }
        });
        //endregion
        //newProductGrid.setAdapter();

    }


    //set listenr for menu , search and cart icon
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        else{
            switch (item.getItemId()){
                case 0:{
                    Intent intent = new Intent(this, SearchActivity.class);
                    startActivity(intent);
                    return true;
                }
                case 1:{
                    Toast.makeText(getBaseContext(),"Cart Icon",Toast.LENGTH_SHORT).show();
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

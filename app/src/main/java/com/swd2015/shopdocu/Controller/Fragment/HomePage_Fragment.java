package com.swd2015.shopdocu.Controller.Fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.swd2015.shopdocu.Controller.Adapter.FragmentAdapter;
import com.swd2015.shopdocu.Model.DAO.UserDAO;
import com.swd2015.shopdocu.Model.DTO.Customer;
import com.swd2015.shopdocu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePage_Fragment extends Fragment implements
        OnTabChangeListener, ViewPager.OnPageChangeListener {
    private TabHost tabHost;
    private ViewPager viewPager;
    private FragmentAdapter fragmentAdapter;
    int i = 0;
    public View v;
    public FragmentActivity fragmentActivity;
    android.support.v4.app.FragmentManager fragmentManager;
    android.support.v4.app.FragmentTransaction fragmentTransaction;
    HomePageMainContentFragment homePageMainContentFragment;

    BanGheCapheFragment banGheCapheFragment;

    // fake content for tabhost
    class FakeContent implements TabHost.TabContentFactory {
        private final Context mContext;

        public FakeContent(Context context) {
            mContext = context;
        }
        @Override
        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumHeight(0);
            v.setMinimumWidth(0);
            return v;
        }
    }
    public HomePage_Fragment() {
        // Required empty public constructor
    }
    public void onAttach(Activity activity) {
        fragmentActivity= (FragmentActivity) activity;
        super.onAttach(fragmentActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         v= inflater.inflate(R.layout.fragment_home_page, container, false);
        i++;
        this.initializeTabHost(savedInstanceState);


        homePageMainContentFragment = new HomePageMainContentFragment();
        fragmentManager=fragmentActivity.getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homepage_main_content, homePageMainContentFragment);
        fragmentTransaction.commit();

        //regionhandle Floating button
        final RequestSellFragment requestSellFragment = new RequestSellFragment();
        FloatingActionButton fab = (FloatingActionButton)v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManagerFAB = getFragmentManager();
                FragmentTransaction fragmentTransactionFAB = fragmentManagerFAB.beginTransaction();
                UserDAO userDAO=new UserDAO(getActivity().getBaseContext());

                //get user from DB local
                Customer customer=userDAO.getUser();
                if (customer!=null){ // user da dang nhap
                    Bundle bundle=new Bundle();
                    bundle.putInt("UserID",customer.getID());
                    requestSellFragment.setArguments(bundle);
                    fragmentTransactionFAB.replace(R.id.main, requestSellFragment);
                    fragmentTransactionFAB.commit();
                }
                else { //chua dang nhap
                    Bundle bundle=new Bundle();
                    bundle.putString("action", "RequestSell");

                    LoginFragment loginFragment=new LoginFragment();
                    loginFragment.setArguments(bundle);
                    fragmentTransactionFAB.replace(R.id.main,loginFragment).commit();
                }
//
//                requestSellFragment.setArguments(bundle);

            }
        });
        //endregion
        return v;
    }



    @TargetApi(Build.VERSION_CODES.M)
    private void initializeTabHost(Bundle args) {

        tabHost = (TabHost)v.findViewById(R.id.tabhost);
        tabHost.setup();



        TabHost.TabSpec tabSpec;
        tabSpec = tabHost.newTabSpec("0");
        tabSpec.setIndicator("Trang chủ");
        tabSpec.setContent(new FakeContent(getActivity().getBaseContext()));
        tabHost.addTab(tabSpec);


        tabSpec = tabHost.newTabSpec("1");
        tabSpec.setIndicator("Bàn ghế cà phê");
        tabSpec.setContent(new FakeContent(getActivity().getBaseContext()));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("2");
        tabSpec.setIndicator("Nội thất gia đình");
        tabSpec.setContent(new FakeContent(getActivity().getBaseContext()));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("3");
        tabSpec.setIndicator("Đồ văn phòng");
        tabSpec.setContent(new FakeContent(getActivity().getBaseContext()));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("4");
        tabSpec.setIndicator("Đồ khách sạn");
        tabSpec.setContent(new FakeContent(getActivity().getBaseContext()));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("5");
        tabSpec.setIndicator("Đồ quán ăn");
        tabSpec.setContent(new FakeContent(getActivity().getBaseContext()));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("6");
        tabSpec.setIndicator("Đồ điện máy, điện tử");
        tabSpec.setContent(new FakeContent(getActivity().getBaseContext()));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("7");
        tabSpec.setIndicator("Danh mục khác");
        tabSpec.setContent(new FakeContent(getActivity().getBaseContext()));
        tabHost.addTab(tabSpec);

        tabHost.setOnTabChangedListener(this);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int position) {
        this.tabHost.setCurrentTab(position);
    }

    @Override
    public void onTabChanged(String tabId) {
        fragmentManager=fragmentActivity.getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        int pos = this.tabHost.getCurrentTab();
        //this.viewPager.setCurrentItem(pos);

        HorizontalScrollView hScrollView = (HorizontalScrollView)v.findViewById(R.id.hScrollView);
        View tabView = tabHost.getCurrentTabView();
        int scrollPos = tabView.getLeft()
                - (hScrollView.getWidth() - tabView.getWidth()) / 2;
        hScrollView.smoothScrollTo(scrollPos, 0);

        switch (pos) {
            case 0://trang chu
            {
                homePageMainContentFragment = new HomePageMainContentFragment();
                fragmentTransaction.replace(R.id.homepage_main_content,homePageMainContentFragment);
                fragmentTransaction.commit();
                break;
            }
            case 1: //quan ca phe
            {
                banGheCapheFragment = new BanGheCapheFragment();
                fragmentTransaction.replace(R.id.homepage_main_content,banGheCapheFragment);
                fragmentTransaction.commit();
                break;
            }
            case 2: // noi ngoi that gia dinh
            {
                GiaDinhFragment giaDinhFragment=new GiaDinhFragment();
                fragmentTransaction.replace(R.id.homepage_main_content,giaDinhFragment);
                fragmentTransaction.commit();
                break;
            }
            case 3: // do van phong
            {
                VanPhongFragment vanPhongFragment = new VanPhongFragment();
                fragmentTransaction.replace(R.id.homepage_main_content,vanPhongFragment);
                fragmentTransaction.commit();
                break;
            }
            case 4:// do khach san
            {
                KhachSanFragment khachSanFragment = new KhachSanFragment();
                fragmentTransaction.replace(R.id.homepage_main_content,khachSanFragment);
                fragmentTransaction.commit();
                break;
            }
            case 5:// do quan an
            {
                QuanAnFragment quanAnFragment=new QuanAnFragment();
                fragmentTransaction.replace(R.id.homepage_main_content,quanAnFragment);
                fragmentTransaction.commit();
                break;
            }
            case 6: // dien tu, dien may
            {
                DienTuFragment dienTuFragment=new DienTuFragment();
                fragmentTransaction.replace(R.id.homepage_main_content,dienTuFragment);
                fragmentTransaction.commit();
                break;
            }
            case 7: //khac
            {
                KhacFragment khacFragment=new KhacFragment();
                fragmentTransaction.replace(R.id.homepage_main_content,khacFragment);
                fragmentTransaction.commit();
                break;
            }

        }
    }
}
package com.example.y.potographu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.example.y.potographu.fragment.FragmentAppointment;
import com.example.y.potographu.fragment.FragmentDiscovery;
import com.example.y.potographu.fragment.FragmentHome;
import com.example.y.potographu.fragment.FragmentMine;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private FragmentManager manager;
    private FragmentHome fragmentHome;
    private FragmentDiscovery fragmentDiscovery;
    private FragmentAppointment fragmentAppointment;
    private FragmentMine fragmentMine;

    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("U拍");
        setSupportActionBar(toolbar);


        BottomNavigationView navigation = findViewById(R.id.navigation);    //bottomNavigation用于选择Fragment
        navigation.setOnNavigationItemSelectedListener(this);

        /*动态加载需要用到FragmentManager和FragmentTransaction来控制Fragment的显示
        * 一个事务只能提交一次，因此我把它的赋值放在showFragment()这个方法中
        */
        manager = getSupportFragmentManager();      //初始化管理者
        fragmentHome = new FragmentHome();      //第一页Fragment
        showFragment(fragmentHome);     //显示方法

    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        /*
        * 使用替换方法，将需要显示的Fragment显示出来
        * 第一个参数是显示fragment的布局
        * 第二个参数是要显示fragment
        * */
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();//提交事务

    }


   /* private void hideFragment(FragmentTransaction transaction) {
        *//*判断当前显示的fragment 将它隐藏
        * 有这一步是为了不引起冲突
        * 但我使用replace方法显示 没有冲突的情况
        * *//*
        if (!fragmentHome.isHidden())
            transaction.hide(fragmentHome);
        else if (!fragmentDiscovery.isHidden())
            transaction.hide(fragmentDiscovery);
        else if (!fragmentAppointment.isHidden())
            transaction.hide(fragmentAppointment);
        else if (!fragmentMine.isHidden())
            transaction.hide(fragmentMine);
    }*/

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       // hideFragment(transaction);
        /*
        * 使用showFragment()方法显示
        * */
        switch (item.getItemId()) {
            case R.id.navigation_home:
                showFragment(fragmentHome);
                return true;
            case R.id.navigation_discovery:
                if (fragmentDiscovery == null)
                    fragmentDiscovery = new FragmentDiscovery();
                showFragment(fragmentDiscovery);
                return true;
            case R.id.navigation_appointment:
                if (fragmentAppointment == null)
                    fragmentAppointment = new FragmentAppointment();
                showFragment(fragmentAppointment);
                return true;
            case R.id.navigation_mine:
                if (fragmentMine == null)
                    fragmentMine = new FragmentMine();
                showFragment(fragmentMine);
                return true;
            default:
        }
        return false;
    }
}

package com.example.y.photographu.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.y.photographu.App;
import com.example.y.photographu.Constant;
import com.example.y.photographu.R;
import com.example.y.photographu.beans.User;
import com.example.y.photographu.view.fragment.FragmentAppointment;
import com.example.y.photographu.view.fragment.FragmentDiscovery;
import com.example.y.photographu.view.fragment.FragmentHome;
import com.example.y.photographu.view.fragment.FragmentMine;
import com.example.y.photographu.util.FileUtil;
import com.example.y.photographu.util.SpfUtil;
import com.google.gson.Gson;

import java.lang.reflect.Field;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener
        , View.OnClickListener {

    private FragmentManager manager;
    private FragmentHome fragmentHome;
    private FragmentDiscovery fragmentDiscovery;
    private FragmentAppointment fragmentAppointment;
    private FragmentMine fragmentMine;
    private View contentView;
    private Fragment currentFragment;
    private TextView schoolName;
    private RelativeLayout search;
    private TextView userNews;
    private TextView photographNews;
    private BottomNavigationView navigation;


    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView("");
        initListener();

        App.getInstance().cookie= SpfUtil.getString(Constant.USER_COOKIE,"");
        App.getInstance().user=new Gson().fromJson(FileUtil.read(Constant.USER_DATA_FILE),User.class);
        User user=App.getInstance().user;

        manager = getSupportFragmentManager();      //初始化管理者
        fragmentHome = new FragmentHome();      //第一页Fragment
        showFragment(fragmentHome);     //显示方法

    }

    @Override
    protected void onResume() {
        super.onResume();
        schoolName.setText(App.getInstance().user.getSchool());
    }

    @Override
    public void initView(String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        navigation = findViewById(R.id.navigation);    //bottomNavigation用于选择Fragment
        contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.popup_new, null);
        schoolName = toolbar.findViewById(R.id.school);
        search = toolbar.findViewById(R.id.search);
        userNews = contentView.findViewById(R.id.popup_user);
        photographNews = contentView.findViewById(R.id.popup_photograph);

        disableShiftMode(navigation);
    }

    @Override
    public void initListener() {
        schoolName.setOnClickListener(this);
        photographNews.setOnClickListener(this);
        userNews.setOnClickListener(this);
        search.setOnClickListener(this);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void showFragment(Fragment fragment) {
        if (currentFragment == null)
            currentFragment = new Fragment();

        if (currentFragment != fragment) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(currentFragment);
            if (!fragment.isAdded())
                transaction.add(R.id.fragment_container, fragment);
            transaction.show(fragment).commit();
        }
        currentFragment = fragment;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                showFragment(fragmentHome);
                break;
            case R.id.navigation_discovery:
                if (fragmentDiscovery == null)
                    fragmentDiscovery = new FragmentDiscovery();
                showFragment(fragmentDiscovery);
                break;
            case R.id.navigation_appointment:
                if (fragmentAppointment == null)
                    fragmentAppointment = new FragmentAppointment();
                showFragment(fragmentAppointment);
                break;
            case R.id.navigation_mine:
                if (fragmentMine == null)
                    fragmentMine = new FragmentMine();
                showFragment(fragmentMine);
                break;
            case R.id.add_news:
                setBackgroundAlpha(0.5f);
                showPopUpWindow();
                break;
            default:
        }
        return true;
    }

    private void showPopUpWindow() {

        View rootView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
        PopupWindow window = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setTouchable(true);
        window.setTouchInterceptor(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        window.setClippingEnabled(true);
        window.setAnimationStyle(R.style.PopupWindowAnimation);
        window.setOutsideTouchable(true);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1);
            }
        });
        window.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }

    private void setBackgroundAlpha(float alpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = alpha;
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.popup_user:
                Toast.makeText(MainActivity.this, "本功能暂未开放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.popup_photograph:
                Toast.makeText(MainActivity.this, "本功能暂未开放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.school:
                Intent intent = new Intent(this, SchoolChooseActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }
}

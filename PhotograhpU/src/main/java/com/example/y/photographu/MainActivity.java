package com.example.y.photographu;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.y.photographu.fragment.FragmentAppointment;
import com.example.y.photographu.fragment.FragmentDiscovery;
import com.example.y.photographu.fragment.FragmentHome;
import com.example.y.photographu.fragment.FragmentMine;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private FragmentManager manager;
    private FragmentHome fragmentHome;
    private FragmentDiscovery fragmentDiscovery;
    private FragmentAppointment fragmentAppointment;
    private FragmentMine fragmentMine;
    private View contentView;
    private final static String TAG = "MainActivity";

    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("U拍");
        setSupportActionBar(toolbar);
        BottomNavigationView navigation = findViewById(R.id.navigation);    //bottomNavigation用于选择Fragment
        navigation.setOnNavigationItemSelectedListener(this);
        contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.popup_new, null);

        TextView userNews = contentView.findViewById(R.id.popup_user);
        TextView photographNews = contentView.findViewById(R.id.popup_photograph);

        photographNews.setOnClickListener(this);
        userNews.setOnClickListener(this);
       /* navigation.setItemIconTintList(new ColorStateList(
                new int[][]{new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked}},
                new int[]{Color.parseColor("#FFE2E2E2"), Color.BLACK})
        );*/
        disableShiftMode(navigation);
        manager = getSupportFragmentManager();      //初始化管理者
        fragmentHome = new FragmentHome();      //第一页Fragment
        showFragment(fragmentHome);     //显示方法

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

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();//提交事务

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
                break;
            case R.id.popup_photograph:
                break;
            default:
        }
    }
}

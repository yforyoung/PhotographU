package com.example.y.photographu.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.example.y.photographu.R;
import com.example.y.photographu.fragment.school.FragmentProvinceShow;

public class SchoolChooseActivity extends BaseActivity {

    private FragmentProvinceShow fragmentProvinceShow;
    private Fragment currentFragment;
    private FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_choose);
        initView("选择");
        initListener();

        fragmentProvinceShow = new FragmentProvinceShow();
        manager = getSupportFragmentManager();
        showFragment(fragmentProvinceShow);

    }

    public void showFragment(Fragment fragment) {
        if (currentFragment == null)
            currentFragment = new Fragment();

        if (currentFragment != fragment) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(currentFragment);
            if (!fragment.isAdded())
                transaction.add(R.id.school_choose_content, fragment, String.valueOf(1));
            transaction.show(fragment).commit();
        }
        currentFragment = fragment;
    }

    @Override
    public void onBackPressed() {
        /*FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(currentFragment);
        transaction.commit();
        if (transaction.isEmpty())*/
            super.onBackPressed();
    }
}

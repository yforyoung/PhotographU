package com.example.y.photographu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.y.photographu.fragment.school.FragmentProvinceShow;

public class SchoolChooseActivity extends BaseActivity {

    FragmentProvinceShow fragmentProvinceShow;
    Fragment currentFragment;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_choose);

        Toolbar toolbar = findViewById(R.id.toolbar_normal);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("选择");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        fragmentProvinceShow=new FragmentProvinceShow();
        manager=getSupportFragmentManager();
        showFragment(fragmentProvinceShow);

    }

    public void showFragment(Fragment fragment) {
        if (currentFragment == null)
            currentFragment = new Fragment();

        if (currentFragment != fragment) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.hide(currentFragment);
            if (!fragment.isAdded())
                transaction.add(R.id.school_choose_content, fragment);
            transaction.show(fragment).commit();
        }
        currentFragment = fragment;
    }
}

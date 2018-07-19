package com.example.y.photographu.view.activity;

import android.app.Application;

import com.example.y.photographu.App;

public class PhotographApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        App.getInstance().init(getApplicationContext());

    }
}

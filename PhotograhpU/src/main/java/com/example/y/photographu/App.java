package com.example.y.photographu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;

import com.example.y.photographu.beans.School;
import com.example.y.photographu.beans.User;
import com.google.gson.Gson;


public class App {
    @SuppressLint("StaticFieldLeak")
    private static final App APP = new App();
    private Context context;
    private Handler handler;
    private Thread mainThread;

    public User user;
    public String cookie = "";
    public School school = new School();
    public static final Gson gson = new Gson();

    private App() {
    }

    public static App getInstance() {
        return APP;
    }

    public static Gson getGson() {
        return gson;
    }

    public void init(Context context){
        this.context=context;
        this.handler=new Handler();
        this.mainThread=Thread.currentThread();
    }

    public Context getContext() {
        return context;
    }

    public Thread getMainThread() {
        return mainThread;
    }

    public Handler getHandler() {
        return handler;
    }
}

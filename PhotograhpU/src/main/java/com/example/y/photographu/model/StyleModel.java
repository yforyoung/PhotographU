package com.example.y.photographu.model;

import android.util.Log;

import com.example.y.photographu.beans.ResponseData;
import com.example.y.photographu.beans.Style;
import com.example.y.photographu.util.OkHttpUtils;
import com.example.y.photographu.view.activity.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;


public class StyleModel implements IStyleModel{
    @Override
    public void showStyle(final IShowStyleListener listener) {
        OkHttpUtils.doPost("http://www.xhban.com:8080/photograph_u/user/listAllStyles",
                null, null, null,
                new OkHttpUtils.MyCallback() {
                    @Override
                    public void onResponse(Response response) throws IOException {
                       listener.showStyle(response);
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                });
    }
}

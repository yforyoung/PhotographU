package com.example.y.photographu.model;


import android.util.Log;

import com.example.y.photographu.Constant;
import com.example.y.photographu.util.MD5Util;
import com.example.y.photographu.util.OkHttpUtils;
import com.example.y.photographu.util.SpfUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;

public class UserModel implements IUserModel {
    @Override
    public void login(String phone, String password, final IUserModel.ILoginListener iLoginListener) {

        Map<String, String> param = new HashMap<>();
        param.put("phone", phone);
        param.put("password", password);
        Log.i(TAG, "login: " + password);
        String url = "http://www.xhban.com:8080/photograph_u/user/login";
        OkHttpUtils.doPost(url, param, null, null, new OkHttpUtils.MyCallback() {
            @Override
            public void onResponse(Response response) throws IOException {
                iLoginListener.login(response);
            }

            @Override
            public void onFailure(IOException e) {
            }
        });

    }

    @Override
    public void register(String phone, String password, String code,
                         final IRegisterListener registerListener) {
        Map<String, String> param = new HashMap<>();
        param.put("phone", phone);
        param.put("password", MD5Util.encodeMD5(password));
        param.put("message_code", code);
        String url = "http://www.xhban.com:8080/photograph_u/user/register";
        OkHttpUtils.doPost(url, param, "cookie",
                SpfUtil.getString(Constant.TEXT_COOKIE, ""),
                new OkHttpUtils.MyCallback() {
                    @Override
                    public void onResponse(Response response) throws IOException {
                        registerListener.register(response);
                    }

                    @Override
                    public void onFailure(IOException e) {
                    }
                });
    }

    @Override
    public void findPassword(String phone, String password, String code, final IFindPasswordListener findPasswordListener) {
        Map<String, String> param = new HashMap<>();
        param.put("phone", phone);
        param.put("new_password", password);
        param.put("message_code", code);
        OkHttpUtils.doPost("http://www.xhban.com:8080/photograph_u/user/resetPassword",
                param,
                "cookie",
                SpfUtil.getString(Constant.TEXT_COOKIE, ""),
                new OkHttpUtils.MyCallback() {
                    @Override
                    public void onResponse(Response response) throws IOException {
                        findPasswordListener.findPassword(response);
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                });
    }


    @Override
    public void saveLoadStatus(String phone, String password) {
        SpfUtil.putString("phone", phone);
        SpfUtil.putString("password", password);
    }

    @Override
    public String readLoadStatus() {
        String s = SpfUtil.getString("password", "");
        return SpfUtil.getString("phone", "") + "||" + s;
    }
}

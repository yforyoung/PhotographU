package com.example.y.photographu.presenter;

import android.util.Log;

import com.example.y.photographu.beans.ResponseData;
import com.example.y.photographu.beans.User;
import com.example.y.photographu.model.IUserModel;
import com.example.y.photographu.model.UserModel;
import com.example.y.photographu.util.FileUtil;
import com.example.y.photographu.util.MD5Util;
import com.example.y.photographu.util.SpfUtil;
import com.example.y.photographu.view.IBaseView;
import com.example.y.photographu.view.ILoadView;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;

public class LoadPs {    //从model层获取数据，进行一定的逻辑处理，交予view进行显示
    private IUserModel mIUserModel;
    private ILoadView mILoadView;

    public LoadPs(ILoadView mILoadView) {
        this.mILoadView = mILoadView;
        mIUserModel = new UserModel();

    }

    public void login(final String phone, final String password) {
        if (phone.equals("")
                || password.equals("")
                || phone.length() != 11
                || password.length() < 6) {
            mILoadView.showFailMessage("请输入正确的用户名或密码！");
        } else {
            load(phone,MD5Util.encodeMD5(password));
        }
    }

    public void autoLogin() {
        String s = mIUserModel.readLoadStatus();
        Log.i(TAG, "autoLogin: " + s);
        if (!s.equals("||")&&!s.equals("")) {
            String phone = s.substring(0, s.indexOf("||"));
            String password = s.substring(s.indexOf("||")+2, s.length());
            Log.i(TAG, "autoLogin: "+phone+"   "+password);;
            load(phone, password);
        }

    }

    private void load(final String phone, final String password) {
        mIUserModel.login(
                phone,
                password,
                new IUserModel.ILoginListener() {
                    @Override
                    public void login(Response response) throws IOException {
                        assert response.body() != null;
                        String s = response.body().string();
                        Gson gson = new Gson();
                        ResponseData responseData = gson.fromJson(s, ResponseData.class);
                        if (responseData.getCode() == 0) {
                            /*存用户 json格式*/
                            FileUtil.save("userData",gson.toJson(responseData.getData()));
                            /*存登陆cookie*/
                            String cookie = response.header("Set-Cookie");
                            assert cookie != null;
                            cookie = cookie.substring(0, cookie.indexOf(";"));
                            SpfUtil.putString("user_cookie",cookie);
                            /*存登陆状态*/
                            mIUserModel.saveLoadStatus(phone, password);
                            /*跳转页面*/
                            mILoadView.jump2MainActivity();
                        } else {
                            mILoadView.showFailMessage(responseData.getMessage());
                        }
                    }
                });
    }
}

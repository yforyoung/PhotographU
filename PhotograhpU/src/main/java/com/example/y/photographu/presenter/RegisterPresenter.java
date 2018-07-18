package com.example.y.photographu.presenter;

import com.example.y.photographu.App;
import com.example.y.photographu.beans.ResponseData;
import com.example.y.photographu.model.ISendTextModel;
import com.example.y.photographu.model.IUserModel;
import com.example.y.photographu.model.SendTextModel;
import com.example.y.photographu.model.UserModel;
import com.example.y.photographu.view.IRegisterView;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Response;

public class RegisterPresenter {
    private IUserModel iUserModel;
    private ISendTextModel iSendTextModel;
    private IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
        iSendTextModel = new SendTextModel();
        iUserModel = new UserModel();
    }

    public void sendText() {
        String phone = iRegisterView.getPhone();
        if (phone.length() != 11)
            iRegisterView.showMessage("请输入正确的手机号码");
        else {
            iSendTextModel.sendText(phone, new ISendTextModel.ISendTextListener() {
                @Override
                public void sendText(Response response) throws IOException {
                    String s = response.body().string();
                    ResponseData responseData = App.getGson().fromJson(s, ResponseData.class);
                    if (responseData.getCode() != 0)
                        iRegisterView.showMessage(responseData.getMessage());
                    else
                        iRegisterView.showMessage("发送成功");
                }
            });
        }

    }

    public void findPassword() {
        String code = iRegisterView.getCode();
        String phone = iRegisterView.getPhone();
        String password = iRegisterView.getPassword();
        if (password.equals("") || code.equals("") || phone.equals(""))
            iRegisterView.showMessage("请输入完整的信息！");
        else if (password.length() < 6)
            iRegisterView.showMessage("新密码少于六个字符！");
        else {
            iUserModel.findPassword(phone, password, code, new IUserModel.IFindPasswordListener() {
                @Override
                public void findPassword(Response response) throws IOException {
                    assert response.body() != null;
                    String s = response.body().string();
                    ResponseData responseData = new Gson().fromJson(s, ResponseData.class);
                    if (responseData.getCode() == 1) {
                        iRegisterView.showMessage(responseData.getMessage());
                    } else if (responseData.getCode() == 0) {
                        iRegisterView.showMessage("修改成功！请登陆");
                        iRegisterView.jump2LoadActivity();
                    }
                }
            });
        }
    }

    public void register() {
        String code = iRegisterView.getCode();
        String phone = iRegisterView.getPhone();
        String password = iRegisterView.getPassword();
        if (password.equals("") || code.equals("") || phone.equals(""))
            iRegisterView.showMessage("请输入完整的注册信息！");
        else if (password.length() < 6)
            iRegisterView.showMessage("密码少于六个字符！");
        else {
            iUserModel.register(
                    phone, password, code, new IUserModel.IRegisterListener() {
                        @Override
                        public void register(Response response) throws IOException {
                            assert response.body() != null;
                            String s = response.body().string();
                            ResponseData responseData = new Gson().fromJson(s, ResponseData.class);
                            if (responseData.getCode() == 1) {
                                iRegisterView.showMessage(responseData.getMessage());
                            } else if (responseData.getCode() == 0) {
                                iRegisterView.showMessage("注册成功！请登陆");
                                iRegisterView.jump2LoadActivity();
                            }
                        }
                    }
            );
        }
    }
}

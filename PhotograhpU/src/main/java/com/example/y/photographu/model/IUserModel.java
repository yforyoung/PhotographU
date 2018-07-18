package com.example.y.photographu.model;

import java.io.IOException;

import okhttp3.Response;

public interface IUserModel {//数据的存储 读写操作 网络请求数据等

    void login(String phone, String password, ILoginListener iLoginListener);

    void register(String phone, String password, String code, IRegisterListener registerListener);

    void findPassword(String phone, String password, String code, IFindPasswordListener findPasswordListener);

    void saveLoadStatus(String phone, String password);


    String readLoadStatus();

    interface ILoginListener {
        void login(Response response) throws IOException;
    }

    interface IRegisterListener {
        void register(Response response) throws IOException;
    }

    interface IFindPasswordListener {
        void findPassword(Response response) throws IOException;
    }

}

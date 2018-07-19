package com.example.y.photographu.model;

import okhttp3.Response;

public interface IStyleModel {
    void showStyle(IShowStyleListener showStyleListener);
    interface IShowStyleListener{
        void showStyle(Response response);
    }
}

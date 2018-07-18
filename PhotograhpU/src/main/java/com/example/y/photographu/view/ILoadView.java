package com.example.y.photographu.view;

public interface ILoadView {
    String getUserPhone();
    String getPassword();
    void showLoading();
    void hideLoading();
    void jump2MainActivity();
    void showFailMessage(String s);
}

package com.example.y.photographu.presenter;

import com.example.y.photographu.model.IStyleModel;
import com.example.y.photographu.model.StyleModel;
import com.example.y.photographu.view.IHomeView;

public class HomePs {
    private IStyleModel styleModel;
    private IHomeView homeView;

    public HomePs(IHomeView homeView) {
        this.homeView = homeView;
        styleModel=new StyleModel();
    }

    public void showStyle(){

    }
}

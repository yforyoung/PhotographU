package com.example.y.photographu.model;


import com.example.y.photographu.util.OkHttpUtils;
import java.io.IOException;
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

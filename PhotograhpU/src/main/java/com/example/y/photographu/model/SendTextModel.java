package com.example.y.photographu.model;

import com.example.y.photographu.util.OkHttpUtils;
import com.example.y.photographu.util.SpfUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

public class SendTextModel implements ISendTextModel {

    @Override
    public void sendText(String phone, final ISendTextListener sendTextListener) {
        Map<String, String> param = new HashMap<>();
        param.put("phone", phone);
        OkHttpUtils.doPost("http://www.xhban.com:8080/photograph_u/user/requestMessageCode",
                param,
                null, null,
                new OkHttpUtils.MyCallback() {
                    @Override
                    public void onResponse(Response response) throws IOException {
                        String cookie = response.header("Set-Cookie");
                        assert cookie != null;
                        SpfUtil.putString("text_cookie",cookie.substring(0, cookie.indexOf(";")));
                        sendTextListener.sendText(response);
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                });
    }


}

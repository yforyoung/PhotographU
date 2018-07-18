package com.example.y.photographu.model;


import java.io.IOException;

import okhttp3.Response;

public interface ISendTextModel {
    void sendText(String phone,ISendTextListener sendTextListener);

    interface ISendTextListener{
        void sendText(Response response) throws IOException;
    }
}

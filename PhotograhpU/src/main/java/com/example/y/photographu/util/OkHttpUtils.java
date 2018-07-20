package com.example.y.photographu.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import com.bumptech.glide.Glide;
import com.example.y.photographu.App;
import com.example.y.photographu.Constant;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtils {
    private static OkHttpClient client;
    private static OkHttpUtils okHttpUtils;
    private Handler handler;

    private OkHttpUtils() {
        client = new OkHttpClient();
        //handler = new Handler(Looper.getMainLooper());
    }

    /*单例模式  获取类实例*/
    private static OkHttpUtils getInstance() {
        if (okHttpUtils == null) {
            synchronized (OkHttpClient.class) {
                okHttpUtils = new OkHttpUtils();
            }
        }
        return okHttpUtils;
    }


    public static void doPost(String url, Map<String, String> param, String headerName, String header, final MyCallback callback) {
        getInstance().inner_doPost(url, param, headerName, header, callback);
    }

    public static void postFiles(String url, List<String> params, final MyCallback callback) {
        getInstance().inner_postFile(url,params,callback);
    }

    private void inner_postFile(String url, List<String> params, final MyCallback callback){
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (String s : params) {
            builder.addFormDataPart("file",
                    s.substring(s.lastIndexOf("/")+1,s.length()),
                    RequestBody.create(MediaType.parse("image/*"),new File(s)));
        }
        RequestBody requestBody = builder
                .setType(MultipartBody.FORM)
                .build();
        Request request = new Request.Builder()
                .addHeader("cookie", SpfUtil.getString(Constant.USER_COOKIE, ""))
                .post(requestBody)
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onResponse(response);
            }
        });
    }
   /* private File writeByte2File(byte[] bytes) {
        FileOutputStream out = null;
        try {
            out =App.getInstance().getContext().openFileOutput("aaa", Context.MODE_PRIVATE);
            InputStream is = new ByteArrayInputStream(bytes);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = is.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(App.getInstance().getContext().getFilesDir().getPath(),"aaa");
    }*/


    /*内部方法 网络请求
     * */
    private void inner_doPost(String url, Map<String, String> param, String headerName, String header, final MyCallback callback) {
        final Request request;
        Request.Builder requestBuilder = new Request.Builder();

        /*判断是否有请求体*/
        if (param != null) {
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> map : param.entrySet()) {
                String key = map.getKey();
                String value;
                if (map.getValue() == null) {
                    value = "";
                } else {
                    value = map.getValue();
                }
                builder.add(key, value);
            }

            RequestBody requestBody = builder.build();
            requestBuilder.post(requestBody);
        }


        /*判断是否有头部信息
         *
         * 这里由于这一个项目的要求头部信息不多，我只用了很简单的一个写法
         * 需要更多信息的可以使用Headers进行扩展
         * */
        if (header != null) {
            requestBuilder.addHeader(headerName, header);
        }

        request = requestBuilder
                .url(url)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverRequestFailure(e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                deliverRequestSuccess(response, callback);
            }
        });
    }


    /*请求失败时回调*/
    private void deliverRequestFailure(final IOException e, final MyCallback callback) {
        if (callback != null)
            callback.onFailure(e);
        /*handler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null)
                    callback.onFailure(e);
            }
        });*/
    }

    /*请求成功时回调*/
    private void deliverRequestSuccess(final Response response, final MyCallback callback) {
        if (callback != null) {
            try {
                callback.onResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }/*
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    try {
                        callback.onResponse(response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });*/
    }

    /*
     * 外部回调*/
    public interface MyCallback {
        void onResponse(Response response) throws IOException;

        void onFailure(IOException e);
    }
}
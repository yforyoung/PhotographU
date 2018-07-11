package com.example.y.photographu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BaseActivity extends AppCompatActivity {

    private static final int SHOW_TOAST = 1;
    private static final int REFRESH_ADAPTER = 2;
    private RecyclerView.Adapter adapter;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SHOW_TOAST:
                    String s = msg.getData().getString("toast");
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    break;
                case REFRESH_ADAPTER:
                    adapter.notifyDataSetChanged();
                    break;
                default:

            }
        }
    };

    public void showToast(String s) {
        Message message = new Message();
        message.what = SHOW_TOAST;
        Bundle bundle = new Bundle();
        bundle.putString("toast", s);
        message.setData(bundle);
        handler.sendMessage(message);
    }

    public void refreshAdapter(RecyclerView.Adapter adapter){
        Message message=new Message();
        message.what=REFRESH_ADAPTER;
        this.adapter=adapter;
        handler.sendMessage(message);
    }

    public void doPost(final Request request) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        handleResponse(response);
                    }
                });
            }
        });
    }

    private HandleResponse handleResponse;


    public interface HandleResponse {
        void handleResponses(Response response) throws IOException;
    }

    public void setHandleResponse(HandleResponse handleResponse) {
        this.handleResponse = handleResponse;
    }

    private void handleResponse(Response response) {
        if (handleResponse != null) {
            try {
                handleResponse.handleResponses(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

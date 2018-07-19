package com.example.y.photographu.view.activity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import com.example.y.photographu.R;
import com.example.y.photographu.view.IBaseView;


public class BaseActivity extends AppCompatActivity implements IBaseView {
    private Toast toast;

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
                    if (toast == null) {
                        toast = Toast.makeText(BaseActivity.this, s, Toast.LENGTH_SHORT);
                    } else {
                        toast.setText(s);
                    }
                    toast.show();
                    break;
                case REFRESH_ADAPTER:
                    adapter.notifyDataSetChanged();
                    break;
                default:

            }
        }
    };


    @Override
    public void showToast(String s) {
        Message message = new Message();
        message.what = SHOW_TOAST;
        Bundle bundle = new Bundle();
        bundle.putString("toast", s);
        message.setData(bundle);
        handler.sendMessage(message);
    }

    public void refreshAdapter(RecyclerView.Adapter adapter) {
        Message message = new Message();
        message.what = REFRESH_ADAPTER;
        this.adapter = adapter;
        handler.sendMessage(message);
    }

    @Override
    public void initView(String title) {
        Toolbar toolbar = findViewById(R.id.toolbar_normal);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void initListener() {

    }



}

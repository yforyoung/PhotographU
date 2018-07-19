package com.example.y.photographu.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.y.photographu.R;
import com.example.y.photographu.adapter.DiscoveryContentAdapter;
import com.example.y.photographu.beans.PageBean;
import com.example.y.photographu.beans.ResponseData;
import com.example.y.photographu.beans.Style;
import com.example.y.photographu.beans.Topic;
import com.example.y.photographu.util.OkHttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

public class TypeShowActivity extends BaseActivity {
    private static final String TAG = "TypeShowActivity";
    private Style type;
    private List<Topic> topicList;
    private DiscoveryContentAdapter adapter;
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_show);

        type = (Style) getIntent().getSerializableExtra("type");

        Toolbar toolbar = findViewById(R.id.toolbar_normal);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(type.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        topicList = new ArrayList<>();


        RecyclerView recyclerView = findViewById(R.id.type_show_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new DiscoveryContentAdapter(topicList);
        recyclerView.setAdapter(adapter);

        initData();
    }

    private void initData() {

        Map<String, String> param = new HashMap<>();
        param.put("style_id", String.valueOf(type.getId()));
        param.put("page_size", "10");
        param.put("current_page", String.valueOf(currentPage));
        OkHttpUtils.doPost("http://www.xhban.com:8080/photograph_u/user/listAllNotesWithPage",
                param, null, null,
                new OkHttpUtils.MyCallback() {
                    @Override
                    public void onResponse(Response response) throws IOException {
                        String s = response.body().string();
                        Log.i(TAG, "handleResponses: " + s);

                        ResponseData responseData = new Gson()
                                .fromJson(s, new TypeToken<ResponseData<PageBean>>() {
                                }.getType());
                        PageBean pageBean = (PageBean) responseData.getData();
                   /*     LinkedTreeMap objects= (LinkedTreeMap) pageBean.getData().get(0);
                        Double id= (Double) objects.get("id");
                        LinkedTreeMap user= (LinkedTreeMap) objects.get("user");*/
                /*if (pageBean != null)
                    topicList.addAll(pageBean.getData());*/
                        refreshAdapter(adapter);
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                });
    }
}

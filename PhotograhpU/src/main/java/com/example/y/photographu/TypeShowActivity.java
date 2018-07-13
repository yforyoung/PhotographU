package com.example.y.photographu;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.y.photographu.adapter.DiscoveryContentAdapter;
import com.example.y.photographu.beans.PageBean;
import com.example.y.photographu.beans.ResponseData;
import com.example.y.photographu.beans.Style;
import com.example.y.photographu.beans.Topic;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TypeShowActivity extends BaseActivity {
    private static final String TAG = "TypeShowActivity";
    private Style type;
    private List<Topic> topicList;
    private DiscoveryContentAdapter adapter;
    private int currentPage=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_show);
        Toolbar toolbar = findViewById(R.id.type_show_toolbar);
        setSupportActionBar(toolbar);
        TextView title = toolbar.findViewById(R.id.type_show_toolbar_title);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        type = (Style) getIntent().getSerializableExtra("type");
        title.setText(type.getName());

        topicList = new ArrayList<>();


        RecyclerView recyclerView = findViewById(R.id.type_show_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new DiscoveryContentAdapter(topicList);
        recyclerView.setAdapter(adapter);

        initData();
    }

    private void initData() {
        RequestBody requestBody = new FormBody.Builder()
                .add("style_id", type.getId() + "")
                .add("page_size","10")
                .add("current_page",currentPage+"")
                .build();
        Request request = new Request.Builder()
                .url("http://www.xhban.com:8080/photograph_u/user/listAllNotesWithPage")
                .post(requestBody)
                .build();
        doPost(request);
        setHandleResponse(new HandleResponse() {
            @Override
            public void handleResponses(Response response) throws IOException {
                String s = response.body().string();
                Log.i(TAG, "handleResponses: "+s);
                /*这个东西是真的烦!*/
                ResponseData responseData = new Gson()
                        .fromJson(s, new TypeToken<ResponseData<PageBean>>() {
                        }.getType());
                PageBean pageBean = (PageBean) responseData.getData();
                LinkedTreeMap objects= (LinkedTreeMap) pageBean.getData().get(0);
                Double id= (Double) objects.get("id");
                LinkedTreeMap user= (LinkedTreeMap) objects.get("user");
                /*if (pageBean != null)
                    topicList.addAll(pageBean.getData());*/
                refreshAdapter(adapter);
            }
        });

    }
}

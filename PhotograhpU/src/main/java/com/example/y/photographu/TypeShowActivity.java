package com.example.y.photographu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.y.photographu.adapter.DiscoveryContentAdapter;
import com.example.y.photographu.beans.Comment;
import com.example.y.photographu.beans.Topic;
import com.example.y.photographu.beans.Type;

import java.util.ArrayList;
import java.util.List;

public class TypeShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_show);
        Toolbar toolbar=findViewById(R.id.type_show_toolbar);
        setSupportActionBar(toolbar);
        TextView title=toolbar.findViewById(R.id.type_show_toolbar_title);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Type type= (Type) getIntent().getSerializableExtra("type");
        title.setText(type.getTypeName());

        List<Topic> topicList=new ArrayList<>();


        RecyclerView recyclerView = findViewById(R.id.type_show_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DiscoveryContentAdapter adapter = new DiscoveryContentAdapter(topicList);
        recyclerView.setAdapter(adapter);
    }
}

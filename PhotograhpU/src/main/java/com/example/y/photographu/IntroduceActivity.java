package com.example.y.photographu;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.y.photographu.adapter.HomeStyleAdapter;

public class IntroduceActivity extends AppCompatActivity {

    private CollapsingToolbarLayoutState state;
    private enum CollapsingToolbarLayoutState{
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        RecyclerView recyclerView=findViewById(R.id.introduce_judge_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(new HomeStyleAdapter());
        AppBarLayout appBarLayout=findViewById(R.id.introduce_app_bar_layout);

        Toolbar toolbar=findViewById(R.id.introduce_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title=toolbar.findViewById(R.id.introduce_toolbar_title);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.introduce_menu,menu);
        return true;

    }

}

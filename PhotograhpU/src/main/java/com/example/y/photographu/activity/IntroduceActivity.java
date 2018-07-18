package com.example.y.photographu.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.y.photographu.R;
import com.example.y.photographu.adapter.PhotographJudgementAdapter;
import com.example.y.photographu.beans.Photographer;
import com.jude.rollviewpager.RollPagerView;

public class IntroduceActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        RecyclerView recyclerView=findViewById(R.id.introduce_judge_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new PhotographJudgementAdapter());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        Toolbar toolbar=findViewById(R.id.introduce_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView title=toolbar.findViewById(R.id.introduce_toolbar_title);
        RollPagerView rollPagerView=findViewById(R.id.introduce_pic_roll_viewpager);
        TextView price=findViewById(R.id.introduce_price);
        TextView name=findViewById(R.id.introduce_photographer_name);
        TextView introduce=findViewById(R.id.introduce_photographer_introduce);
        TextView commentCount=findViewById(R.id.introduce_judge_count);
        ImageView head=findViewById(R.id.introduce_photographer_head_portrait);
        TextView service=findViewById(R.id.introduce_service_content);
        TextView talkTo=findViewById(R.id.introduce_chat);
        TextView focus=findViewById(R.id.introduce_focus);
        TextView order=findViewById(R.id.introduce_order);

        talkTo.setOnClickListener(this);
        focus.setOnClickListener(this);
        order.setOnClickListener(this);

        Photographer photographer= (Photographer) getIntent().getSerializableExtra("p");
        title.setText(photographer.getUser().getNickname());
        price.setText("￥"+photographer.getPrice());
        name.setText(photographer.getUser().getNickname());
        introduce.setText(photographer.getIntroduce());
        //service.setText(photographer.getService());
       // commentCount.setText("("+photographer.getPhotographerCommentList().size()+")");
       // head.setImageResource(photographer.getUser().getHeadImage());
        //rollPagerView.setAdapter(new PhotographerRollViewPagerAdapter(photographer.getPhotos()));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.introduce_menu,menu);
        return true;

    }


    @Override
    public void onClick(View v) {
        Toast.makeText(this, "本功能暂时未开放哦~", Toast.LENGTH_SHORT).show();
    }
}

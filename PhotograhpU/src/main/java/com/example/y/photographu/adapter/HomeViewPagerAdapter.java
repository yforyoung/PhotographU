package com.example.y.potographu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.y.potographu.R;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;


public class HomeViewPagerAdapter extends StaticPagerAdapter {

    private int img[]=new int[]{R.drawable.roll_3,R.drawable.roll_2,R.drawable.roll_1};

    @Override
    public View getView(ViewGroup container, int position) {
        View view1= LayoutInflater.from(container.getContext()).inflate(R.layout.view_home_type,container,false);
        view1.findViewById(R.id.home_type_img).setBackgroundResource(img[position]);
        ((TextView)view1.findViewById(R.id.home_type_text)).setText("本周最佳风格：小清新新");
        return view1;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

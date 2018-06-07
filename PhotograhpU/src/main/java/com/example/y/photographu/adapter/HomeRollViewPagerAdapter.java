package com.example.y.photographu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.y.photographu.R;
import com.example.y.photographu.beans.Type;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;


public class HomeRollViewPagerAdapter extends StaticPagerAdapter {

    private Type types[]=new Type[]{
            new Type("毕业照","",R.drawable.graduation),
            new Type("个人写真","",R.drawable.personal_index),
            new Type("多人写真","",R.drawable.people_index)
    };

    @Override
    public View getView(ViewGroup container, int position) {
        View view1= LayoutInflater.from(container.getContext()).inflate(R.layout.view_home_type,container,false);
        view1.findViewById(R.id.home_type_img).setBackgroundResource(types[position].getTypePic());
        ((TextView)view1.findViewById(R.id.home_type_text)).setText("推荐风格："+types[position].getTypeName());
        return view1;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

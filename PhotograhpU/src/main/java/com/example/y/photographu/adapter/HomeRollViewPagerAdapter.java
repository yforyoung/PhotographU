package com.example.y.photographu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.y.photographu.R;
import com.example.y.photographu.Util;
import com.example.y.photographu.beans.Style;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import okhttp3.Request;
import okhttp3.RequestBody;


public class HomeRollViewPagerAdapter extends StaticPagerAdapter {

    private Style styles[]=new Style[]{
            new Style(1,"毕业照","default.jpg"),
            new Style(2,"古装","default.jpg"),
            new Style(3,"个人写真","default.jpg")
    };

    @Override
    public View getView(ViewGroup container, int position) {
        View view1= LayoutInflater.from(container.getContext()).inflate(R.layout.view_home_type,container,false);
        Glide.with(container)
                .load("http://www.xhban.com:8080/photograph_u/style_images/default.jpg")
                .into((ImageView) view1.findViewById(R.id.home_type_img));


        /*view1.findViewById(R.id.home_type_img).setBackgroundResource(Styles[position].getImage());*/
        ((TextView)view1.findViewById(R.id.home_type_text)).setText("推荐风格："+styles[position].getName());
        return view1;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

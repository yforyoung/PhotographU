package com.example.y.photographu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.y.photographu.R;
import com.example.y.photographu.beans.Type;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;


import java.util.List;

public class PhotographerRollViewPagerAdapter extends StaticPagerAdapter {
    private List<Integer> photoList;

    public PhotographerRollViewPagerAdapter(List<Integer> photoList) {
        this.photoList = photoList;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView imageView=new ImageView(container.getContext());
        imageView.setImageResource(photoList.get(position));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public int getCount() {
        return photoList.size();
    }
}

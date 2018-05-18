package com.example.y.photographu.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import com.example.y.photographu.R;
import com.example.y.photographu.adapter.HomeStyleAdapter;
import com.example.y.photographu.adapter.HomeViewPagerAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

public class FragmentHome extends Fragment {
    private RecyclerView recyclerView;
    private RollPagerView rollPagerView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = getActivity().findViewById(R.id.home_style_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(new HomeStyleAdapter());

        rollPagerView=getActivity().findViewById(R.id.type_roll_viewpager);
        rollPagerView.setHintView(new ColorPointHintView(getActivity(),
                Color.parseColor("#FF000000"), Color.parseColor("#FFB7B7B7")));
        rollPagerView.setAdapter(new HomeViewPagerAdapter());


    }


}

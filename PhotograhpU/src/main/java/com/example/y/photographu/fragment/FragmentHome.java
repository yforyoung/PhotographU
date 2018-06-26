package com.example.y.photographu.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.y.photographu.R;
import com.example.y.photographu.Test;
import com.example.y.photographu.TypeShowActivity;
import com.example.y.photographu.adapter.HomeStyleAdapter;
import com.example.y.photographu.adapter.HomeRollViewPagerAdapter;
import com.example.y.photographu.beans.Type;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    private RecyclerView recyclerView;
    private RollPagerView rollPagerView;
    private View view;
    private List<Type> typeList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        typeList = new ArrayList<>();


        recyclerView = getActivity().findViewById(R.id.home_style_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        HomeStyleAdapter adapter=new HomeStyleAdapter(typeList);
        adapter.setOnItemClickListener(new HomeStyleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int tag) {
                Intent intent=new Intent(getActivity(), TypeShowActivity.class);
                intent.putExtra("type",typeList.get(tag));
                startActivity(intent);

            }
        });
        recyclerView.setAdapter(adapter);

        rollPagerView = getActivity().findViewById(R.id.type_roll_viewpager);
        rollPagerView.setHintView(new ColorPointHintView(getActivity(),
                Color.parseColor("#FF000000"), Color.parseColor("#FFB7B7B7")));
        rollPagerView.setAdapter(new HomeRollViewPagerAdapter());


    }


}

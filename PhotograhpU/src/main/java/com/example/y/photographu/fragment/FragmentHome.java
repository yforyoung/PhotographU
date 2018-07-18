package com.example.y.photographu.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.y.photographu.activity.BaseActivity;
import com.example.y.photographu.R;
import com.example.y.photographu.activity.TypeShowActivity;
import com.example.y.photographu.adapter.StyleAdapter;
import com.example.y.photographu.adapter.HomeRollViewPagerAdapter;
import com.example.y.photographu.beans.ResponseData;
import com.example.y.photographu.beans.Style;
import com.example.y.photographu.util.OkHttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;

public class FragmentHome extends Fragment {
    private RecyclerView recyclerView;
    private RollPagerView rollPagerView;
    private View view;
    private List<Style> typeList;
    private StyleAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeList = new ArrayList<>();
        initData();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        /*风格列表显示*/
        adapter = new StyleAdapter(typeList, getActivity());
        recyclerView = getActivity().findViewById(R.id.home_style_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter.setOnItemClickListener(new StyleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int tag) {
                Intent intent = new Intent(getActivity(), TypeShowActivity.class);
                intent.putExtra("type", typeList.get(tag));
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);


        /*轮播图显示*/
        rollPagerView = getActivity().findViewById(R.id.type_roll_viewpager);
        rollPagerView.setHintView(new ColorPointHintView(getActivity(),
                Color.parseColor("#FF000000"), Color.parseColor("#FFB7B7B7")));
        rollPagerView.setAdapter(new HomeRollViewPagerAdapter());


    }

    private void initData() {
        OkHttpUtils.doPost("http://www.xhban.com:8080/photograph_u/user/listAllStyles",
                null, null, null,
                new OkHttpUtils.MyCallback() {
                    @Override
                    public void onResponse(Response response) throws IOException {
                        String s = response.body().string();
                        Log.i(TAG, "handleResponses: " + s);
                        ResponseData responseData = new Gson().fromJson(s, new TypeToken<ResponseData<List<Style>>>() {
                        }.getType());
                        typeList.addAll((List<Style>) responseData.getData());
                        Log.i(TAG, "handleResponses: ");
                        // adapter.notifyDataSetChanged();
                        ((BaseActivity) getActivity()).refreshAdapter(adapter);
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                });
    }


}

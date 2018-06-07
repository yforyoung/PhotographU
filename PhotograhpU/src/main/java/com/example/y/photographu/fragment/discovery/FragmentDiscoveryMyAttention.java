package com.example.y.photographu.fragment.discovery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.y.photographu.R;
import com.example.y.photographu.Test;
import com.example.y.photographu.Util;
import com.example.y.photographu.adapter.DiscoveryContentAdapter;
import com.example.y.photographu.beans.Comment;
import com.example.y.photographu.beans.Topic;
import com.example.y.photographu.beans.Type;
import com.example.y.photographu.beans.User;

import java.util.ArrayList;
import java.util.List;

public class FragmentDiscoveryMyAttention extends Fragment {
    private List<Topic> topicList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discovery_1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        topicList = new ArrayList<>();
        topicList.add(new Topic(Test.getInstence().user3, "个人拍！",
                R.drawable.personal,
                10,
                new ArrayList<Comment>(),
                new Type()));
        topicList.add(new Topic(Test.getInstence().user4, "古装照！",
                R.drawable.gu,
                10,
                new ArrayList<Comment>(),
                new Type()));
        topicList.add(new Topic(Test.getInstence().user5, "毕业啦！",
                R.drawable.graduation,
                10,
                new ArrayList<Comment>(),
                new Type()));
        topicList.add(new Topic(Test.getInstence().user1, "毕业啦！",
                R.drawable.graduation,
                10,
                new ArrayList<Comment>(),
                new Type()));
        topicList.add(new Topic(Test.getInstence().user2, "cos！",
                R.drawable.cosplay,
                10,
                new ArrayList<Comment>(),
                new Type()));


        RecyclerView recyclerView = getActivity().findViewById(R.id.discovery_1_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        DiscoveryContentAdapter adapter = new DiscoveryContentAdapter(topicList);
        recyclerView.setAdapter(adapter);
    }
}

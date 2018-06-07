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

public class FragmentDiscoveryRecommend extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discovery_2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = getActivity().findViewById(R.id.discovery_2_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        DiscoveryContentAdapter adapter = new DiscoveryContentAdapter(new Util().getListTopic());
        recyclerView.setAdapter(adapter);
    }
}

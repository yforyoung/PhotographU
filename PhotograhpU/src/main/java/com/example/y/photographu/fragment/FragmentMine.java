package com.example.y.photographu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.y.photographu.R;
import com.example.y.photographu.Test;
import com.example.y.photographu.beans.User;

public class FragmentMine extends Fragment {
    private ImageView headPic;
    private TextView nickname;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        if (Test.getInstance().user != null)
            nickname.setText(Test.getInstance().user.getNickname());

    }

    private void initView() {
        headPic = getActivity().findViewById(R.id.mine_head);
        nickname = getActivity().findViewById(R.id.mine_nickname);
    }


}

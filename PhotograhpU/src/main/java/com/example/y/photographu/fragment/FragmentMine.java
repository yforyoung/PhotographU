package com.example.y.photographu.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.y.photographu.activity.LoadActivity;
import com.example.y.photographu.R;
import com.example.y.photographu.App;
import com.example.y.photographu.beans.User;
import com.example.y.photographu.util.SpfUtil;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

public class FragmentMine extends Fragment implements View.OnClickListener {
    private CircleImageView headPic;
    private TextView nickname;
    private TextView set;
    private TextView myNote;
    private TextView myConcern;
    private TextView myHistory;
    private TextView myCollection;
    private TextView apply;
    private TextView aboutUs;
    private TextView introduce;
    private Button exit;
    private FragmentActivity mContext;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext=getActivity();
        initView();
        initUserInfo();
    }

    private void initUserInfo() {
        if (App.getInstance().user != null) {
            User user= App.getInstance().user;
            nickname.setText(user.getNickname());
            Glide.with(this)
                    .load("http://www.xhban.com:8080/photograph_u/head_images/"
                            +user.getHeadImage())
                    .into(headPic);
            Log.i(TAG, "initUserInfo: ");
        }
    }

    private void initView() {
        headPic = mContext.findViewById(R.id.mine_head);
        nickname = mContext.findViewById(R.id.mine_nickname);
        exit=mContext.findViewById(R.id.exit);
        myNote=mContext.findViewById(R.id.my_note);
        myCollection = mContext.findViewById(R.id.my_collection);
        myConcern = mContext.findViewById(R.id.my_concern);
        myHistory=mContext.findViewById(R.id.my_history);
        apply=mContext.findViewById(R.id.my_apply);
        aboutUs = mContext.findViewById(R.id.about_us);
        introduce = mContext.findViewById(R.id.mine_intro);
        set=mContext.findViewById(R.id.my_set);

        exit.setOnClickListener(this);
        headPic.setOnClickListener(this);
        myHistory.setOnClickListener(this);
        myConcern.setOnClickListener(this);
        myCollection.setOnClickListener(this);
        myNote.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        apply.setOnClickListener(this);
        set.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exit:
                logout();
                break;
            case R.id.my_apply:
                break;
            case R.id.my_collection:
                break;
            case R.id.my_concern:
                break;
            case R.id.my_history:
                break;
            case R.id.my_note:
                break;
            case R.id.my_set:
                break;
            case R.id.mine_head:
                break;
            case R.id.about_us:
                break;

        }
    }

    private void jump2Activity(Class<?> targetClass) {
        if (intent==null)
            intent=new Intent();
        intent.setClass(mContext,targetClass);
        startActivity(intent);

    }


    private void logout() {
        new AlertDialog.Builder(mContext)
                .setTitle("")
                .setMessage("确定退出账号？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        exit();
                    }
                })
                .setNegativeButton("取消",null)
                .create()
                .show();
    }

    private void exit() {
        SpfUtil.putString("phone","");
        SpfUtil.putString("password","");
        Intent intent=new Intent(mContext, LoadActivity.class);
        startActivity(intent);
        mContext.finish();
    }
}

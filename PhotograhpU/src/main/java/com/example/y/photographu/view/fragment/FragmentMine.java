package com.example.y.photographu.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.y.photographu.Constant;
import com.example.y.photographu.beans.ResponseData;
import com.example.y.photographu.util.FileUtil;
import com.example.y.photographu.util.HandleImageUtil;
import com.example.y.photographu.util.OkHttpUtils;
import com.example.y.photographu.view.activity.BaseActivity;
import com.example.y.photographu.view.activity.LoadActivity;
import com.example.y.photographu.R;
import com.example.y.photographu.App;
import com.example.y.photographu.beans.User;
import com.example.y.photographu.util.SpfUtil;
import com.example.y.photographu.view.activity.SettingActivity;
import com.example.y.photographu.view.activity.UserInfoSetActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

public class FragmentMine extends Fragment implements View.OnClickListener {
    private CircleImageView headPic;
    private TextView nickname;
    private RelativeLayout set;
    private RelativeLayout myNote;
    private RelativeLayout myConcern;
    private RelativeLayout myHistory;
    private RelativeLayout myCollection;
    private RelativeLayout apply;
    private TextView introduce;
    private Button exit;
    private RelativeLayout user;
    private FragmentActivity mContext;
    private Intent intent;
    private String imgPath = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        initView();

    }

    @Override
    public void onResume() {
        super.onResume();
        initUserInfo();

    }


    private void initUserInfo() {
        if (App.getInstance().user != null) {
            User user = App.getInstance().user;
            nickname.setText(user.getNickname());
            SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                    Bitmap bitmap=((BitmapDrawable)resource).getBitmap();
                    bitmap=Bitmap.createScaledBitmap(bitmap,76,76,false);
                    headPic.setImageBitmap(bitmap);
                }
            };

            Log.i(TAG, "initUserInfo: "+user.getHeadImage());
            Glide.with(this)
                    .load("http://www.xhban.com:8080/photograph_u/head_images/"
                            + user.getHeadImage())
                    .into(simpleTarget);

        }
    }

    private void initView() {
        headPic = mContext.findViewById(R.id.mine_head);
        nickname = mContext.findViewById(R.id.mine_nickname);
        exit = mContext.findViewById(R.id.exit);
        myNote = mContext.findViewById(R.id.my_note);
        myCollection = mContext.findViewById(R.id.my_collection);
        myConcern = mContext.findViewById(R.id.my_concern);
        myHistory = mContext.findViewById(R.id.my_history);
        apply = mContext.findViewById(R.id.my_apply);
        introduce = mContext.findViewById(R.id.mine_intro);
        set = mContext.findViewById(R.id.my_set);
        user = mContext.findViewById(R.id.mine_user);

        exit.setOnClickListener(this);
        headPic.setOnClickListener(this);
        myHistory.setOnClickListener(this);
        myConcern.setOnClickListener(this);
        myCollection.setOnClickListener(this);
        myNote.setOnClickListener(this);
        apply.setOnClickListener(this);
        set.setOnClickListener(this);
        user.setOnClickListener(this);
    }


    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    imgPath = HandleImageUtil.handleImageOnKitKat(data);
                    List<String> list = new ArrayList<>();
                    list.add(imgPath);
                    OkHttpUtils.postFiles("http://www.xhban.com:8080/photograph_u/user/setHeadImage",
                            list, new OkHttpUtils.MyCallback() {
                                @Override
                                public void onResponse(Response response) throws IOException {
                                    String s = response.body().string();
                                    Log.i(TAG, "onResponse: " + s);
                                    ResponseData responseData = App.getGson().fromJson(s, ResponseData.class);
                                    if (responseData.getCode() == 0) {
                                        ((BaseActivity) getActivity()).showToast("修改成功");
                                        App.getInstance().user.setHeadImage(imgPath);
                                        String m = App.getGson().toJson(App.getInstance().user);
                                        FileUtil.save(Constant.USER_DATA_FILE, m);
                                    } else
                                        ((BaseActivity) getActivity()).showToast(responseData.getMessage());
                                }

                                @Override
                                public void onFailure(IOException e) {
                                    Log.i(TAG, "onFailure: " + e);
                                }
                            });
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                jump2Activity(SettingActivity.class);
                break;
            case R.id.mine_head:
                openAlbum();
                break;
            case R.id.mine_user:
                jump2Activity(UserInfoSetActivity.class);
                break;

        }
    }

    private void jump2Activity(Class<?> targetClass) {
        if (intent == null)
            intent = new Intent();
        intent.setClass(mContext, targetClass);
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
                .setNegativeButton("取消", null)
                .create()
                .show();
    }

    private void exit() {
        SpfUtil.putString("phone", "");
        SpfUtil.putString("password", "");
        Intent intent = new Intent(mContext, LoadActivity.class);
        startActivity(intent);
        mContext.finish();
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }
}

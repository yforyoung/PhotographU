package com.example.y.photographu.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.y.photographu.App;
import com.example.y.photographu.Constant;
import com.example.y.photographu.R;
import com.example.y.photographu.beans.ResponseData;
import com.example.y.photographu.util.HandleImageUtil;
import com.example.y.photographu.util.OkHttpUtils;
import com.example.y.photographu.util.SpfUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ApplyPhotographerActivity extends BaseActivity implements View.OnClickListener {
    private EditText cardNumber;
    private ImageView cardImage;
    private Button submit;
    private String imgPath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_photograher);
        initView("申请成为摄影师");
        initListener();

    }

    @Override
    public void initView(String title) {
        super.initView(title);
        cardImage = findViewById(R.id.add_card_photo);
        cardNumber = findViewById(R.id.card_number);
        submit = findViewById(R.id.apply_submit);
    }

    @Override
    public void initListener() {
        cardImage.setOnClickListener(this);
        submit.setOnClickListener(this);
    }


    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    imgPath = HandleImageUtil.handleImageOnKitKat(data);
                    SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                            bitmap = Bitmap.createScaledBitmap(bitmap, 342, 220, false);
                            cardImage.setImageBitmap(bitmap);
                        }
                    };
                    Glide.with(this).load(imgPath)
                            .into(simpleTarget);

                }
                break;
        }
    }


    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_card_photo:
                Log.i("info", "onClick: ");
                cardImage.requestFocus();
                openAlbum();
                break;
            case R.id.apply_submit:
                final String no = cardNumber.getText().toString();
                if (imgPath == "" || no == "") {
                    showToast("请输入完整的信息！");
                } else {
                    Map<String, String> param = new HashMap<>();
                    param.put("card_no", no);
                    param.put("file", imgPath);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            final RequestBody requestBody = new MultipartBody.Builder()
                                    .addFormDataPart("file", imgPath, RequestBody.create(MediaType.parse("image/*"), new File(imgPath)))
                                    .addFormDataPart("card_no", no)
                                    .setType(MultipartBody.FORM)
                                    .build();
                            final Request request = new Request.Builder()
                                    .addHeader("cookie", SpfUtil.getString(Constant.USER_COOKIE, ""))
                                    .post(requestBody)
                                    .url("http://www.xhban.com:8080/photograph_u/user/requestToBePhotographer")
                                    .build();
                            new OkHttpClient().newCall(request).enqueue(new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String s = response.body().string();
                                    ResponseData responseData = App.getGson().fromJson(s, ResponseData.class);
                                    showToast(responseData.getMessage());
                                    Log.i("info", "onResponse: " + s);
                                }
                            });
                        }
                    });
                    break;
                }
        }
    }
}

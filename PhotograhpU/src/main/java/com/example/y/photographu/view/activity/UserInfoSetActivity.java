package com.example.y.photographu.view.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import com.example.y.photographu.App;
import com.example.y.photographu.Constant;
import com.example.y.photographu.R;
import com.example.y.photographu.beans.ResponseData;
import com.example.y.photographu.beans.User;
import com.example.y.photographu.util.FileUtil;
import com.example.y.photographu.util.OkHttpUtils;
import com.example.y.photographu.util.SpfUtil;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.Response;


public class UserInfoSetActivity extends BaseActivity implements View.OnClickListener {
    private EditText nickname;
    private EditText sex;
    private EditText birthday;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_set);
        initView("编辑资料");
        initListener();
        user = App.getInstance().user;
        nickname.setText(user.getNickname());
        sex.setText(user.getSex());
        birthday.setText(user.getBirthday());

    }

    @Override
    public void initView(String title) {
        Toolbar toolbar = findViewById(R.id.toolbar_normal);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo();
            }
        });

        nickname = findViewById(R.id.set_nickname);
        sex = findViewById(R.id.set_sex);
        birthday = findViewById(R.id.set_birthday);
    }

    @Override
    public void initListener() {
        sex.setOnClickListener(this);
        birthday.setOnClickListener(this);
        sex.setFocusable(false);
        birthday.setFocusable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_birthday:
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        birthday.setText(i + "-" + (++i1) + "-" + i2);
                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(this, 0, listener, year, month, day);
                dialog.show();
                break;

            case R.id.set_sex:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final String s[] = new String[]{"男", "女"};
                builder.setItems(s, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sex.setText(s[i]);
                    }
                });
                builder.show();
                break;
        }
    }

    private void saveInfo() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("确认修改？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map<String, String> params = new HashMap<>();
                        params.put("school", user.getSchool());
                        params.put("nickname", nickname.getText().toString());
                        params.put("sex", sex.getText().toString());
                        params.put("birthday", birthday.getText().toString());
                        OkHttpUtils.doPost("http://www.xhban.com:8080/photograph_u/user/updateInfo",
                                params, "cookie", SpfUtil.getString(Constant.USER_COOKIE, ""),
                                new OkHttpUtils.MyCallback() {
                                    @Override
                                    public void onResponse(Response response) throws IOException {
                                        String s = response.body().string();
                                        ResponseData responseData = new Gson().fromJson(s, ResponseData.class);
                                        if (responseData.getCode() == 0) { //修改成功就存到文件中
                                            user.setBirthday(birthday.getText().toString());
                                            user.setSex(sex.getText().toString());
                                            user.setNickname(nickname.getText().toString());
                                            App.getInstance().user = user;
                                            String m = new Gson().toJson(App.getInstance().user);
                                            FileUtil.save(Constant.USER_DATA_FILE, m);
                                            showToast("修改成功");
                                        } else {
                                            showToast(responseData.getMessage());
                                        }
                                        finish();
                                    }
                                    @Override
                                    public void onFailure(IOException e) {
                                    }
                                });


                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create().show();
    }

    @Override
    public void onBackPressed() {
        saveInfo();
    }
}

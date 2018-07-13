package com.example.y.photographu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.y.photographu.beans.ResponseData;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class FindPasswordActivity extends BaseActivity implements View.OnClickListener {
    private EditText userEdit;
    private EditText passwordEdit;
    private EditText codeEdit;
    private Button sendCode;
    private Button confirm;
    private String cookie;

    private String userPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);

        Toolbar toolbar = findViewById(R.id.load_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("找回密码");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        userEdit = findViewById(R.id.find_password_user);
        codeEdit = findViewById(R.id.find_password_code);
        passwordEdit = findViewById(R.id.find_password_new_password);
        sendCode = findViewById(R.id.find_password_send_code);
        confirm = findViewById(R.id.find_password_confirm);

        sendCode.setOnClickListener(this);
        confirm.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_password_confirm:
                resetPassword();
                break;
            case R.id.find_password_send_code:
                sendText();
                break;
            default:
        }
    }

    private void resetPassword() {
        String password = passwordEdit.getText().toString();
        String code = codeEdit.getText().toString();
        userPhone = userEdit.getText().toString();

        if (password.equals("") || code.equals("") || userPhone.equals(""))
            showToast("请输入完整的信息！");
        else if (password.length() < 6)
            showToast("密码少于六个字符！");
        else {
            RequestBody requestBody = new FormBody.Builder()
                    .add("phone", userPhone)
                    .add("new_password", passwordEdit.getText().toString())
                    .add("message_code", codeEdit.getText().toString())
                    .build();
            Request request = new Request.Builder()
                    .post(requestBody)
                    .addHeader("cookie", cookie)
                    .url("http://www.xhban.com:8080/photograph_u/user/resetPassword")
                    .build();
            doPost(request);
            setHandleResponse(new HandleResponse() {
                @Override
                public void handleResponses(Response response) throws IOException {
                    assert response.body() != null;
                    String s=response.body().string();
                    ResponseData responseData=new Gson().fromJson(s,ResponseData.class);
                    if (responseData.getCode()==1){
                        showToast(responseData.getMessage());
                    }
                    else if (responseData.getCode()==0){
                        showToast("注册成功！请登陆");
                        Intent intent=new Intent(FindPasswordActivity.this,LoadActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    }

    private void sendText() {
        userPhone = userEdit.getText().toString();
        if (userPhone.length() != 11) {
            showToast("请输入正确的手机号码");
        } else {
            RequestBody requestBody = new FormBody.Builder()
                    .add("phone", userPhone)
                    .build();
            final Request request = new Request.Builder().url("http://www.xhban.com:8080/photograph_u/user/requestMessageCode")
                    .post(requestBody)
                    .build();

            doPost(request);
            setHandleResponse(new HandleResponse() {
                @Override
                public void handleResponses(Response response) throws IOException {
                    cookie = response.header("Set-Cookie");

                    cookie = cookie.substring(0, cookie.indexOf(";"));
                    assert response.body() != null;
                    String s = response.body().string();
                    ResponseData responseData = new Gson().fromJson(s, ResponseData.class);
                    if (responseData.getCode() == 0) {
                        showToast("发送成功");
                    } else {
                        showToast(responseData.getMessage());
                    }
                }
            });
        }
    }
}

package com.example.y.photographu;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private EditText userEdit;
    private EditText passwordEdit;
    private EditText codeEdit;
    private Button sendTextButton;
    private Button registerButton;
    private String userPhone="";
    private String cookie;

    private final static String TAG="RegisterActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initClickListener();

    }

    private void initClickListener() {
        sendTextButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar_normal);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("账号注册");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        userEdit = findViewById(R.id.register_user);
        passwordEdit = findViewById(R.id.register_password);
        codeEdit = findViewById(R.id.register_reference_code);
        sendTextButton = findViewById(R.id.register_send_text);
        registerButton = findViewById(R.id.register_button);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_send_text:
                sendText();
                break;
            case R.id.register_button:
                register();
                break;
            default:
        }
    }

    private void register() {
        String password=passwordEdit.getText().toString();
        String code=codeEdit.getText().toString();
        userPhone=userEdit.getText().toString();
        Log.i(TAG, "register: "+cookie);
        if (password.equals("")||code.equals("")||userPhone.equals(""))
            showToast("请输入完整的注册信息！");
        else if (password.length()<6)
            showToast("密码少于六个字符！");
        else{
            RequestBody requestBody=new FormBody.Builder()
                    .add("phone",userPhone)
                    .add("password",password)
                    .add("message_code",code)
                    .build();
            final Request request=new Request.Builder()
                    .addHeader("cookie",cookie)
                    .url("http://www.xhban.com:8080/photograph_u/user/register")
                    .post(requestBody)
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
                        finish();
                    }
                }
            });
        }

    }

    private void sendText() {
        userPhone=userEdit.getText().toString();
        if (userPhone.length()!=11){
            showToast("请输入正确的手机号码");
        }
        else {
            RequestBody requestBody=new FormBody.Builder()
                    .add("phone",userPhone)
                    .build();
            final Request request=new Request.Builder().url("http://www.xhban.com:8080/photograph_u/user/requestMessageCode")
                    .post(requestBody)
                    .build();

            doPost(request);
           setHandleResponse(new HandleResponse() {
                @Override
                public void handleResponses(Response response) throws IOException {
                    cookie=response.header("Set-Cookie");
                    Log.i(TAG, "handleResponses: "+cookie);
                    cookie=cookie.substring(0,cookie.indexOf(";"));
                    assert response.body() != null;
                    String s=response.body().string();
                    ResponseData responseData=new Gson().fromJson(s,ResponseData.class);
                    if (responseData.getCode()==0){
                        showToast("发送成功");
                    }
                    else{
                        showToast(responseData.getMessage());
                    }
                }
            });
        }
    }
}

package com.example.y.photographu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.y.photographu.beans.ResponseData;
import com.example.y.photographu.beans.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoadActivity extends BaseActivity implements View.OnClickListener {
    private final static String TAG = "info";
    private EditText loadUser;
    private EditText loadPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        if (sharedPreferences.getInt("user", 0) == 1) {
            Intent intent = new Intent(LoadActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        TextView findPassword = findViewById(R.id.load_find_password);
        TextView register = findViewById(R.id.load_register);

        loadUser = findViewById(R.id.load_user);
        loadPassword = findViewById(R.id.load_password);
        Button load = findViewById(R.id.load_button);

        load.setOnClickListener(this);
        findPassword.setOnClickListener(this);
        register.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.load_find_password:
                Intent intent1 = new Intent(LoadActivity.this, FindPasswordActivity.class);
                startActivity(intent1);
                break;
            case R.id.load_register:
                Intent intent2 = new Intent(LoadActivity.this, RegisterActivity.class);
                startActivity(intent2);
                break;
            case R.id.load_button:
                load();
                break;
            default:

        }
    }

    private void load() {
        String user = loadUser.getText().toString();
        String password = loadPassword.getText().toString();
        if (user.equals("") || password.equals("")) {
            Toast.makeText(LoadActivity.this, "请输入正确的用户名或密码！", Toast.LENGTH_SHORT).show();
        } else {
            final OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("phone", user)
                    .add("password", password)
                    .build();
            final Request request = new Request.Builder()
                    .url("http://www.xhban.com:8080/photograph_u/user/login")
                    .post(requestBody)
                    .build();
            final Util util = new Util();
            util.doPost(LoadActivity.this, request);
            util.setHandleResponse(new Util.HandleResponse() {
                @Override
                public void handleResponses(Response response) throws IOException {

                    String s = response.body().string();
                    Gson gson = new Gson();
                    ResponseData responseData = gson.fromJson(s, ResponseData.class);
                    if (responseData.getCode() == 0) {
                        String m = gson.toJson(responseData.getData());
                        User user = gson.fromJson(m, User.class);
                        Headers headers = response.headers();
                        String cookie = headers.get("Set-Cookie");
                        cookie = cookie.substring(cookie.indexOf("=")+1, cookie.indexOf(";"));
                        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = getSharedPreferences("user_info", MODE_PRIVATE).edit();
                        editor.putString("cookie", cookie);
                        editor.putInt("user", 1);
                        Test.getInstence().user = user;
                        util.save(m, LoadActivity.this);
                        Log.i(TAG, "handleResponses: " + cookie);
                        Intent intent = new Intent(LoadActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (responseData.getCode() == 1) {
                        showToast("用户名或密码错误！");
                    }

                }
            });

        }
    }
}

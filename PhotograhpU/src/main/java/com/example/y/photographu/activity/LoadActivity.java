package com.example.y.photographu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.y.photographu.R;
import com.example.y.photographu.presenter.LoadPresenter;
import com.example.y.photographu.view.ILoadView;

public class LoadActivity extends BaseActivity implements View.OnClickListener, ILoadView {
    private final static String TAG = "info";
    private LoadPresenter loadPresenter;
    private EditText loadUser;
    private EditText loadPassword;
    private TextView findPassword;
    private TextView register;
    private Button load;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        initView("");
        initListener();
        loadPresenter=new LoadPresenter(this);
        loadPresenter.autoLogin();
    }

    @Override
    public void initView(String title) {
        findPassword = findViewById(R.id.load_find_password);
        register = findViewById(R.id.load_register);
        loadUser = findViewById(R.id.load_user);
        loadPassword = findViewById(R.id.load_password);
        load = findViewById(R.id.load_button);
    }

    @Override
    public void initListener() {
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
                loadPresenter.login(getUserPhone(),getPassword());
                break;
            default:

        }
    }


    @Override
    public String getUserPhone() {
        return loadUser.getText().toString();
    }

    @Override
    public String getPassword() {
        return loadPassword.getText().toString();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void jump2MainActivity() {
        Intent intent = new Intent(LoadActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showFailMessage(String s) {
        showToast(s);
    }
}

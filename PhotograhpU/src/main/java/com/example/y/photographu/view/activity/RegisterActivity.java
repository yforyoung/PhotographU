package com.example.y.photographu.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.y.photographu.R;
import com.example.y.photographu.presenter.RegisterPs;
import com.example.y.photographu.view.IRegisterView;

public class RegisterActivity extends BaseActivity implements View.OnClickListener ,IRegisterView{
    private Toolbar toolbar;
    private EditText userEdit;
    private EditText passwordEdit;
    private EditText codeEdit;
    private Button sendTextButton;
    private Button registerButton;
    private RegisterPs registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initClickListener();
        registerPresenter=new RegisterPs(this);

    }

    private void initClickListener() {
        sendTextButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initView() {
        toolbar = findViewById(R.id.toolbar_normal);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("账号注册");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
                registerPresenter.sendText();
                break;
            case R.id.register_button:
                registerPresenter.register();
                break;
            default:
        }
    }

    @Override
    public String getPhone() {
        return userEdit.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEdit.getText().toString();
    }

    @Override
    public String getCode() {
        return codeEdit.getText().toString();
    }

    @Override
    public void jump2LoadActivity() {
        Intent intent=new Intent(this, LoadActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMessage(String s) {
        showToast(s);
    }
}

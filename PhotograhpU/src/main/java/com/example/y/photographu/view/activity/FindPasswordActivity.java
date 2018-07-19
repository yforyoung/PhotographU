package com.example.y.photographu.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.y.photographu.R;
import com.example.y.photographu.presenter.RegisterPs;
import com.example.y.photographu.view.IRegisterView;


public class FindPasswordActivity extends BaseActivity implements View.OnClickListener, IRegisterView {
    private EditText userEdit;
    private EditText passwordEdit;
    private EditText codeEdit;
    private Button sendCode;
    private Button confirm;
    private RegisterPs registerPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        initView("找回密码");
        initListener();
        registerPresenter=new RegisterPs(this);

    }

    @Override
    public void initView(String title) {
        super.initView(title);
        userEdit = findViewById(R.id.find_password_user);
        codeEdit = findViewById(R.id.find_password_code);
        passwordEdit = findViewById(R.id.find_password_new_password);
        sendCode = findViewById(R.id.find_password_send_code);
        confirm = findViewById(R.id.find_password_confirm);
    }

    @Override
    public void initListener() {
        super.initListener();
        sendCode.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_password_confirm:
                registerPresenter.findPassword();
                break;
            case R.id.find_password_send_code:
                registerPresenter.sendText();
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
        Intent intent=new Intent(this,LoadActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMessage(String s) {
        showToast(s);
    }
}

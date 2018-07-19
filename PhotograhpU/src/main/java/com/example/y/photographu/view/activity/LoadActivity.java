package com.example.y.photographu.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.y.photographu.R;
import com.example.y.photographu.presenter.LoadPs;
import com.example.y.photographu.util.HandleImageUtil;
import com.example.y.photographu.view.ILoadView;

import java.util.ArrayList;
import java.util.List;

public class LoadActivity extends BaseActivity implements View.OnClickListener, ILoadView {
    private final static String TAG = "info";
    private LoadPs loadPs;
    private EditText loadUser;
    private EditText loadPassword;
    private TextView findPassword;
    private TextView register;
    private Button load;
    private List<String> pList = new ArrayList<>();
    private String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        request();
        initView("");
        initListener();
        loadPs =new LoadPs(this);
        loadPs.autoLogin();
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
                loadPs.login(getUserPhone(),getPassword());
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

    public void request() {         //请求权限
        pList.clear();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                pList.add(permission);
            }
        }
        if (!pList.isEmpty()) {
            String[] permissions = pList.toArray(new String[pList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(this, permissions, 1);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]);
                    if (showRequestPermission) {
                        Toast.makeText(this, "您拒绝了权限!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}

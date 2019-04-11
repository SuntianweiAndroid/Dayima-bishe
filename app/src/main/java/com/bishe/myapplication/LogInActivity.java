package com.bishe.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bishe.myapplication.utils.MySharedPreferences;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     * 123456789
     */
    private EditText mEdtName;
    /**
     * ABCDEF
     */
    private EditText mEdtPwd;
    /**
     * 登录
     */
    private Button mBtnLogin;
    /**
     * 注册
     */
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        MySharedPreferences.getInstance(this);
        mEdtName = findViewById(R.id.edt_name);
        mEdtPwd = findViewById(R.id.edt_pwd);
        mBtnLogin = findViewById(R.id.btn_login);
    }

    private void initView() {
        mEdtName = (EditText) findViewById(R.id.edt_name);
        mEdtPwd = (EditText) findViewById(R.id.edt_pwd);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mBtnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_login:
                String loginName = MySharedPreferences.getString("name");
                String loginPwd = MySharedPreferences.getString("pwd");

                if (mEdtName.getText().toString().equals(loginName) && mEdtPwd.getText().toString().equals(loginPwd)) {
                    showToast(this, "登录成功！");
                    Intent intent = new Intent(this, MenuActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.btn_register:
                String name = mEdtName.getText().toString();
                String pwd = mEdtPwd.getText().toString();
                MySharedPreferences.setString("name", name);
                MySharedPreferences.setString("pwd", pwd);
                showToast(this, "注册成功，请登录！");

                break;
        }
    }

    private void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}

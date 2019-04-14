package com.bishe.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bishe.myapplication.registeract.RegisterActivity;
import com.bishe.myapplication.utils.MySharedPreferences;

public class LogInActivity extends MyBaseActivity implements View.OnClickListener {


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
        setContentView(R.layout.activity_login);
        initView();
        MySharedPreferences.getInstance(this);
        if (MySharedPreferences.getIslogin()) {
            intentClass(MenuActivity.class);
            finish();
        } else {

        }
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
                String loginName = MySharedPreferences.getName();
                String loginPwd = MySharedPreferences.getPwd();
                if (mEdtName.getText().toString().equals("") || mEdtPwd.getText().toString().equals("")) {
                    showToast(this, "请输入用户名或密码！");
                } else if (mEdtName.getText().toString().equals(loginName) && mEdtPwd.getText().toString().equals(loginPwd)) {
                    showToast(this, "登录成功！");
                    MySharedPreferences.setIslogin(true);
                    intentClass(MenuActivity.class);
                    finish();
                } else {
                    showToast(this, "用户名或密码错误！");
                }
                break;
            case R.id.btn_register:
                intentClass(RegisterActivity.class);
                break;
        }
    }


}

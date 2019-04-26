package com.bishe.myapplication.registeract;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bishe.myapplication.MyBaseActivity;
import com.bishe.myapplication.R;
import com.bishe.myapplication.utils.MySharedPreferences;

public class RegisterActivity extends MyBaseActivity implements View.OnClickListener {

    private EditText mEdtName;
    private EditText mEdtPwd;
    /**
     * 确定
     */
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mEdtName = (EditText) findViewById(R.id.edt_name);
        mEdtPwd = (EditText) findViewById(R.id.edt_pwd);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mBtnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_register:
                String name = mEdtName.getText().toString();
                String pwd = mEdtPwd.getText().toString();
                if (name.equals("") || pwd.equals("")) {
                    showToast(this, "请填写正确的用户名密码！");
                } else {
                    MySharedPreferences.setName(name);
                    MySharedPreferences.setPwd(pwd);
                    intentClass(ZhouqiActivity.class);
                }
                break;
        }
    }
}

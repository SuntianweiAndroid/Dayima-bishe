package com.bishe.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.bishe.myapplication.fragment.HomeFragment;
import com.bishe.myapplication.fragment.SettingFragment;

public class MenuActivity extends FragmentActivity implements View.OnClickListener {
    private HomeFragment homeFragment;
    private SettingFragment settingFragment;
    private FrameLayout mFragment;
    private ImageButton mBtnHome;
    private ImageButton mDate;
    private ImageButton mYueliang;
    private ImageButton mBtnSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏 第一种方法
        setContentView(R.layout.activity_menu);
        initView();
        initFragment();
        changeFragment(homeFragment);
    }

    /**
     * 切换的Fragment
     *
     * @param f
     */
    public void changeFragment(Fragment f) {
        //关闭
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment, f).commit();

    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        settingFragment = new SettingFragment();
    }

    private void initView() {
        mFragment = (FrameLayout) findViewById(R.id.fragment);
        mBtnHome = (ImageButton) findViewById(R.id.btn_home);
        mBtnHome.setOnClickListener(this);
        mDate = (ImageButton) findViewById(R.id.date);
        mDate.setOnClickListener(this);
        mYueliang = (ImageButton) findViewById(R.id.yueliang);
        mYueliang.setOnClickListener(this);
        mBtnSetting = (ImageButton) findViewById(R.id.btn_setting);
        mBtnSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_home:
                changeFragment(homeFragment);
                break;
            case R.id.date:
                break;
            case R.id.yueliang:
                break;
            case R.id.btn_setting:
                changeFragment(settingFragment);
                break;
        }
    }
}

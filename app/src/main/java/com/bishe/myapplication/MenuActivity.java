package com.bishe.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.bishe.myapplication.fragment.HomeFragment;
import com.bishe.myapplication.fragment.JiluFragment;
import com.bishe.myapplication.fragment.RiliFragment;
import com.bishe.myapplication.fragment.SettingFragment;

/**
 * 主界面 承托fragment
 */
public class MenuActivity extends FragmentActivity implements View.OnClickListener {
    private HomeFragment homeFragment;
    private SettingFragment settingFragment;
    private RiliFragment riliFragment;
    private JiluFragment jiluFragment;
    private FrameLayout mFragment;
    private ImageButton mBtnHome;
    private ImageButton mDate;
    private ImageButton mYueliang;
    private ImageButton mBtnSetting;
    private LinearLayout mLayoutHome;
    private LinearLayout mLayoutDate;
    private LinearLayout mLayoutYueliang;
    private LinearLayout mLayoutSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_menu);
        initView();
        initFragment();
        changeFragment(homeFragment);
        mLayoutHome.setBackgroundResource(R.drawable.menu_btn_false);
        mLayoutDate.setBackgroundResource(R.drawable.menu_btn_true);
        mLayoutYueliang.setBackgroundResource(R.drawable.menu_btn_true);
        mLayoutSetting.setBackgroundResource(R.drawable.menu_btn_true);
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

    /**
     * 初始所有fragment
     */
    private void initFragment() {
        homeFragment = new HomeFragment();
        settingFragment = new SettingFragment();
        riliFragment = new RiliFragment();
        jiluFragment = new JiluFragment();
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
        mLayoutHome = (LinearLayout) findViewById(R.id.layout_home);
        mLayoutHome.setOnClickListener(this);
        mLayoutDate = (LinearLayout) findViewById(R.id.layout_date);
        mLayoutDate.setOnClickListener(this);
        mLayoutYueliang = (LinearLayout) findViewById(R.id.layout_yueliang);
        mLayoutYueliang.setOnClickListener(this);
        mLayoutSetting = (LinearLayout) findViewById(R.id.layout_setting);
        mLayoutSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_home:
                //切换按钮改变颜色
                mLayoutHome.setBackgroundResource(R.drawable.menu_btn_false);
                mLayoutDate.setBackgroundResource(R.drawable.menu_btn_true);
                mLayoutYueliang.setBackgroundResource(R.drawable.menu_btn_true);
                mLayoutSetting.setBackgroundResource(R.drawable.menu_btn_true);
                //改变fragment
                changeFragment(homeFragment);
                break;
            case R.id.date:
                //切换按钮改变颜色
                mLayoutHome.setBackgroundResource(R.drawable.menu_btn_true);
                mLayoutDate.setBackgroundResource(R.drawable.menu_btn_false);
                mLayoutYueliang.setBackgroundResource(R.drawable.menu_btn_true);
                mLayoutSetting.setBackgroundResource(R.drawable.menu_btn_true);
                //改变fragment
                changeFragment(riliFragment);
                break;
            case R.id.yueliang:
                //切换按钮改变颜色
                mLayoutHome.setBackgroundResource(R.drawable.menu_btn_true);
                mLayoutDate.setBackgroundResource(R.drawable.menu_btn_true);
                mLayoutYueliang.setBackgroundResource(R.drawable.menu_btn_false);
                mLayoutSetting.setBackgroundResource(R.drawable.menu_btn_true);
                //改变fragment
                changeFragment(jiluFragment);
                break;
            case R.id.btn_setting:
                //切换按钮改变颜色
                mLayoutHome.setBackgroundResource(R.drawable.menu_btn_true);
                mLayoutDate.setBackgroundResource(R.drawable.menu_btn_true);
                mLayoutYueliang.setBackgroundResource(R.drawable.menu_btn_true);
                mLayoutSetting.setBackgroundResource(R.drawable.menu_btn_false);
                //改变fragment
                changeFragment(settingFragment);
                break;

        }
    }
}

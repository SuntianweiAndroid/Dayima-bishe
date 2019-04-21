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
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏 第一种方法
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

    //    /*
////     * 去除（隐藏）所有的Fragment
////     * */
////    private void hideFragment(FragmentTransaction transaction) {
////        if (f1 != null) {
////            //transaction.hide(f1);隐藏方法也可以实现同样的效果，不过我一般使用去除
////            transaction.remove(f1);
////        }
////        if (f2 != null) {
////            //transaction.hide(f2);
////            transaction.remove(f2);
////        }
////        if (f3 != null) {
////            //transaction.hide(f3);
////            transaction.remove(f3);
////        }
////
////
////    }
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
                mLayoutHome.setBackgroundResource(R.drawable.menu_btn_false);
                mLayoutDate.setBackgroundResource(R.drawable.menu_btn_true);
                mLayoutYueliang.setBackgroundResource(R.drawable.menu_btn_true);
                mLayoutSetting.setBackgroundResource(R.drawable.menu_btn_true);
                changeFragment(homeFragment);
                break;
            case R.id.date:
                mLayoutHome.setBackgroundResource(R.drawable.menu_btn_true);
                mLayoutDate.setBackgroundResource(R.drawable.menu_btn_false);
                mLayoutYueliang.setBackgroundResource(R.drawable.menu_btn_true);
                mLayoutSetting.setBackgroundResource(R.drawable.menu_btn_true);
                changeFragment(riliFragment);
                break;
            case R.id.yueliang:
                mLayoutHome.setBackgroundResource(R.drawable.menu_btn_true);
                mLayoutDate.setBackgroundResource(R.drawable.menu_btn_true);
                mLayoutYueliang.setBackgroundResource(R.drawable.menu_btn_false);
                mLayoutSetting.setBackgroundResource(R.drawable.menu_btn_true);
                changeFragment(jiluFragment);
                break;
            case R.id.btn_setting:
                mLayoutHome.setBackgroundResource(R.drawable.menu_btn_true);
                mLayoutDate.setBackgroundResource(R.drawable.menu_btn_true);
                mLayoutYueliang.setBackgroundResource(R.drawable.menu_btn_true);
                mLayoutSetting.setBackgroundResource(R.drawable.menu_btn_false);
                changeFragment(settingFragment);
                break;

        }
    }
}

package com.bishe.myapplication.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bishe.myapplication.LogInActivity;
import com.bishe.myapplication.R;
import com.bishe.myapplication.dayimarili.db.MenstruationCycle;
import com.bishe.myapplication.dayimarili.db.MenstruationDao;
import com.bishe.myapplication.utils.MyDialog;
import com.bishe.myapplication.utils.SettingJingqiDialog;
import com.bishe.myapplication.utils.MySharedPreferences;

/**
 * 设置界面
 */
public class SettingFragment extends Fragment implements View.OnClickListener {
    private View view;
    private LinearLayout mSetingZhanghao;
    private LinearLayout mSetingJingqiday;
    private LinearLayout mSetingZhouqiday;
    private LinearLayout mSetingTixing;
    /**
     * 退出登录
     */
    private Button mBtnSetingOut;
    /**
     * 122122
     */
    private TextView mTvZhanghao;
    /**
     * 6天
     */
    private TextView mTvJignqiday;
    /**
     * 9天
     */
    private TextView mTvZhouqiday;
    /**
     * 已开启
     */
    private TextView mTvTixing;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        initView(v);
        return v;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initView(View v) {
        mSetingZhanghao = (LinearLayout) v.findViewById(R.id.seting_zhanghao);
        mSetingZhanghao.setOnClickListener(this);
        mSetingJingqiday = (LinearLayout) v.findViewById(R.id.seting_jingqiday);
        mSetingJingqiday.setOnClickListener(this);
        mSetingZhouqiday = (LinearLayout) v.findViewById(R.id.seting_zhouqiday);
        mSetingZhouqiday.setOnClickListener(this);
        mSetingTixing = (LinearLayout) v.findViewById(R.id.seting_tixing);
        mSetingTixing.setOnClickListener(this);
        mBtnSetingOut = (Button) v.findViewById(R.id.btn_seting_out);
        mBtnSetingOut.setOnClickListener(this);
        mTvZhanghao = (TextView) v.findViewById(R.id.tv_zhanghao);
        mTvJignqiday = (TextView) v.findViewById(R.id.tv_jignqiday);
        mTvZhouqiday = (TextView) v.findViewById(R.id.tv_zhouqiday);
        mTvTixing = (TextView) v.findViewById(R.id.tv_tixing);

        mTvZhanghao.setText(MySharedPreferences.getName());
        mTvJignqiday.setText(MySharedPreferences.getJingqiTime() + "天");
        mTvZhouqiday.setText(MySharedPreferences.getZhouqiTime() + "天");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.seting_zhanghao:
                break;
            case R.id.seting_jingqiday:
                new SettingJingqiDialog(getActivity(), R.style.DialogTheme, new SettingJingqiDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm, int selectTime) {
                        Log.i("stw", "onClick:设置经期 " + selectTime);
                        if (confirm) {
                            MySharedPreferences.setJingqiTime(selectTime);
                            mTvJignqiday.setText(selectTime + "天");
                            MenstruationCycle mc = new MenstruationCycle();
                            mc.setNumber(MySharedPreferences.getJingqiTime());
                            mc.setCycle(MySharedPreferences.getZhouqiTime());
                            new MenstruationDao(getActivity()).upMTCycle(mc);
                        }
                    }
                }, "jingqi").setTitle("设置经期").show();
                break;
            case R.id.seting_zhouqiday:
                new SettingJingqiDialog(getActivity(), R.style.DialogTheme, new SettingJingqiDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm, int selectTime) {
                        Log.i("stw", "onClick:设置周期 " + selectTime);
                        if (confirm) {
                            MySharedPreferences.setZhouqiTime(selectTime);
                            mTvZhouqiday.setText(selectTime + "天");
                            MenstruationCycle mc = new MenstruationCycle();
                            mc.setNumber(MySharedPreferences.getJingqiTime());
                            mc.setCycle(MySharedPreferences.getZhouqiTime());
                            new MenstruationDao(getActivity()).upMTCycle(mc);
                        }
                    }
                }, "zhouqi").setTitle("设置周期").show();
                break;
            case R.id.seting_tixing:
                //提醒
                Toast.makeText(getActivity(), "暂未开发", Toast.LENGTH_SHORT).show();
                //跳转提醒设置界面
                getFragmentManager().beginTransaction().addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.fragment, new SettingTixingFragment()).commit();
                break;
            case R.id.btn_seting_out:
                new MyDialog(getActivity(), R.style.DialogTheme, "确定要退出登录吗？", new MyDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        //确定按钮点击监听
                        if (confirm) {
                            //修改登录状态
                            MySharedPreferences.setIslogin(false);
                            //清楚数据保存
                            MySharedPreferences.clearAll();
                            //删除数据库数据
                            MenstruationDao mtDao = new MenstruationDao(getActivity());
                            mtDao.deleteALL();
                            //跳转登录界面
                            Intent intent = new Intent(getActivity(), LogInActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    }
                }).show();
                break;
        }
    }

}

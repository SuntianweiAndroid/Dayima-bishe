package com.bishe.myapplication.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.bishe.myapplication.dayimarili.MenstruationCycle;
import com.bishe.myapplication.dayimarili.db.MenstruationDBHelper;
import com.bishe.myapplication.dayimarili.db.MenstruationDao;
import com.bishe.myapplication.utils.CommomDialog;
import com.bishe.myapplication.utils.CommomDialog2;
import com.bishe.myapplication.utils.MySharedPreferences;

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
//        mTvTixing.setText(MySharedPreferences.getJingqiTime());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.seting_zhanghao:
                break;
            case R.id.seting_jingqiday:
                new CommomDialog2(getActivity(), R.style.DialogTheme, new CommomDialog2.OnCloseListener() {
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
                new CommomDialog2(getActivity(), R.style.DialogTheme, new CommomDialog2.OnCloseListener() {
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
                getFragmentManager().beginTransaction().addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.fragment, new SettingTixingFragment()).commit();
                Toast.makeText(getActivity(), "暂未开发", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_seting_out:
                new CommomDialog(getActivity(), R.style.DialogTheme, "确定要退出登录吗？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            MySharedPreferences.setIslogin(false);
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

    private void showNormalDialog(final Context context) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setMessage("确定要退出登录吗?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MySharedPreferences.setIslogin(false);
                        MySharedPreferences.clearAll();
                        MenstruationDao mtDao = new MenstruationDao(getActivity());
                        mtDao.deleteALL();
                        Intent intent = new Intent(context, LogInActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }
}

package com.bishe.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bishe.myapplication.R;
/**
 * 提醒设置界面
 */
public class SettingTixingFragment extends Fragment implements View.OnClickListener {


    private View view;
    private ImageButton mImgBack;
    /**
     * 提醒
     */
    private TextView mTvTitle;
    /**
     * 已开启
     */
    private TextView mTvJingqi;
    private LinearLayout mSetingJingqi;
    /**
     * 已开启
     */
    private TextView mTvJingqiTuichi;
    private LinearLayout mSetingJingqiTuichi;
    /**
     * 已开启
     */
    private TextView mTvYiyun;
    private LinearLayout mSetingYiyun;
    /**
     * 已开启
     */
    private TextView mTvPailuan;
    private LinearLayout mSetingPailuan;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_setting_tixing, container, false);

        initView(v);
        return v;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void initView(View v) {
        mImgBack = (ImageButton) v.findViewById(R.id.img_back);
        mImgBack.setOnClickListener(this);
        mTvTitle = (TextView) v.findViewById(R.id.tv_title);
        mTvJingqi = (TextView) v.findViewById(R.id.tv_jingqi);
        mSetingJingqi = (LinearLayout) v.findViewById(R.id.seting_jingqi);
        mSetingJingqi.setOnClickListener(this);
        mTvJingqiTuichi = (TextView) v.findViewById(R.id.tv_jingqi_tuichi);
        mSetingJingqiTuichi = (LinearLayout) v.findViewById(R.id.seting_jingqi_tuichi);
        mSetingJingqiTuichi.setOnClickListener(this);
        mTvYiyun = (TextView) v.findViewById(R.id.tv_yiyun);
        mSetingYiyun = (LinearLayout) v.findViewById(R.id.seting_yiyun);
        mSetingYiyun.setOnClickListener(this);
        mTvPailuan = (TextView) v.findViewById(R.id.tv_pailuan);
        mSetingPailuan = (LinearLayout) v.findViewById(R.id.seting_pailuan);
        mSetingPailuan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img_back:
                getFragmentManager().popBackStack();

                break;
            case R.id.seting_jingqi:
                break;
            case R.id.seting_jingqi_tuichi:
                break;
            case R.id.seting_yiyun:
                break;
            case R.id.seting_pailuan:
                break;
        }
    }
}

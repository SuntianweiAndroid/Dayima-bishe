package com.bishe.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bishe.myapplication.R;
import com.bishe.myapplication.testrili.DateView;
import com.bishe.myapplication.utils.MySharedPreferences;

public class RiliFragment extends Fragment {


    private View view;
    /**
     * 回今天
     */
    private TextView mTvToday;
    private ImageView mIvClickLeftMonth;
    private TextView mTvDate;
    private ImageView mIvClickRightMonth;
    private DateView mDateView;
    private LinearLayout mLlMtCome;
    private LinearLayout mLlMtBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rili, container, false);
        initView(v);
        return v;
    }


    private void initView(View v) {
        mDateView = (DateView) v.findViewById(R.id.date_view);
        mLlMtCome = (LinearLayout) v.findViewById(R.id.ll_mt_come);
        mLlMtBack = (LinearLayout) v.findViewById(R.id.ll_mt_back);
        mDateView.initDate(MySharedPreferences.getRiqiTime2());
        mDateView.setOnItemClick(new DateView.OnItemClick() {
            @Override
            public void onItemClick(String date) {

            }
        });
    }
}


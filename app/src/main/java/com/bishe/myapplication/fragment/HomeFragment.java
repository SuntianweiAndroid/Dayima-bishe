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
import com.bishe.myapplication.view.DateCardModel;
import com.bishe.myapplication.view.DateChange;
import com.bishe.myapplication.view.DateView;
import com.bishe.myapplication.view.MenstruationCycle;
import com.bishe.myapplication.view.MenstruationDao;
import com.bishe.myapplication.view.MenstruationModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {

    private TextView textView;
    private DateView dateView;
    private View view;
    /**
     * 回今天
     */
    private TextView tvToday;
    private ImageView ivClickLeftMonth;
    private TextView tvDate;
    private ImageView ivClickRightMonth;
    private LinearLayout llMtCome;
    private LinearLayout llMtBack;
    private MenstruationCycle mCycle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private long nowTime = 0;//点击的日期
    private DateCardModel dcm;//点击的月份

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        initView(v);
//        dateView = (DateView) v.findViewById(R.id.date_view);
//        dateView.setOnItemClickListener(new DateView.OnItemListener() {
//
//            @Override
//            public void onClick(long time, DateCardModel d) {
//                nowTime = time;
//                dcm = d;
//                if (time > DateChange.getDate()) {
//                    llMtCome.setVisibility(View.GONE);
//                    llMtBack.setVisibility(View.GONE);
//                    return;
//                } else if (dcm.type == 1) {
//                    llMtCome.setVisibility(View.GONE);
//                    llMtBack.setVisibility(View.VISIBLE);
//
//                }
////                else if (mtDao.getEndTimeNumber(nowTime) < 6) {
////                    llMtCome.setVisibility(View.GONE);
////                    llMtBack.setVisibility(View.VISIBLE);
////
////                } else if (dcm.type != 1) {
////                    llMtCome.setVisibility(View.VISIBLE);
////                    llMtBack.setVisibility(View.GONE);
////
////                }
//            }
//        });
//        tvDate.setText(dateView.getYearAndmonth());

        return v;
    }

    /**
     * 初始化大姨妈数据
     */
    private void initData() {
        Calendar calendar = Calendar.getInstance();
        Date curDate = new Date();
        calendar.setTime(curDate);
        MenstruationDao mtDao = new MenstruationDao(getContext());
        mCycle = mtDao.getMTCycle();
        long nowDate = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-1", "yyyy-MM-dd");
        long nextDate = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 2) + "-1", "yyyy-MM-dd");
        //获取当月数据
        List<MenstruationModel> mtmList = mtDao.getMTModelList(nowDate, nextDate);
        //获取全部数据
        List<MenstruationModel> list = mtDao.getMTModelList(0, 0);
        //当数据库中没有本月记录时，根据上一次的记录预测本月记录
        for (int i = 0; i < mtmList.size(); i++) {
            mtmList.get(i).setCon(true);
        }
        if (mtmList.size() == 0) {
            MenstruationModel mtm = new MenstruationModel();
            mtm.setDate(nowDate);
            mtm.setBeginTime(list.get(list.size() - 1).getBeginTime() + intervalTime(list.get(list.size() - 1).getBeginTime(), nowDate));
            mtm.setEndTime(list.get(list.size() - 1).getBeginTime() + intervalTime(list.get(list.size() - 1).getBeginTime(), nowDate) + 86400000l * (mCycle.getNumber() - 1));
            mtm.setCon(false);
            //如果当月没有记录，就根据之前的数据来预测现在的来月经时间，如果根据之前预测的时间小于当天时间就从现在开始记录
            if (mtm.getBeginTime() > DateChange.getDate()) {
                mtmList.add(mtm);
            } else {
                mtm.setBeginTime(DateChange.getDate());
                mtm.setEndTime(DateChange.getDate() + 86400000l * 4);
                mtmList.add(mtm);
            }
            //记录预测的基准

        } else {
            //记录预测的基准

        }
        //下一次的月经是否在当月

        dateView.initData(mtmList);
    }

    /**
     * 计算间隔时间
     *
     * @param startTime
     * @param endTime
     * @return
     */
    private long intervalTime(long startTime, long endTime) {
        int i = (int) ((endTime - startTime) / 86400000 / mCycle.getCycle());
        i = (endTime - startTime) / 86400000 % mCycle.getCycle() == 0 ? i - 1 : i;
        return i * 86400000l * mCycle.getCycle();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initView(View v) {
        tvToday = (TextView) v.findViewById(R.id.tv_today);
        ivClickLeftMonth = (ImageView) v.findViewById(R.id.iv_click_left_month);
        tvDate = (TextView) v.findViewById(R.id.tv_date);
        ivClickRightMonth = (ImageView) v.findViewById(R.id.iv_click_right_month);
//        dateView = (DateView) v.findViewById(R.id.date_view);
        llMtCome = (LinearLayout) v.findViewById(R.id.ll_mt_come);
        llMtBack = (LinearLayout) v.findViewById(R.id.ll_mt_back);
    }
}

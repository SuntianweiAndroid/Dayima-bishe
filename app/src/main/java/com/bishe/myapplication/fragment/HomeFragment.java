package com.bishe.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bishe.myapplication.R;
import com.bishe.myapplication.dayimarili.DateCardModel;
import com.bishe.myapplication.dayimarili.DateChange;
import com.bishe.myapplication.dayimarili.DateView;
import com.bishe.myapplication.dayimarili.DateView2;
import com.bishe.myapplication.dayimarili.DateView3;
import com.bishe.myapplication.dayimarili.MenstruationCycle;
import com.bishe.myapplication.dayimarili.MenstruationModel;
import com.bishe.myapplication.dayimarili.db.MenstruationDao;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {

    private TextView tvDate;
    private TextView tvDate2;
    private DateView2 dateView;
    private DateView3 dateView3;
    private TextView llMtCome, llMtBack;
    private MenstruationDao mtDao;
    private MenstruationCycle mCycle;
    private Date curDate; // 当前日历显示的月
    private Calendar calendar;
    private MenstruationModel mtmBass;//预测大姨妈的基础数据
    private long nowTime = 0;//点击的日期
    private DateCardModel dcm;//点击的月份
    private List<MenstruationModel> list;
    private Context mContext;
    private List<MenstruationModel> mtmList3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = getActivity();
        initView(v);
        initData();
        setListener(v);
        return v;
    }

    private void initView(View v) {
        tvDate = (TextView) v.findViewById(R.id.tv_date);
        tvDate2 = (TextView) v.findViewById(R.id.tv_date2);
        dateView = (DateView2) v.findViewById(R.id.date_view);
        dateView3 = (DateView3) v.findViewById(R.id.date_view2);
//        dateView2 = (DateView) v.findViewById(R.id.date_view2);
        dateView.setOnItemClickListener(new DateView2.OnItemListener() {

            @Override
            public void onClick(long time, DateCardModel d) {
                nowTime = time;
                dcm = d;
                if (time > DateChange.getDate()) {
                    llMtCome.setVisibility(View.GONE);
                    llMtBack.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "无法记录未来哦", Toast.LENGTH_SHORT).show();
                    return;
                } else if (dcm.type == 1) {
                    llMtCome.setVisibility(View.GONE);
                    llMtBack.setVisibility(View.VISIBLE);

                } else if (mtDao.getEndTimeNumber(nowTime) < 6) {
                    llMtCome.setVisibility(View.GONE);
                    llMtBack.setVisibility(View.VISIBLE);

                } else if (dcm.type != 1) {
                    llMtCome.setVisibility(View.VISIBLE);
                    llMtBack.setVisibility(View.GONE);

                }
            }
        });
        dateView3.setOnItemClickListener(new DateView3.OnItemListener() {

            @Override
            public void onClick(long time, DateCardModel d) {
                nowTime = time;
                dcm = d;
                if (time > DateChange.getDate()) {
                    llMtCome.setVisibility(View.GONE);
                    llMtBack.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "无法记录未来哦", Toast.LENGTH_SHORT).show();
                    return;
                } else if (dcm.type == 1) {
                    llMtCome.setVisibility(View.GONE);
                    llMtBack.setVisibility(View.VISIBLE);

                } else if (mtDao.getEndTimeNumber(nowTime) < 6) {
                    llMtCome.setVisibility(View.GONE);
                    llMtBack.setVisibility(View.VISIBLE);

                } else if (dcm.type != 1) {
                    llMtCome.setVisibility(View.VISIBLE);
                    llMtBack.setVisibility(View.GONE);

                }
            }
        });

        llMtCome = (TextView) v.findViewById(R.id.ll_mt_come);
        llMtBack = (TextView) v.findViewById(R.id.ll_mt_back);


    }

    /**
     * 初始化大姨妈数据
     */
    private void initData() {
        calendar = Calendar.getInstance();
        curDate = new Date();
        calendar.setTime(curDate);
        mtDao = new MenstruationDao(mContext);
        mCycle = mtDao.getMTCycle();
        long nowDate = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-1", "yyyy-MM-dd");
        long nextDate = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 2) + "-1", "yyyy-MM-dd");
        //获取当月数据 为空
        List<MenstruationModel> mtmList = mtDao.getMTModelList(nowDate, nextDate);
        //获取全部数据
        list = mtDao.getMTModelList(0, 0);
        //当数据库中没有本月记录时，根据上一次的记录预测本月记录
        for (int i = 0; i < mtmList.size(); i++) {
            mtmList.get(i).setCon(true);
        }
        if (mtmList.size() == 0) {
            MenstruationModel mtm = new MenstruationModel();
            mtm.setDate(nowDate);
            mtm.setBeginTime(list.get(list.size() - 1).getBeginTime() + intervalTime(list.get(list.size() - 1).getBeginTime(), nowDate));
            mtm.setEndTime(list.get(list.size() - 1).getBeginTime() + intervalTime(list.get(list.size() - 1).getBeginTime(), nowDate) + 86400000L * (mCycle.getNumber() - 1));
            mtm.setCon(false);
            //如果当月没有记录，就根据之前的数据来预测现在的来月经时间，如果根据之前预测的时间小于当天时间就从现在开始记录
            if (mtm.getBeginTime() > DateChange.getDate()) {
                mtmList.add(mtm);
            } else {
                mtm.setBeginTime(DateChange.getDate());
                mtm.setEndTime(DateChange.getDate() + 86400000L * 4);
                mtmList.add(mtm);
            }
            //记录预测的基准
            mtmBass = mtm;
        } else {
            //记录预测的基准
            mtmBass = mtmList.get(mtmList.size() - 1);
        }
        //下一次的月经是否在当月
        MenstruationModel mtm = new MenstruationModel();
        mtm.setBeginTime(mtmBass.getBeginTime() + 86400000L * 30);
        mtm.setEndTime(mtmBass.getBeginTime() + 86400000L * 30 + 86400000L * (mCycle.getNumber() - 1));
        mtm.setDate(nowDate);
        mtm.setCon(false);
        if (nextDate > mtm.getBeginTime()) {
            if (mtm.getBeginTime() > DateChange.getDate()) {
                mtmList.add(mtm);
            } else {
                mtm.setBeginTime(DateChange.getDate());
                mtm.setEndTime(DateChange.getDate() + 86400000L * 4);
                mtmList.add(mtm);
            }
            mtmBass = mtm;
        }
        dateView.initData(mtmList);
        tvDate.setText(dateView.getYearAndmonth());
        //初始化 的哥日历表格
        Calendar calendar = Calendar.getInstance();
        Date curDate = new Date();
        calendar.setTime(curDate);
        calendar.add(Calendar.MONTH, 1);
        curDate = calendar.getTime();
        long nowDate3 = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-1", "yyyy-MM-dd");
        long nextDate3 = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 2) + "-1", "yyyy-MM-dd");
        mtmList3 = calculateMt(nowDate3, nextDate3);
        dateView3.initData(mtmList);
        tvDate2.setText(dateView3.clickRightMonth(mtmList3));
        tvDate2.setText(dateView3.getYearAndmonth());
    }

    /**
     * 获取并预测大姨妈
     *
     * @param nowDate  当月时间
     * @param nextDate 下月时间
     * @return
     */
    private List<MenstruationModel> calculateMt(long nowDate, long nextDate) {
        //获取本月大姨妈数据
        List<MenstruationModel> mtmList = mtDao.getMTModelList(nowDate, nextDate);//将数据库中的当月大姨妈数据取出来
        for (int i = 0; i < mtmList.size(); i++) {
            mtmList.get(i).setCon(true);
        }
        //现在时间小于基础时间，不用计算其他的
        if (nowDate < mtmBass.getDate()) {
            return mtmList;
        }
        //如果当月没有大姨妈数据，就根据上一个月的数据预测这个月的姨妈周期
        if (nowDate == mtmBass.getDate()) {
            //现在时间跟基础时间相同
            if (!mtmBass.isCon()) {
                mtmList.add(mtmBass);
            }
        } else {
            //不同就根据基础时间预测
            MenstruationModel mtm1 = new MenstruationModel();
            mtm1.setBeginTime(mtmBass.getBeginTime() + intervalTime(mtmBass.getDate(), nowDate));
            mtm1.setEndTime(mtmBass.getBeginTime() + intervalTime(mtmBass.getDate(), nowDate) + 86400000L * (mCycle.getNumber() - 1));
            mtm1.setCon(false);
            mtmList.add(mtm1);
            //判断下一次的大姨妈是否在本月
            MenstruationModel mtm = new MenstruationModel();
            mtm.setBeginTime(mtmBass.getBeginTime() + intervalTime(mtmBass.getDate(), nowDate) + 86400000L * 28);
            mtm.setEndTime(mtmBass.getBeginTime() + intervalTime(mtmBass.getDate(), nowDate) + 86400000L * 28 + 86400000L * (mCycle.getNumber() - 1));
            mtm.setCon(false);
            if (nextDate > mtm.getBeginTime()) {
                if (mtm.getBeginTime() > DateChange.getDate()) {
                    mtmList.add(mtm);
                } else {
                    mtm.setBeginTime(DateChange.getDate());
                    mtm.setEndTime(DateChange.getDate() + 86400000L * 4);
                    mtmList.add(mtm);
                }
            }
        }
        //判断上一次的大姨妈是否有在本月
        MenstruationModel mtm1 = new MenstruationModel();
        mtm1.setBeginTime(mtmBass.getBeginTime() + intervalTime(mtmBass.getDate(), nowDate) - 86400000L * 28);
        mtm1.setEndTime(mtmBass.getBeginTime() + intervalTime(mtmBass.getDate(), nowDate) - 86400000L * 28 + 86400000L * (mCycle.getNumber() - 1));
        mtm1.setCon(false);
        if (nowDate <= mtm1.getEndTime()) {
            mtmList.add(mtm1);
        }
        return mtmList;
    }


    private void setListener(View v) {
//        /**
//         * 上一月
//         */
//        v.findViewById(R.id.iv_click_left_month).setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                calendar.setTime(curDate);
//                calendar.add(Calendar.MONTH, -1);
//                curDate = calendar.getTime();
//                long nowDate = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-1", "yyyy-MM-dd");
//                long nextDate = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 2) + "-1", "yyyy-MM-dd");
//                List<MenstruationModel> mtmList = calculateMt(nowDate, nextDate);
//                tvDate.setText(dateView.clickLeftMonth(mtmList));
//            }
//        });
//        /**
//         * 下一月
//         */
//        v.findViewById(R.id.iv_click_right_month).setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                calendar.setTime(curDate);
//                calendar.add(Calendar.MONTH, 1);
//                curDate = calendar.getTime();
//                long nowDate = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-1", "yyyy-MM-dd");
//                long nextDate = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 2) + "-1", "yyyy-MM-dd");
//                List<MenstruationModel> mtmList = calculateMt(nowDate, nextDate);
//                tvDate.setText(dateView.clickRightMonth(mtmList));
//            }
//        });
        /**
         * 回到当月
         */
        v.findViewById(R.id.back_taday).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                calendar.setTime(curDate);
                calendar.add(Calendar.MONTH, getNowTime("yyyy") * 12 + getNowTime("MM") - (calendar.get(Calendar.MONTH) + 1) - calendar.get(Calendar.YEAR) * 12);
                curDate = calendar.getTime();
                long nowDate = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-1", "yyyy-MM-dd");
                long nextDate = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 2) + "-1", "yyyy-MM-dd");
                List<MenstruationModel> mtmList = mtDao.getMTModelList(nowDate, nextDate);
                for (int i = 0; i < mtmList.size(); i++) {
                    mtmList.get(i).setCon(true);
                }
                if (mtmList.size() == 0) {
                    MenstruationModel mtm = new MenstruationModel();
                    mtm.setBeginTime(list.get(list.size() - 1).getBeginTime() + intervalTime(list.get(list.size() - 1).getBeginTime(), nowDate));
                    mtm.setEndTime(list.get(list.size() - 1).getBeginTime() + intervalTime(list.get(list.size() - 1).getBeginTime(), nowDate) + 86400000L * (mCycle.getNumber() - 1));
                    mtm.setCon(false);
                    if (mtm.getBeginTime() > DateChange.getDate()) {
                        mtmList.add(mtm);
                    } else {
                        mtm.setBeginTime(DateChange.getDate());
                        mtm.setEndTime(DateChange.getDate() + 86400000L * 4);
                        mtm.setCon(false);
                        mtmList.add(mtm);
                    }
                }
                mtmList.add(mtmBass);
                tvDate2.setText(dateView3.recurToday(mtmList));
            }
        });

        /**
         * 姨妈来了
         */
        llMtCome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                long startTime = mtDao.getStartTimeNumber(nowTime);
                if ((startTime - nowTime) / 86400000 < 9 && (startTime - nowTime) / 86400000 > 0) {
                    mtDao.updateMTStartTime(nowTime, startTime);
                } else {
                    MenstruationModel mtm = new MenstruationModel();
                    mtm.setDate(DateChange.dateTimeStamp(DateChange.timeStamp2Date(nowTime + "", "yyyy-MM") + "-1", "yyyy-MM-dd"));
                    mtm.setBeginTime(nowTime);
                    mtm.setEndTime(nowTime + 86400000L * (mCycle.getNumber() - 1));
                    mtm.setCycle(mCycle.getCycle());
                    mtm.setDurationDay(mCycle.getNumber());
                    mtDao.setMTModel(mtm);
                }

//                calendar.setTime(curDate);
//                calendar.add(Calendar.MONTH, 1);
//                curDate = calendar.getTime();
//                long nowDate3 = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-1", "yyyy-MM-dd");
//                long nextDate3 = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 2) + "-1", "yyyy-MM-dd");
//                List<MenstruationModel> mtmList3 = calculateMt(nowDate3, nextDate3);
//                tvDate2.setText(dateView3.clickRightMonth(mtmList3));
//                tvDate2.setText(dateView3.getYearAndmonth());
                refreshUI();



            }
        });

        /**
         * 姨妈走了
         */
        llMtBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                mtDao.updateMTEndTime(nowTime);
//                Calendar calendar = Calendar.getInstance();
//                Date curDate = new Date();
//                calendar.setTime(curDate);
//                calendar.add(Calendar.MONTH, 1);
//                curDate = calendar.getTime();
//                long nowDate3 = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-1", "yyyy-MM-dd");
//                long nextDate3 = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 2) + "-1", "yyyy-MM-dd");
//                List<MenstruationModel> mtmList3 = calculateMt(nowDate3, nextDate3);
//                tvDate2.setText(dateView3.clickRightMonth(mtmList3));
//                tvDate2.setText(dateView3.getYearAndmonth());
                refreshUI();

            }
        });

    }

    /**
     * 刷新UI
     */
    private void refreshUI() {
        long nowDate = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-1", "yyyy-MM-dd");
        long nextDate = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 2) + "-1", "yyyy-MM-dd");
        //获取当月数据
        List<MenstruationModel> mtmList = mtDao.getMTModelList(nowDate, nextDate);
        //获取全部数据
        list = mtDao.getMTModelList(0, 0);
        for (int i = 0; i < mtmList.size(); i++) {
            mtmList.get(i).setCon(true);
        }
        //下一次的月经是否在当月
        MenstruationModel mtm = new MenstruationModel();
        mtm.setBeginTime(mtmBass.getBeginTime() + 86400000L * 28);
        mtm.setEndTime(mtmBass.getBeginTime() + 86400000L * 28 + 86400000L * (mCycle.getNumber() - 1));
        mtm.setDate(nowDate);
        mtm.setCon(false);
        if (nextDate > mtm.getBeginTime()) {
            if (mtm.getBeginTime() > DateChange.getDate()) {
                mtmList.add(mtm);
            } else {
                mtm.setBeginTime(DateChange.getDate());
                mtm.setEndTime(DateChange.getDate() + 86400000L * 4);
                mtmList.add(mtm);
            }
        }
        dateView.refreshUI(mtmList);
        dateView3.recurToday(mtmList3);


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
        return i * 86400000L * mCycle.getCycle();
    }

    /**
     * 获取当天日期
     *
     * @param format
     * @return
     */
    public int getNowTime(String format) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);// 可以方便地修改日期格式
        String hehe = dateFormat.format(now);
        return Integer.parseInt(hehe);
    }
}


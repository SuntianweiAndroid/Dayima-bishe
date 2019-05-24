package com.bishe.myapplication.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.bishe.myapplication.dayimarili.db.MenstruationCycle;
import com.bishe.myapplication.dayimarili.db.MenstruationModel;
import com.bishe.myapplication.dayimarili.db.MenstruationMt;
import com.bishe.myapplication.dayimarili.db.MenstruationDao;
import com.bishe.myapplication.utils.JiluDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RiliFragment extends Fragment implements View.OnClickListener {
    private TextView tvDate;
    private DateView dateView;
    private TextView llMtCome, llMtBack;
    private MenstruationDao mtDao;
    private MenstruationCycle mCycle;
    private Date curDate; // 当前日历显示的月
    private Calendar calendar;
    private MenstruationModel mtmBass;//预测大姨妈的基础数据
    private MenstruationModel mtmBass2;//预测大姨妈的基础数据
    private long nowTime = 0;//点击的日期
    private DateCardModel dcm;//点击的月份
    private List<MenstruationModel> list;
    private Context mContext;
    private View view;
    private TextView mRiliJilu;
    private TextView mBtnTixing;
    private TextView mBtnTixing2;
    private TextView mBtnTixing3;
    /**
     * dddd
     */
    private TextView mBtnTixing4;
    private LinearLayout mLinerlayoutJilu;
    private TextView mBtnTixing5;
    private TextView mBtnTixing6;
    private LinearLayout mLinerlayoutJilu1;
    private TextView mBtnTixing7;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rili, container, false);
        mContext = getActivity();
        initView(v);
        initData();
        setListener(v);
        return v;
    }

    private void initView(final View v) {

        dateView = (DateView) v.findViewById(R.id.date_view);
        dateView.setOnItemClickListener(new DateView.OnItemListener() {

            @Override
            public void onClick(long time, DateCardModel d) {
                nowTime = time;
                dcm = d;
                //判断经期开始日期是否大于现在时间
                if (time > DateChange.getDate()) {
                    llMtBack.setVisibility(View.VISIBLE);
                    llMtCome.setVisibility(View.GONE);
                    mLinerlayoutJilu.setVisibility(View.GONE);
                    mLinerlayoutJilu1.setVisibility(View.GONE);
                    mRiliJilu.setVisibility(View.GONE);
                    v.findViewById(R.id.back_taday).setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "无法记录未来哦", Toast.LENGTH_SHORT).show();
                    return;
                    //
                } else if (dcm.type == 1) {
                    llMtCome.setVisibility(View.VISIBLE);
                    mLinerlayoutJilu.setVisibility(View.GONE);
                    mLinerlayoutJilu1.setVisibility(View.GONE);
                    llMtBack.setVisibility(View.GONE);
                    mRiliJilu.setVisibility(View.VISIBLE);
                    v.findViewById(R.id.back_taday).setVisibility(View.GONE);
                    MenstruationMt mt = mtDao.getMTMT(nowTime);
                    if (mt != null) {
                        llMtCome.setVisibility(View.GONE);
                        mRiliJilu.setVisibility(View.GONE);
                        //获取记录内容
                        String jilu = mt.getQuantity();
                        String[] splitstr = jilu.split(",");
                        for (String res : splitstr) {
                            Log.e("截取保存记录的内容：", res);
                        }
                        List<String> jilu2 = new ArrayList<>();
                        for (int i = 0; i < splitstr.length; i++) {
                            if (!splitstr[i].equals("")) {
                                jilu2.add(splitstr[i]);
                            }
                        }
                        if (splitstr.length > 4) {
                            mLinerlayoutJilu.setVisibility(View.VISIBLE);
                            mLinerlayoutJilu1.setVisibility(View.VISIBLE);

                        } else {
                            mLinerlayoutJilu.setVisibility(View.VISIBLE);
                        }
                        mBtnTixing.setVisibility(View.INVISIBLE);
                        mBtnTixing2.setVisibility(View.INVISIBLE);
                        mBtnTixing3.setVisibility(View.INVISIBLE);
                        mBtnTixing4.setVisibility(View.INVISIBLE);
                        mBtnTixing5.setVisibility(View.INVISIBLE);
                        mBtnTixing6.setVisibility(View.INVISIBLE);
                        mBtnTixing7.setVisibility(View.INVISIBLE);
                        for (int i = 0; i < jilu2.size(); i++) {
                            switch (i) {
                                case 0:
                                    mBtnTixing.setText(jilu2.get(i));
                                    mBtnTixing.setVisibility(View.VISIBLE);
                                    break;
                                case 1:
                                    mBtnTixing2.setText(jilu2.get(i));
                                    mBtnTixing2.setVisibility(View.VISIBLE);
                                    break;
                                case 2:
                                    mBtnTixing3.setText(jilu2.get(i));
                                    mBtnTixing3.setVisibility(View.VISIBLE);
                                    break;
                                case 3:
                                    mBtnTixing4.setText(jilu2.get(i));
                                    mBtnTixing4.setVisibility(View.VISIBLE);
                                    break;
                                case 4:
                                    mBtnTixing5.setText(jilu2.get(i));
                                    mBtnTixing5.setVisibility(View.VISIBLE);
                                    break;
                                case 5:
                                    mBtnTixing6.setText(jilu2.get(i));
                                    mBtnTixing6.setVisibility(View.VISIBLE);
                                    break;
                                case 6:
                                    mBtnTixing7.setText(jilu2.get(i));
                                    mBtnTixing7.setVisibility(View.VISIBLE);
                                    break;
                            }

                        }
                    } else {
                        llMtCome.setVisibility(View.VISIBLE);
                        mLinerlayoutJilu.setVisibility(View.GONE);
                        mLinerlayoutJilu1.setVisibility(View.GONE);
                    }
                } else if (mtDao.getEndTimeNumber(nowTime) < 6) {
                    llMtCome.setVisibility(View.VISIBLE);
                    llMtBack.setVisibility(View.GONE);
                    mLinerlayoutJilu.setVisibility(View.GONE);
                    mLinerlayoutJilu1.setVisibility(View.GONE);
                    mRiliJilu.setVisibility(View.VISIBLE);
                    v.findViewById(R.id.back_taday).setVisibility(View.GONE);
                } else if (dcm.type != 1) {
                    MenstruationMt mt = mtDao.getMTMT(nowTime);
                    if (mt != null) {
                        mRiliJilu.setVisibility(View.GONE);
                        llMtCome.setVisibility(View.GONE);
                        String jilu = mt.getQuantity();
                        String[] splitstr = jilu.split(",");
                        for (String res : splitstr) {
                            Log.e("输出截图的内容：", res);
                        }
                        List<String> jilu2 = new ArrayList<>();
                        for (int i = 0; i < splitstr.length; i++) {
                            if (!splitstr[i].equals("")) {
                                jilu2.add(splitstr[i]);

                            }
                        }
                        if (jilu2.size() > 5) {
                            mLinerlayoutJilu.setVisibility(View.VISIBLE);
                            mLinerlayoutJilu1.setVisibility(View.VISIBLE);

                        } else {
                            mLinerlayoutJilu.setVisibility(View.VISIBLE);
                        }
                        mBtnTixing.setVisibility(View.INVISIBLE);
                        mBtnTixing2.setVisibility(View.INVISIBLE);
                        mBtnTixing3.setVisibility(View.INVISIBLE);
                        mBtnTixing4.setVisibility(View.INVISIBLE);
                        mBtnTixing5.setVisibility(View.INVISIBLE);
                        mBtnTixing6.setVisibility(View.INVISIBLE);
                        mBtnTixing7.setVisibility(View.INVISIBLE);
                        for (int i = 0; i < jilu2.size(); i++) {
                            switch (i) {
                                case 0:
                                    mBtnTixing.setText(jilu2.get(i));
                                    mBtnTixing.setVisibility(View.VISIBLE);
                                    break;
                                case 1:
                                    mBtnTixing2.setText(jilu2.get(i));
                                    mBtnTixing2.setVisibility(View.VISIBLE);
                                    break;
                                case 2:
                                    mBtnTixing3.setText(jilu2.get(i));
                                    mBtnTixing3.setVisibility(View.VISIBLE);
                                    break;
                                case 3:
                                    mBtnTixing4.setText(jilu2.get(i));
                                    mBtnTixing4.setVisibility(View.VISIBLE);
                                    break;
                                case 4:
                                    mBtnTixing5.setText(jilu2.get(i));
                                    mBtnTixing5.setVisibility(View.VISIBLE);
                                    break;
                                case 5:
                                    mBtnTixing6.setText(jilu2.get(i));
                                    mBtnTixing6.setVisibility(View.VISIBLE);
                                    break;
                                case 6:
                                    mBtnTixing7.setText(jilu2.get(i));
                                    mBtnTixing7.setVisibility(View.VISIBLE);
                                    break;
                            }

                        }
                    } else {
                        llMtCome.setVisibility(View.VISIBLE);
                        llMtBack.setVisibility(View.GONE);
                        mLinerlayoutJilu.setVisibility(View.GONE);
                        mLinerlayoutJilu1.setVisibility(View.GONE);
                        mRiliJilu.setVisibility(View.VISIBLE);
                        v.findViewById(R.id.back_taday).setVisibility(View.GONE);
                    }
                }
            }
        });
        tvDate = (TextView) v.findViewById(R.id.tv_date);
        llMtCome = (TextView) v.findViewById(R.id.ll_mt_come);
        llMtBack = (TextView) v.findViewById(R.id.ll_mt_back);
        mRiliJilu = (TextView) v.findViewById(R.id.rili_jilu);
        mRiliJilu.setOnClickListener(this);

        mBtnTixing = (TextView) v.findViewById(R.id.btn_tixing);
        mBtnTixing2 = (TextView) v.findViewById(R.id.btn_tixing2);
        mBtnTixing3 = (TextView) v.findViewById(R.id.btn_tixing3);
        mBtnTixing4 = (TextView) v.findViewById(R.id.btn_tixing4);
        mLinerlayoutJilu = (LinearLayout) v.findViewById(R.id.linerlayout_jilu);
        mBtnTixing5 = (TextView) v.findViewById(R.id.btn_tixing5);
        mBtnTixing6 = (TextView) v.findViewById(R.id.btn_tixing6);
        mLinerlayoutJilu1 = (LinearLayout) v.findViewById(R.id.linerlayout_jilu1);
        mBtnTixing7 = (TextView) v.findViewById(R.id.btn_tixing7);
    }


    /**
     * 初始化大姨妈数据
     */
    private void initData() {
        //获取日历对象
        calendar = Calendar.getInstance();
        curDate = new Date();
        //设置当前日期
        calendar.setTime(curDate);
        //获取数据库
        mtDao = new MenstruationDao(mContext);
        //获取周期与平均天数
        mCycle = mtDao.getMTCycle();
        long nowDate = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-1", "yyyy-MM-dd");
        long nextDate = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 2) + "-1", "yyyy-MM-dd");
        //获取当月数据
        List<MenstruationModel> mtmList = mtDao.getMTModelList(nowDate, nextDate);
        //获取全部数据
        list = mtDao.getMTModelList(0, 0);
        mtmList.get(mtmList.size() - 1).setCon(true);
        mtmBass = mtmList.get(mtmList.size() - 1);
        mtmBass2 = mtmList.get(mtmList.size() - 1);
        //下一次的月经是否在当月
        MenstruationModel mtm = new MenstruationModel();
        mtm.setBeginTime(mtmBass.getBeginTime() + 86400000L * (mCycle.getCycle() - 1));
        mtm.setEndTime(mtm.getBeginTime() + 86400000L * (mCycle.getNumber() - 1));
        mtm.setDate(nowDate);
        mtm.setCon(false);
        //判断下次时间是否大于保存下次时间
        if (nextDate > mtm.getBeginTime()) {
            if (mtm.getBeginTime() > DateChange.getDate()) {
                mtmList.add(mtm);
            } else {
                mtm.setBeginTime(DateChange.getDate());
                mtm.setEndTime(DateChange.getDate() + 86400000L * (mCycle.getNumber() - 1));
                mtmList.add(mtm);
            }
            mtmBass = mtm;
        }
        //日历控件初始化数据
        dateView.initData(mtmList);
        //设置主界面时间
        tvDate.setText(dateView.getYearAndmonth());
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
            mtm.setBeginTime(mtmBass.getBeginTime() + intervalTime(mtmBass.getDate(), nowDate) + 86400000L * (mCycle.getCycle() - 1));
            mtm.setEndTime(mtm.getBeginTime() + intervalTime(mtm.getDate(), nowDate)  + 86400000L * (mCycle.getNumber() - 1));
            mtm.setCon(false);
            if (nextDate > mtm.getBeginTime()) {
                if (mtm.getBeginTime() > DateChange.getDate()) {
                    mtmList.add(mtm);
                } else {
                    mtm.setBeginTime(DateChange.getDate());
                    mtm.setEndTime(DateChange.getDate() + 86400000L * (mCycle.getNumber() - 1));
                    mtmList.add(mtm);
                }
            }
        }
        //判断上一次的大姨妈是否有在本月
        MenstruationModel mtm1 = new MenstruationModel();
        mtm1.setBeginTime(mtmBass.getBeginTime() + intervalTime(mtmBass.getDate(), nowDate) - (mCycle.getCycle() - 1));
        mtm1.setEndTime(mtmBass.getBeginTime() + intervalTime(mtmBass.getDate(), nowDate) + 86400000L * (mCycle.getNumber() - 1));
        mtm1.setCon(false);
        if (nowDate <= mtm1.getEndTime()) {
            mtmList.add(mtm1);
        }
        return mtmList;
    }


    private void setListener(View v) {
        /**
         * 上一月
         */
        v.findViewById(R.id.iv_click_left_month).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                calendar.setTime(curDate);
                calendar.add(Calendar.MONTH, -1);
                curDate = calendar.getTime();
                long nowDate = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-1", "yyyy-MM-dd");
                long nextDate = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 2) + "-1", "yyyy-MM-dd");
                List<MenstruationModel> mtmList = calculateMt(nowDate, nextDate);
                tvDate.setText(dateView.clickLeftMonth(mtmList));
            }
        });
        /**
         * 下一月
         */
        v.findViewById(R.id.iv_click_right_month).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                calendar.setTime(curDate);
                calendar.add(Calendar.MONTH, 1);
                curDate = calendar.getTime();
                long nowDate = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-1", "yyyy-MM-dd");
                long nextDate = DateChange.dateTimeStamp(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 2) + "-1", "yyyy-MM-dd");
                List<MenstruationModel> mtmList = calculateMt(nowDate, nextDate);
                tvDate.setText(dateView.clickRightMonth(mtmList));
            }
        });
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
                        mtm.setEndTime(DateChange.getDate() + 86400000L * (mCycle.getNumber() - 1));
                        mtm.setCon(false);
                        mtmList.add(mtm);
                    }
                }
                mtmList.add(mtmBass);
                tvDate.setText(dateView.recurToday(mtmList));
            }
        });

    }

    /**
     * 计算间隔时间
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
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
     * @param format 时间格式
     * @return
     */
    public int getNowTime(String format) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);// 可以方便地修改日期格式
        String hehe = dateFormat.format(now);
        return Integer.parseInt(hehe);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rili_jilu:
                boolean isJinqi = false;
                //判断点击当前时间是否为在预测大姨妈期间
                if (nowTime >= mtmBass2.getBeginTime() && nowTime <= mtmBass2.getEndTime()) {
                    isJinqi = true;
                } else {
                    isJinqi = false;
                }
                //拿到当前开始经期时间
                SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
                String time = sdf.format(new Date(nowTime));
                String time2 = sdf.format(new Date(mtmBass2.getBeginTime()));
                String time3 = sdf.format(new Date(mtmBass2.getEndTime()));
                new JiluDialog(getActivity(), R.style.DialogTheme, isJinqi, "", new JiluDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean is, String confirm) {
                        if (is) {
                            //保存记录数据到数据库
                            MenstruationMt mt = mtDao.getMTMT(nowTime);
                            if (mt != null) {
                                mt.setQuantity(confirm);
                                mtDao.updateMTM(mt);
                            } else {
                                mt = new MenstruationMt();
                                mt.setDate(nowTime);
                                mt.setQuantity(confirm);
                                mtDao.setMTMT(mt);
                            }
                        }
                    }


                }).setTitle(time).show();
                break;
            default:
                break;
        }
    }
}


package com.bishe.myapplication.registeract;

import android.os.Bundle;
import android.text.method.DateTimeKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bishe.myapplication.MenuActivity;
import com.bishe.myapplication.MyBaseActivity;
import com.bishe.myapplication.R;
import com.bishe.myapplication.date.CustomDatePicker;
import com.bishe.myapplication.date.DateFormatUtils;
import com.bishe.myapplication.date.PickerView;
import com.bishe.myapplication.dayimarili.DateChange;
import com.bishe.myapplication.dayimarili.MenstruationCycle;
import com.bishe.myapplication.dayimarili.MenstruationModel;
import com.bishe.myapplication.dayimarili.db.MenstruationDao;
import com.bishe.myapplication.utils.MySharedPreferences;

public class RiqiActivity extends MyBaseActivity implements View.OnClickListener {

    private ImageButton mImgBack;
    /**
     * 3/3日期
     */
    private TextView mTvTitles;
    private PickerView mDpvYear;
    private PickerView mDpvMonth;
    private PickerView mDpvDay;
    private PickerView mDpvHour;
    /**
     * 时
     */
    private TextView mTvHourUnit;
    private PickerView mDpvMinute;
    /**
     * 分
     */
    private TextView mTvMinuteUnit;
    private String time;
    /**
     * 确定
     */
    private Button mBtnTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riqi);
        initView();
        initDatePicker();
        // 日期格式为yyyy-MM-dd 设置当前时间
        long endTimestamp = System.currentTimeMillis();
        mDatePicker.show2(DateFormatUtils.long2Str(endTimestamp, false));
    }

    private CustomDatePicker mDatePicker;


    private void initDatePicker() {
        //设置初始显示时间
        long beginTimestamp = DateFormatUtils.str2Long("2018-01-01", false);
        long endTimestamp = System.currentTimeMillis();
        // 通过时间戳初始化日期，毫秒级别
        mDatePicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                // TODO: 2019/4/14 保存时间
                MySharedPreferences.setRiqiTime(timestamp);
                MySharedPreferences.setRiqiTime2(DateFormatUtils.long2Str(timestamp, false));
                time = DateFormatUtils.long2Str(timestamp, false);
                Log.i("stw", "onTimeSelected: " + DateFormatUtils.long2Str(timestamp, false));
            }
        }, beginTimestamp, endTimestamp, mDpvYear, mDpvMonth, mDpvDay, mDpvHour, mDpvMinute, mTvHourUnit, mTvMinuteUnit);

        // 不显示时和分
        mDatePicker.setCanShowPreciseTime2(false);
        // 不允许循环滚动
        mDatePicker.setScrollLoop2(false);
        // 不允许滚动动画
        mDatePicker.setCanShowAnim2(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatePicker.onDestroy();
    }

    MenstruationDao mtDao;
    private void initView() {
        mImgBack = (ImageButton) findViewById(R.id.img_back);
        mTvTitles = (TextView) findViewById(R.id.tv_titles);
        mDpvYear = (PickerView) findViewById(R.id.dpv_year);
        mDpvMonth = (PickerView) findViewById(R.id.dpv_month);
        mDpvDay = (PickerView) findViewById(R.id.dpv_day);
        mDpvHour = (PickerView) findViewById(R.id.dpv_hour);
        mTvHourUnit = (TextView) findViewById(R.id.tv_hour_unit);
        mDpvMinute = (PickerView) findViewById(R.id.dpv_minute);
        mTvMinuteUnit = (TextView) findViewById(R.id.tv_minute_unit);
        mBtnTrue = (Button) findViewById(R.id.btn_true);
        mBtnTrue.setOnClickListener(this);
        mImgBack.setOnClickListener(this);
        mtDao = new MenstruationDao(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_true:
                mDatePicker.getSelectTime();
                if (time.equals("")) {
                    showToast(this,"请选择日期！");
                }else {
                    MenstruationCycle mc = new MenstruationCycle();
                    mc.setNumber(MySharedPreferences.getJingqiTime());
                    mc.setCycle(MySharedPreferences.getZhouqiTime());
                    mtDao.setMTCycle(mc);
                    MenstruationModel mtm = new MenstruationModel();
                    mtm.setBeginTime(DateChange.dateTimeStamp(MySharedPreferences.getRiqiTime2(), "yyyy-MM-dd"));
                    mtm.setEndTime(DateChange.dateTimeStamp(MySharedPreferences.getRiqiTime2(), "yyyy-MM-dd") + 86400000L* (MySharedPreferences.getJingqiTime() - 1));
                    mtm.setCycle(MySharedPreferences.getZhouqiTime());
                    mtm.setDurationDay(MySharedPreferences.getJingqiTime());
                    mtm.setDate(DateChange.dateTimeStamp(MySharedPreferences.getRiqiTime2(), "yyyy-MM-dd"));
                    mtDao.setMTModel(mtm);

                    showToast(this, "注册成功！");
                    MySharedPreferences.setIslogin(true);
                    intentClass2(MenuActivity.class);
                    finish();
                }
                break;
            case R.id.img_back:
                finish();
                break;

        }
    }
}

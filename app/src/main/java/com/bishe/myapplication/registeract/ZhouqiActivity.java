package com.bishe.myapplication.registeract;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bishe.myapplication.MyBaseActivity;
import com.bishe.myapplication.Myadapter;
import com.bishe.myapplication.R;
import com.bishe.myapplication.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZhouqiActivity extends MyBaseActivity implements View.OnClickListener {

    private ListView mZhouqiList;
    private ImageButton mImgBack;
    /**
     * 1/3周期
     */
    private TextView mTvTitle;
    /**
     * 确定
     */
    private Button mBtnTrue;
    private int zhouqi = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhouqi);
        initView();
        initDev();
    }

    private void initDev() {
        final List<String> stringList = new ArrayList<>();
        for (int i = 15; i < 91; i++) {
            stringList.add(i + "天");
        }
        final Myadapter myadapter = new Myadapter(stringList, this);
        mZhouqiList.setAdapter(myadapter);
        mZhouqiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myadapter.setSelect(position);
                myadapter.notifyDataSetChanged();
                int zhouqiTiem = Integer.parseInt(getNumbers(stringList.get(position)));
                Log.i("stw", "onItemClick: 周期==" + zhouqiTiem);
                zhouqi = zhouqiTiem;
                MySharedPreferences.setZhouqiTime(zhouqiTiem);
            }
        });
    }

    private void initView() {
        mZhouqiList = (ListView) findViewById(R.id.zhouqi_list);
        mImgBack = (ImageButton) findViewById(R.id.img_back);
        mImgBack.setOnClickListener(this);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mBtnTrue = (Button) findViewById(R.id.btn_true);
        mBtnTrue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_true:
                if (zhouqi==0) {
                    showToast(this,"请选择周期天数！");
                }else {
                    intentClass(JinqiActivity.class);
                }
                break;
        }
    }
}

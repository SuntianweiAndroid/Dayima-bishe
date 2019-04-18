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

public class JinqiActivity extends MyBaseActivity implements View.OnClickListener {

    private ImageButton mImgBack;
    /**
     * 2/3经期
     */
    private TextView mTvTitle;
    private ListView mjingqiList;
    /**
     * 确定
     */
    private Button mBtnTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jinqi);
        initView();
        initDev();
    }

    private void initDev() {
        final List<String> stringList = new ArrayList<>();
        for (int i = 2; i <16; i++) {
            stringList.add(i + "天");
        }
        final Myadapter myadapter = new Myadapter(stringList, this);
        mjingqiList.setAdapter(myadapter);
        mjingqiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myadapter.setSelect(position);
                myadapter.notifyDataSetChanged();
                int zhouqiTiem = Integer.parseInt(getNumbers(stringList.get(position)));
                Log.i("stw", "onItemClick: 经期==" + zhouqiTiem);
                MySharedPreferences.setZhouqiTime(zhouqiTiem);
            }
        });
    }

    private void initView() {
        mImgBack = (ImageButton) findViewById(R.id.img_back);
        mImgBack.setOnClickListener(this);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mjingqiList = (ListView) findViewById(R.id.jingqi_list);
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
                intentClass(RiqiActivity.class);

                break;
        }
    }
}

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

/**
 * 选择经期天数界面
 */
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
    private int jingqi=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jinqi);
        initView();
        initDev();
    }

    private void initDev() {
        //初始化经期天数2--16天
        final List<String> stringList = new ArrayList<>();
        for (int i = 2; i < 16; i++) {
            stringList.add(i + "天");
        }
        //实例化自定义adapter（适配器）
        final Myadapter myadapter = new Myadapter(stringList, this);
        //适配经期listview数据
        mjingqiList.setAdapter(myadapter);
        //设置经期listview item点击事件
        mjingqiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myadapter.setSelect(position);
                myadapter.notifyDataSetChanged();
                int jingqiTiem = Integer.parseInt(getNumbers(stringList.get(position)));
                Log.i("stw", "onItemClick: 经期==" + jingqiTiem);
                jingqi = jingqiTiem;
                //保存经期时间
                MySharedPreferences.setJingqiTime(jingqiTiem);
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
                //判断是否选择经期
                if (jingqi == 0) {
                    showToast(this, "请选择经期天数");
                } else {
                    //跳转日期选择界面
                    intentClass(RiqiActivity.class);
                }
                break;
        }
    }
}

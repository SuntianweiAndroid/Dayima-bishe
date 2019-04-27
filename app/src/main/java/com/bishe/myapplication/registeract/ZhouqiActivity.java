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
/**
 * 选择周期天数界面
 */
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

    /**
     * 初始化数据
     */
    private void initDev() {
        //周期选择范围15--91天
        final List<String> stringList = new ArrayList<>();
        for (int i = 15; i < 91; i++) {
            stringList.add(i + "天");
        }
        //实例化自定义adapter（适配器）
        final Myadapter myadapter = new Myadapter(stringList, this);
        //适配周期listview数据
        mZhouqiList.setAdapter(myadapter);
        //设置周期listview item点击事件
        mZhouqiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myadapter.setSelect(position);
                //刷新listview数据
                myadapter.notifyDataSetChanged();
                int zhouqiTiem = Integer.parseInt(getNumbers(stringList.get(position)));
                Log.i("stw", "onItemClick: 周期==" + zhouqiTiem);
                zhouqi = zhouqiTiem;
                //保存周期数据
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
                //判断是否选择了周期天数否则提示
                if (zhouqi == 0) {
                    showToast(this, "请选择周期天数！");
                } else {
                    //跳转经期选择界面
                    intentClass(JinqiActivity.class);
                }
                break;
        }
    }
}

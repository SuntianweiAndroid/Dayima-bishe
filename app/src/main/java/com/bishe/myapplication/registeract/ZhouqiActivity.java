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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhouqi);
        initView();
        initDev();
    }

    private void initDev() {
        List<Map<String, Object>> list_map = new ArrayList<Map<String, Object>>(); //定义一个适配器对象
        for (int i = 15; i < 91; i++) {
            Map<String, Object> items = new HashMap<String, Object>(); //创建一个键值对的Map集合，用来存放名字和头像
            items.put("day", i + "天");  //放入头像， 根据下标获取数组
            list_map.add(items);   //把这个存放好数据的Map集合放入到list中，这就完成类数据源的准备工作
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this,/*传入一个上下文作为参数*/
                list_map,         /*传入相对应的数据源，这个数据源不仅仅是数据而且还是和界面相耦合的混合体。*/
                R.layout.layout_register_item, /*设置具体某个items的布局，需要是新的布局，而不是ListView控件的布局*/
                new String[]{"day"}, /*传入上面定义的键值对的键名称,会自动根据传入的键找到对应的值*/
                new int[]{R.id.tv_item});/*传入items布局文件中需要指定传入的控件，这里直接上id即可*/
        mZhouqiList.setAdapter(simpleAdapter);

        mZhouqiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, String> infoMap = (Map<String, String>) parent.getItemAtPosition(position);
                int zhouqiTiem = Integer.parseInt(getNumbers(infoMap.get("day")));
                Log.i("stw", "onItemClick: 周期==" + zhouqiTiem);
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
                intentClass(JinqiActivity.class);
                break;
        }
    }
}

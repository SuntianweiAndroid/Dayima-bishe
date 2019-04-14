package com.bishe.myapplication.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bishe.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommomDialog2 extends Dialog implements View.OnClickListener {
    private ListView listView;
    private ImageView imageViewError;
    private TextView titleTxt;
    private TextView submitTxt;
    private TextView cancelTxt;

    private Context mContext;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;
    private String type;
    private int selectTime;

    public CommomDialog2(Context context) {
        super(context);
        this.mContext = context;
    }

    public CommomDialog2(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    public CommomDialog2(Context context, int themeResId, OnCloseListener listener, String type) {
        super(context, themeResId);
        this.mContext = context;
        this.type = type;
        this.listener = listener;
    }

    protected CommomDialog2(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public CommomDialog2 setTitle(String title) {
        this.title = title;
        return this;
    }

    public CommomDialog2 setPositiveButton(String name) {
        this.positiveName = name;
        return this;
    }

    public CommomDialog2 setNegativeButton(String name) {
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setting_dialog2);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
        imageViewError = findViewById(R.id.img_error);
        imageViewError.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.lsit_show);
        titleTxt = (TextView) findViewById(R.id.title);
        submitTxt = (TextView) findViewById(R.id.submit);
        submitTxt.setOnClickListener(this);
        cancelTxt = (TextView) findViewById(R.id.cancel);
        cancelTxt.setOnClickListener(this);
        List<Map<String, Object>> list_map = new ArrayList<Map<String, Object>>(); //定义一个适配器对象
        if (type.equals("jingqi")) {
            for (int i = 2; i < 16; i++) {
                Map<String, Object> items = new HashMap<String, Object>(); //创建一个键值对的Map集合，用来存放名字和头像
                items.put("day", i + "天");  //放入头像， 根据下标获取数组
                list_map.add(items);   //把这个存放好数据的Map集合放入到list中，这就完成类数据源的准备工作
            }
        } else if (type.equals("zhouqi")) {
            for (int i = 15; i < 91; i++) {
                Map<String, Object> items = new HashMap<String, Object>(); //创建一个键值对的Map集合，用来存放名字和头像
                items.put("day", i + "天");  //放入头像， 根据下标获取数组
                list_map.add(items);   //把这个存放好数据的Map集合放入到list中，这就完成类数据源的准备工作
            }
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                mContext,/*传入一个上下文作为参数*/
                list_map,/*传入相对应的数据源，这个数据源不仅仅是数据而且还是和界面相耦合的混合体。*/
                R.layout.layout_register_item, /*设置具体某个items的布局，需要是新的布局，而不是ListView控件的布局*/
                new String[]{"day"}, /*传入上面定义的键值对的键名称,会自动根据传入的键找到对应的值*/
                new int[]{R.id.tv_item});/*传入items布局文件中需要指定传入的控件，这里直接上id即可*/
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, String> infoMap = (Map<String, String>) parent.getItemAtPosition(position);
                selectTime = Integer.parseInt(getNumbers(infoMap.get("day")));
                Log.i("stw", "onItemClick:diaolog== " + selectTime);
            }
        });
        if (!TextUtils.isEmpty(positiveName)) {
            submitTxt.setText(positiveName);
        }

        if (!TextUtils.isEmpty(negativeName)) {
            cancelTxt.setText(negativeName);
        }

        if (!TextUtils.isEmpty(title)) {
            titleTxt.setText(title);
        }

    }

    private String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                if (listener != null) {
                    listener.onClick(this, false, selectTime);
                }
                this.dismiss();
                break;
            case R.id.submit:
                if (listener != null) {
                    listener.onClick(this, true, selectTime);
                }
                this.dismiss();
                break;
            case R.id.img_error:

                break;
            default:
                break;
        }
    }

    public interface OnCloseListener {
        void onClick(Dialog dialog, boolean confirm, int selectTime);
    }
}
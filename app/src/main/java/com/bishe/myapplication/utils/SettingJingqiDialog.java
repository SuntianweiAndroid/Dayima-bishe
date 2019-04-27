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
import android.widget.TextView;

import com.bishe.myapplication.Myadapter;
import com.bishe.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义设置界面 经期与周期 对话框
 */
public class SettingJingqiDialog extends Dialog implements View.OnClickListener {
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

    public SettingJingqiDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public SettingJingqiDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    public SettingJingqiDialog(Context context, int themeResId, OnCloseListener listener, String type) {
        super(context, themeResId);
        this.mContext = context;
        this.type = type;
        this.listener = listener;
    }

    protected SettingJingqiDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public SettingJingqiDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public SettingJingqiDialog setPositiveButton(String name) {
        this.positiveName = name;
        return this;
    }

    public SettingJingqiDialog setNegativeButton(String name) {
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
        final List<String> stringList = new ArrayList<>();
        if (type.equals("jingqi")) {
            stringList.clear();
            for (int i = 2; i < 16; i++) {
                stringList.add(i + "天");
            }
        } else if (type.equals("zhouqi")) {
            stringList.clear();
            for (int i = 15; i < 91; i++) {
                stringList.add(i + "天");
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, String> infoMap = (Map<String, String>) parent.getItemAtPosition(position);
                selectTime = Integer.parseInt(getNumbers(infoMap.get("day")));
                Log.i("stw", "onItemClick:diaolog== " + selectTime);
            }
        });

        final Myadapter myadapter = new Myadapter(stringList, mContext);
        listView.setAdapter(myadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myadapter.setSelect(position);
                myadapter.notifyDataSetChanged();
                selectTime = Integer.parseInt(getNumbers(stringList.get(position)));
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
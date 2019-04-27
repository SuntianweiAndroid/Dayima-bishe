package com.bishe.myapplication.dayimarili;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bishe.myapplication.R;


/**
 * 大姨妈日历控件卡片类
 */
public class DateCard2 extends FrameLayout {
    //颜色
    private String safety = "#101110"; //3
    private String risk = "#101110"; //4

    private TextView tvNumber;
    private LinearLayout ll, layout;

    public DateCard2(Context context) {
        super(context);
        initView();
    }

    String TAG = "stw";

    private void initView() {
        Log.i(TAG, "initView: ");
        layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundResource(R.drawable.date_view_bg_witle);
        LayoutParams lps = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lps.setMargins(5, 5, 0, 0);
        layout.setLayoutParams(lps);
        layout.setPadding(1, 1, 1, 1);
        addView(layout);

        ll = new LinearLayout(getContext());
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setBackgroundResource(R.drawable.menstruation_date_view_item_white);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(lp);
        layout.addView(ll, lp);

        //显示日期
        tvNumber = new TextView(getContext());
        tvNumber.setTextSize(14);
        tvNumber.setTextColor(Color.parseColor("#BBBBBB"));
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        ll.addView(tvNumber, params);

        LinearLayout l = new LinearLayout(getContext());
        l.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams lparams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        l.setGravity(Gravity.CENTER_VERTICAL);
        l.setLayoutParams(lparams);
        ll.addView(l);
    }

    /**
     * 初始化数据
     *
     * @param dateCard
     */
    public void initData(DateCardModel dateCard) {
        tvNumber.setText(dateCard.date + "");
        setToMonth(dateCard.istoMonth);
        setType(dateCard.type);
        setOnClick(dateCard.isClick);
    }

    /**
     * 是否显示边框（点击显示的边框）
     *
     * @param isClick true显示
     */
    public void setOnClick(boolean isClick) {
        if (isClick) {
            layout.setBackgroundResource(R.drawable.date_view_bg);
        } else {
            layout.setBackgroundResource(R.drawable.date_view_bg_witle);
        }
    }

    /**
     * 是否显示内容（在本月中显示的上下月时间不用显示内容）
     *
     * @param istoMonth
     */
    public void setToMonth(boolean istoMonth) {
        if (!istoMonth) {
            tvNumber.setVisibility(View.GONE);
        } else {
            tvNumber.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 设置数字字体颜色与背景颜色
     *
     * @param type
     */
    public void setType(int type) {
        switch (type) {
            case 0:
                tvNumber.setTextColor(Color.parseColor("#101110"));
                ll.setBackgroundResource(R.drawable.menstruation_date_view_item_white);
                break;
            case 1:
                tvNumber.setTextColor(Color.WHITE);
                ll.setBackgroundResource(R.drawable.menstruation_date_view_item_red);
                break;
            case 2:
                tvNumber.setTextColor(Color.WHITE);
                ll.setBackgroundResource(R.drawable.menstruation_date_view_item_pink);
                break;
            case 3:
                tvNumber.setTextColor(Color.parseColor(safety));
                ll.setBackgroundResource(R.drawable.menstruation_date_view_item_white);
                break;
            case 4:
                tvNumber.setTextColor(Color.parseColor(risk));
                ll.setBackgroundResource(R.drawable.menstruation_date_view_item_white);
                break;
        }
    }


}

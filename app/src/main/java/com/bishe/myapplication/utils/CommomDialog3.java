package com.bishe.myapplication.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bishe.myapplication.R;


public class CommomDialog3 extends Dialog implements View.OnClickListener {
    private TextView contentTxt;
    private TextView titleTxt;
    private TextView submitTxt;
    private TextView cancelTxt;
    /**
     * ddd
     */
    private TextView mTvTop1;
    /**
     * ddd
     */
    private TextView mTvTop2;
    /**
     * ddd
     */
    private TextView mTvTop3;
    /**
     * ddd
     */
    private TextView mTvTop4;
    /**
     * ddd
     */
    private TextView mTvTop5;
    /**
     * ddd
     */
    private TextView mTvTop6;
    /**
     * ddd
     */
    private TextView mTvTop7;
    /**
     * ddd
     */
    private TextView mTvTop8;
    /**
     * ddd
     */
    private Button mTvDwon1;
    /**
     * ddd
     */
    private Button mTvDwon2;
    /**
     * ddd
     */
    private Button mTvDwon3;
    /**
     * ddd
     */
    private Button mTvDwon4;
    /**
     * ddd
     */
    private Button mTvDwon5;
    /**
     * ddd
     */
    private Button mTvDwon6;
    /**
     * ddd
     */
    private Button mTvDwon7;
    /**
     * ddd
     */
    private Button mTvDwon8;
    private LinearLayout mLayDwon;
    /**
     * 经期
     */
    private TextView mTvTitleJingqi;
    /**
     * 爱爱
     */
    private TextView mTvTitleAiai;
    /**
     * 心情
     */
    private TextView mTvTitleXinqing;
    /**
     * 症状
     */
    private TextView mTvTitleZhengzhuang;

    private Context mContext;
    private String content;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;

    private FrameLayout frameLayout;

    public CommomDialog3(Context context) {
        super(context);
        this.mContext = context;
    }

    public CommomDialog3(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    public CommomDialog3(Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }

    protected CommomDialog3(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public CommomDialog3 setTitle(String title) {
        this.title = title;
        return this;
    }

    public CommomDialog3 setPositiveButton(String name) {
        this.positiveName = name;
        return this;
    }

    public CommomDialog3 setNegativeButton(String name) {
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rili_dialoag);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
        contentTxt = (TextView) findViewById(R.id.content);
        titleTxt = (TextView) findViewById(R.id.title);
        submitTxt = (TextView) findViewById(R.id.submit);
        submitTxt.setOnClickListener(this);
        cancelTxt = (TextView) findViewById(R.id.cancel);
        cancelTxt.setOnClickListener(this);

        contentTxt.setText(content);
        if (!TextUtils.isEmpty(positiveName)) {
            submitTxt.setText(positiveName);
        }

        if (!TextUtils.isEmpty(negativeName)) {
            cancelTxt.setText(negativeName);
        }

        if (!TextUtils.isEmpty(title)) {
            titleTxt.setText(title);
        }
        mTvTop1 = (TextView) findViewById(R.id.tv_top1);
        mTvTop1.setOnClickListener(this);
        mTvTop2 = (TextView) findViewById(R.id.tv_top2);
        mTvTop2.setOnClickListener(this);
        mTvTop3 = (TextView) findViewById(R.id.tv_top3);
        mTvTop3.setOnClickListener(this);
        mTvTop4 = (TextView) findViewById(R.id.tv_top4);
        mTvTop4.setOnClickListener(this);
        mTvTop5 = (TextView) findViewById(R.id.tv_top5);
        mTvTop5.setOnClickListener(this);
        mTvTop6 = (TextView) findViewById(R.id.tv_top6);
        mTvTop6.setOnClickListener(this);
        mTvTop7 = (TextView) findViewById(R.id.tv_top7);
        mTvTop7.setOnClickListener(this);
        mTvTop8 = (TextView) findViewById(R.id.tv_top8);
        mTvTop8.setOnClickListener(this);
        mTvDwon1 = (Button) findViewById(R.id.tv_dwon1);
        mTvDwon1.setOnClickListener(this);
        mTvDwon2 = (Button) findViewById(R.id.tv_dwon2);
        mTvDwon2.setOnClickListener(this);
        mTvDwon3 = (Button) findViewById(R.id.tv_dwon3);
        mTvDwon3.setOnClickListener(this);
        mTvDwon4 = (Button) findViewById(R.id.tv_dwon4);
        mTvDwon4.setOnClickListener(this);
        mTvDwon5 = (Button) findViewById(R.id.tv_dwon5);
        mTvDwon5.setOnClickListener(this);
        mTvDwon6 = (Button) findViewById(R.id.tv_dwon6);
        mTvDwon6.setOnClickListener(this);
        mTvDwon7 = (Button) findViewById(R.id.tv_dwon7);
        mTvDwon7.setOnClickListener(this);
        mTvDwon8 = (Button) findViewById(R.id.tv_dwon8);
        mTvDwon8.setOnClickListener(this);
        mLayDwon = (LinearLayout) findViewById(R.id.lay_dwon);
        mLayDwon.setOnClickListener(this);

        mTvTitleJingqi = (TextView) findViewById(R.id.tv_title_jingqi);
        mTvTitleJingqi.setOnClickListener(this);
        mTvTitleAiai = (TextView) findViewById(R.id.tv_title_aiai);
        mTvTitleAiai.setOnClickListener(this);
        mTvTitleXinqing = (TextView) findViewById(R.id.tv_title_xinqing);
        mTvTitleXinqing.setOnClickListener(this);
        mTvTitleZhengzhuang = (TextView) findViewById(R.id.tv_title_zhengzhuang);
        mTvTitleZhengzhuang.setOnClickListener(this);

        mTvDwon1.setText("量多");
        mTvDwon2.setText("中等");
        mTvDwon3.setText("较少");
        mTvDwon4.setText("很少");
        mTvDwon5.setText("无感");
        mTvDwon6.setText("微痛");
        mTvDwon7.setText("很痛");
        mTvDwon8.setText("很少");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                if (listener != null) {
                    listener.onClick(this, false);
                }
                this.dismiss();
                break;
            case R.id.submit:
                if (listener != null) {
                    listener.onClick(this, true);
                }
                this.dismiss();
                break;
            default:
                break;
            case R.id.tv_top1:
                mTvDwon1.setEnabled(true);
                mTvDwon2.setEnabled(true);
                mTvDwon3.setEnabled(true);
                mTvDwon4.setEnabled(true);

                switch (select) {
                    case 1:
                        mTvDwon1.setVisibility(View.VISIBLE);
                        mTvTop1.setVisibility(View.GONE);
                        mTvDwon1.setText(mTvTop1.getText().toString());
                        break;
                    case 2:
                        mTvDwon2.setVisibility(View.VISIBLE);
                        mTvTop1.setVisibility(View.GONE);
                        mTvDwon2.setText(mTvTop1.getText().toString());
                        break;
                    case 3:
                        mTvDwon3.setVisibility(View.VISIBLE);
                        mTvTop1.setVisibility(View.GONE);
                        mTvDwon3.setText(mTvTop1.getText().toString());
                        break;
                    case 4:
                        mTvDwon4.setVisibility(View.VISIBLE);
                        mTvTop1.setVisibility(View.GONE);
                        mTvDwon4.setText(mTvTop1.getText().toString());
                        break;
                }

                break;
            case R.id.tv_top2:
                mTvDwon5.setEnabled(true);
                mTvDwon6.setEnabled(true);
                mTvDwon7.setEnabled(true);
                mTvDwon8.setEnabled(true);
                switch (select2) {
                    case 5:
                        mTvDwon5.setVisibility(View.VISIBLE);
                        mTvTop2.setVisibility(View.GONE);
                        mTvDwon5.setText(mTvTop2.getText().toString());
                        break;
                    case 6:
                        mTvDwon6.setVisibility(View.VISIBLE);
                        mTvTop2.setVisibility(View.GONE);
                        mTvDwon6.setText(mTvTop2.getText().toString());
                        break;
                    case 7:
                        mTvDwon7.setVisibility(View.VISIBLE);
                        mTvTop2.setVisibility(View.GONE);
                        mTvDwon7.setText(mTvTop2.getText().toString());
                        break;
                    case 8:
                        mTvDwon8.setVisibility(View.VISIBLE);
                        mTvTop2.setVisibility(View.GONE);
                        mTvDwon8.setText(mTvTop2.getText().toString());
                        break;
                }
                break;
            case R.id.tv_top3:
                mTvDwon3.setVisibility(View.VISIBLE);
                mTvTop3.setVisibility(View.GONE);
                mTvDwon3.setText(mTvTop3.getText().toString());
                break;
            case R.id.tv_top4:
                mTvDwon4.setVisibility(View.VISIBLE);
                mTvTop4.setVisibility(View.GONE);
                mTvDwon4.setText(mTvTop4.getText().toString());
                break;
            case R.id.tv_top5:
                mTvDwon5.setVisibility(View.VISIBLE);
                mTvTop5.setVisibility(View.GONE);
                mTvDwon5.setText(mTvTop5.getText().toString());
                break;
            case R.id.tv_top6:
                mTvDwon6.setVisibility(View.VISIBLE);
                mTvTop6.setVisibility(View.GONE);
                mTvDwon4.setText(mTvTop6.getText().toString());
                break;
            case R.id.tv_top7:
                mTvDwon7.setVisibility(View.VISIBLE);
                mTvTop7.setVisibility(View.GONE);
                mTvDwon7.setText(mTvTop7.getText().toString());
                break;
            case R.id.tv_top8:
                mTvDwon8.setVisibility(View.VISIBLE);
                mTvTop8.setVisibility(View.GONE);
                mTvDwon8.setText(mTvTop8.getText().toString());
                break;
            case R.id.tv_dwon1:
                select = 1;
                mTvDwon2.setEnabled(false);
                mTvDwon3.setEnabled(false);
                mTvDwon4.setEnabled(false);
                mTvTop1.setText(mTvDwon1.getText().toString());
                mTvDwon1.setVisibility(View.INVISIBLE);
                mTvTop1.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_dwon2:
                mTvDwon1.setEnabled(false);
                mTvDwon3.setEnabled(false);
                mTvDwon4.setEnabled(false);
                select = 2;
                mTvTop1.setText(mTvDwon2.getText().toString());
                mTvDwon2.setVisibility(View.INVISIBLE);
                mTvTop1.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_dwon3:
                select = 3;
                mTvDwon1.setEnabled(false);
                mTvDwon2.setEnabled(false);
                mTvDwon4.setEnabled(false);
                mTvTop1.setText(mTvDwon3.getText().toString());
                mTvDwon3.setVisibility(View.INVISIBLE);
                mTvTop1.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_dwon4:
                select = 4;
                mTvDwon1.setEnabled(false);
                mTvDwon3.setEnabled(false);
                mTvDwon2.setEnabled(false);
                mTvTop1.setText(mTvDwon4.getText().toString());
                mTvDwon4.setVisibility(View.INVISIBLE);
                mTvTop1.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_dwon5:
                select2 = 5;
                mTvDwon6.setEnabled(false);
                mTvDwon7.setEnabled(false);
                mTvDwon8.setEnabled(false);
                mTvTop2.setText(mTvDwon5.getText().toString());
                mTvDwon5.setVisibility(View.INVISIBLE);
                mTvTop2.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_dwon6:
                select2 = 6;
                mTvDwon5.setEnabled(false);
                mTvDwon7.setEnabled(false);
                mTvDwon8.setEnabled(false);
                mTvTop2.setText(mTvDwon6.getText().toString());
                mTvDwon6.setVisibility(View.INVISIBLE);
                mTvTop2.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_dwon7:
                select2 = 7;
                mTvDwon5.setEnabled(false);
                mTvDwon6.setEnabled(false);
                mTvDwon8.setEnabled(false);
                mTvTop2.setText(mTvDwon7.getText().toString());
                mTvDwon7.setVisibility(View.INVISIBLE);
                mTvTop2.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_dwon8:
                select2 = 8;
                mTvDwon5.setEnabled(false);
                mTvDwon7.setEnabled(false);
                mTvDwon6.setEnabled(false);
                mTvTop2.setText(mTvDwon8.getText().toString());
                mTvDwon8.setVisibility(View.INVISIBLE);
                mTvTop2.setVisibility(View.VISIBLE);
                break;
            case R.id.lay_dwon:
                break;

            case R.id.tv_title_jingqi:
                mTvDwon1.setEnabled(true);
                mTvDwon2.setEnabled(true);
                mTvDwon3.setEnabled(true);
                mTvDwon4.setEnabled(true);
                mTvDwon5.setEnabled(true);
                mTvDwon6.setEnabled(true);
                mTvDwon7.setEnabled(true);
                mTvDwon8.setEnabled(true);
                mLayDwon.setVisibility(View.VISIBLE);
                mTvDwon1.setVisibility(View.VISIBLE);
                mTvDwon2.setVisibility(View.VISIBLE);
                mTvDwon3.setVisibility(View.VISIBLE);
                mTvDwon4.setVisibility(View.VISIBLE);
                mTvDwon5.setVisibility(View.VISIBLE);
                mTvDwon6.setVisibility(View.VISIBLE);
                mTvDwon7.setVisibility(View.VISIBLE);
                mTvDwon8.setVisibility(View.VISIBLE);
                mTvTop1.setVisibility(View.GONE);
                mTvTop2.setVisibility(View.GONE);
                mTvTop3.setVisibility(View.INVISIBLE);
                mTvTop4.setVisibility(View.INVISIBLE);
                mTvTop5.setVisibility(View.INVISIBLE);
                mTvTop6.setVisibility(View.INVISIBLE);
                mTvTop7.setVisibility(View.INVISIBLE);
                mTvTop8.setVisibility(View.INVISIBLE);
                mTvDwon1.setText("量多");
                mTvDwon2.setText("中等");
                mTvDwon3.setText("较少");
                mTvDwon4.setText("很少");
                mTvDwon5.setText("无感");
                mTvDwon6.setText("微痛");
                mTvDwon7.setText("很痛");
                mTvDwon8.setText("很少");
                break;
            case R.id.tv_title_aiai:
                mTvDwon1.setEnabled(true);
                mTvDwon2.setEnabled(true);
                mTvDwon3.setEnabled(true);
                mTvDwon4.setEnabled(true);
                mTvDwon5.setEnabled(true);
                mTvDwon6.setEnabled(true);
                mTvDwon7.setEnabled(true);
                mTvDwon8.setEnabled(true);
                mLayDwon.setVisibility(View.GONE);
                mTvDwon1.setText("无措施");
                mTvDwon2.setText("避孕套");
                mTvDwon3.setText("避孕药");
                mTvDwon4.setText("体外排精");
                mTvDwon1.setVisibility(View.VISIBLE);
                mTvDwon2.setVisibility(View.VISIBLE);
                mTvDwon3.setVisibility(View.VISIBLE);
                mTvDwon4.setVisibility(View.VISIBLE);
                mTvTop1.setVisibility(View.GONE);
                mTvTop2.setVisibility(View.GONE);
                mTvTop3.setVisibility(View.INVISIBLE);
                mTvTop4.setVisibility(View.INVISIBLE);
                mTvTop5.setVisibility(View.INVISIBLE);
                mTvTop6.setVisibility(View.INVISIBLE);
                mTvTop7.setVisibility(View.INVISIBLE);
                mTvTop8.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_title_xinqing:
                mTvDwon1.setEnabled(true);
                mTvDwon2.setEnabled(true);
                mTvDwon3.setEnabled(true);
                mTvDwon4.setEnabled(true);
                mTvDwon5.setEnabled(true);
                mTvDwon6.setEnabled(true);
                mTvDwon7.setEnabled(true);
                mTvDwon8.setEnabled(true);
                mLayDwon.setVisibility(View.VISIBLE);
                mLayDwon.setVisibility(View.VISIBLE);
                mTvDwon1.setVisibility(View.VISIBLE);
                mTvDwon2.setVisibility(View.VISIBLE);
                mTvDwon3.setVisibility(View.VISIBLE);
                mTvDwon4.setVisibility(View.VISIBLE);
                mTvDwon5.setVisibility(View.VISIBLE);
                mTvDwon6.setVisibility(View.VISIBLE);
                mTvDwon7.setVisibility(View.VISIBLE);
                mTvDwon8.setVisibility(View.VISIBLE);
                mTvDwon1.setText("开心");
                mTvDwon2.setText("生气");
                mTvDwon3.setText("难过");
                mTvDwon4.setText("敏感");
                mTvDwon5.setText("疲倦");
                mTvDwon6.setText("平静");
                mTvDwon7.setText("活泼");
                mTvDwon8.setText("紧张");
                mTvTop1.setVisibility(View.INVISIBLE);
                mTvTop2.setVisibility(View.INVISIBLE);
                mTvTop3.setVisibility(View.INVISIBLE);
                mTvTop4.setVisibility(View.INVISIBLE);
                mTvTop5.setVisibility(View.INVISIBLE);
                mTvTop6.setVisibility(View.INVISIBLE);
                mTvTop7.setVisibility(View.INVISIBLE);
                mTvTop8.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_title_zhengzhuang:
                mTvDwon1.setEnabled(true);
                mTvDwon2.setEnabled(true);
                mTvDwon3.setEnabled(true);
                mTvDwon4.setEnabled(true);
                mTvDwon5.setEnabled(true);
                mTvDwon6.setEnabled(true);
                mTvDwon7.setEnabled(true);
                mTvDwon8.setEnabled(true);
                mLayDwon.setVisibility(View.VISIBLE);
                mTvDwon1.setVisibility(View.VISIBLE);
                mTvDwon2.setVisibility(View.VISIBLE);
                mTvDwon3.setVisibility(View.VISIBLE);
                mTvDwon4.setVisibility(View.VISIBLE);
                mTvDwon5.setVisibility(View.VISIBLE);
                mTvDwon6.setVisibility(View.VISIBLE);
                mTvDwon7.setVisibility(View.VISIBLE);
                mTvDwon8.setVisibility(View.VISIBLE);
                mTvDwon1.setText("腰酸");
                mTvDwon2.setText("头疼");
                mTvDwon3.setText("长痘");
                mTvDwon4.setText("粉刺");
                mTvDwon5.setText("乳房胀痛");
                mTvDwon6.setText("失眠");
                mTvDwon7.setText("恶心");
                mTvDwon8.setText("眩晕");
                mTvTop1.setVisibility(View.INVISIBLE);
                mTvTop2.setVisibility(View.INVISIBLE);
                mTvTop3.setVisibility(View.INVISIBLE);
                mTvTop4.setVisibility(View.INVISIBLE);
                mTvTop5.setVisibility(View.INVISIBLE);
                mTvTop6.setVisibility(View.INVISIBLE);
                mTvTop7.setVisibility(View.INVISIBLE);
                mTvTop8.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private int select = 0;
    private int select2 = 0;

    public interface OnCloseListener {
        void onClick(Dialog dialog, boolean confirm);
    }
}
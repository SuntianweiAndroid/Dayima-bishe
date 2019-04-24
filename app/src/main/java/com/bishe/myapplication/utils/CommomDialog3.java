package com.bishe.myapplication.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

    private FrameLayout frameLayout1, frameLayout2;
    private TextView iamgeQuxiao;

    public CommomDialog3(Context context) {
        super(context);
        this.mContext = context;
    }

    public CommomDialog3(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    private boolean isJinqi = false;

    public CommomDialog3(Context context, int themeResId, boolean isJinqi, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
        this.isJinqi = isJinqi;
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
        frameLayout1 = findViewById(R.id.fram_layout1);
        frameLayout2 = findViewById(R.id.fram_layout2);
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
        mTvTop1.setText("");
        mTvTop2.setText("");
        if (isJinqi) {
            Drawable bottom = mContext.getResources().getDrawable(R.mipmap.bg_redline);
            bottom.setBounds(0, 0, bottom.getMinimumWidth(), bottom.getMinimumHeight());
            mTvTitleJingqi.setCompoundDrawables(null, null, null, bottom);
            mTvTitleAiai.setCompoundDrawables(null, null, null, null);
            mTvTitleXinqing.setCompoundDrawables(null, null, null, null);
            mTvTitleZhengzhuang.setCompoundDrawables(null, null, null, null);
            yemian = 1;
            mLayDwon.setVisibility(View.VISIBLE);
            mTvTitleJingqi.setVisibility(View.VISIBLE);
            mTvDwon1.setText("量多");
            mTvDwon2.setText("中等");
            mTvDwon3.setText("较少");
            mTvDwon4.setText("很少");
            mTvDwon5.setText("无感");
            mTvDwon6.setText("微痛");
            mTvDwon7.setText("很痛");
            mTvDwon8.setText("很少");
        } else {
            Drawable bottom = mContext.getResources().getDrawable(R.mipmap.bg_redline);
            bottom.setBounds(0, 0, bottom.getMinimumWidth(), bottom.getMinimumHeight());
            mTvTitleAiai.setCompoundDrawables(null, null, null, bottom);
            mTvTitleJingqi.setCompoundDrawables(null, null, null, null);
            mTvTitleXinqing.setCompoundDrawables(null, null, null, null);
            mTvTitleZhengzhuang.setCompoundDrawables(null, null, null, null);
            yemian = 2;
            mTvTitleJingqi.setVisibility(View.GONE);
            mLayDwon.setVisibility(View.GONE);
            mTvDwon1.setText("无措施");
            mTvDwon2.setText("避孕套");
            mTvDwon3.setText("避孕药");
            mTvDwon4.setText("体外排精");
        }
        iamgeQuxiao = (TextView) findViewById(R.id.iamge_quxiao);
        iamgeQuxiao.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Drawable bottom = mContext.getResources().getDrawable(R.mipmap.bg_redline);
        bottom.setBounds(0, 0, bottom.getMinimumWidth(), bottom.getMinimumHeight());
        switch (v.getId()) {
            case R.id.cancel:
                if (listener != null) {
                    Log.i("stw", "onClick: " + jingqiState + "\n" + aiaiState + "\n" + xinqingState + "\n" + zhengzhuangState);
                    String re = jingqiState + aiaiState + xinqingState + zhengzhuangState;
                    listener.onClick(this, false, re);
                }
                this.dismiss();
                break;
            case R.id.submit:
                if (listener != null) {
                    Log.i("stw", "onClick: " + jingqiState + "\n" + aiaiState + "\n" + xinqingState + "\n" + zhengzhuangState);
                    String re = jingqiState + aiaiState + xinqingState + zhengzhuangState;
                    listener.onClick(this, true, re);
                }
                this.dismiss();
                break;
            case R.id.iamge_quxiao:
                if (listener != null) {
                    Log.i("stw", "onClick: " + jingqiState + "\n" + aiaiState + "\n" + xinqingState + "\n" + zhengzhuangState);
                    String re = jingqiState + aiaiState + xinqingState + zhengzhuangState;
                    listener.onClick(this, false, re);
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
                        frameLayout1.setVisibility(View.GONE);
                        mTvDwon1.setText(mTvTop1.getText().toString());
                        mTvTop1.setText("");
                        break;
                    case 2:
                        mTvDwon2.setVisibility(View.VISIBLE);
                        frameLayout1.setVisibility(View.GONE);
                        mTvDwon2.setText(mTvTop1.getText().toString());
                        mTvTop1.setText("");
                        break;
                    case 3:
                        mTvDwon3.setVisibility(View.VISIBLE);
                        frameLayout1.setVisibility(View.GONE);
                        mTvDwon3.setText(mTvTop1.getText().toString());
                        mTvTop1.setText("");
                        break;
                    case 4:
                        mTvDwon4.setVisibility(View.VISIBLE);
                        frameLayout1.setVisibility(View.GONE);
                        mTvDwon4.setText(mTvTop1.getText().toString());
                        mTvTop1.setText("");
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
                        frameLayout2.setVisibility(View.GONE);
                        mTvDwon5.setText(mTvTop2.getText().toString());
                        mTvTop2.setText("");
                        break;
                    case 6:
                        mTvDwon6.setVisibility(View.VISIBLE);
                        frameLayout2.setVisibility(View.GONE);
                        mTvDwon6.setText(mTvTop2.getText().toString());
                        mTvTop2.setText("");
                        break;
                    case 7:
                        mTvDwon7.setVisibility(View.VISIBLE);
                        mTvTop2.setVisibility(View.GONE);
                        mTvDwon7.setText(mTvTop2.getText().toString());
                        mTvTop2.setText("");
                        break;
                    case 8:
                        mTvDwon8.setVisibility(View.VISIBLE);
                        frameLayout2.setVisibility(View.GONE);
                        mTvDwon8.setText(mTvTop2.getText().toString());
                        mTvTop2.setText("");
                        break;
                }
                break;

            case R.id.tv_dwon1:
                select = 1;
                mTvDwon2.setEnabled(false);
                mTvDwon3.setEnabled(false);
                mTvDwon4.setEnabled(false);
                mTvTop1.setText(mTvDwon1.getText().toString());
                mTvDwon1.setVisibility(View.INVISIBLE);
                frameLayout1.setVisibility(View.VISIBLE);
                switch (yemian) {
                    case 1:
                        jingqiState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 2:
                        aiaiState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 3:
                        xinqingState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 4:
                        zhengzhuangState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    default:
                        break;

                }
                break;
            case R.id.tv_dwon2:
                mTvDwon1.setEnabled(false);
                mTvDwon3.setEnabled(false);
                mTvDwon4.setEnabled(false);
                select = 2;
                mTvTop1.setText(mTvDwon2.getText().toString());
                mTvDwon2.setVisibility(View.INVISIBLE);
                frameLayout1.setVisibility(View.VISIBLE);
                switch (yemian) {
                    case 1:
                        jingqiState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 2:
                        aiaiState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 3:
                        xinqingState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 4:
                        zhengzhuangState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    default:
                        break;

                }
                break;
            case R.id.tv_dwon3:
                select = 3;
                mTvDwon1.setEnabled(false);
                mTvDwon2.setEnabled(false);
                mTvDwon4.setEnabled(false);
                mTvTop1.setText(mTvDwon3.getText().toString());
                mTvDwon3.setVisibility(View.INVISIBLE);
                frameLayout1.setVisibility(View.VISIBLE);
                switch (yemian) {
                    case 1:
                        jingqiState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 2:
                        aiaiState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 3:
                        xinqingState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 4:
                        zhengzhuangState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    default:
                        break;

                }
                break;
            case R.id.tv_dwon4:
                select = 4;
                mTvDwon1.setEnabled(false);
                mTvDwon3.setEnabled(false);
                mTvDwon2.setEnabled(false);
                mTvTop1.setText(mTvDwon4.getText().toString());
                mTvDwon4.setVisibility(View.INVISIBLE);
                frameLayout1.setVisibility(View.VISIBLE);
                switch (yemian) {
                    case 1:
                        jingqiState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 2:
                        aiaiState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 3:
                        xinqingState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 4:
                        zhengzhuangState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    default:
                        break;

                }
                break;
            case R.id.tv_dwon5:
                select2 = 5;
                mTvDwon6.setEnabled(false);
                mTvDwon7.setEnabled(false);
                mTvDwon8.setEnabled(false);
                mTvTop2.setText(mTvDwon5.getText().toString());
                mTvDwon5.setVisibility(View.INVISIBLE);
                frameLayout2.setVisibility(View.VISIBLE);
                switch (yemian) {
                    case 1:
                        jingqiState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 2:
                        aiaiState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 3:
                        xinqingState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 4:
                        zhengzhuangState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    default:
                        break;

                }
                break;
            case R.id.tv_dwon6:
                select2 = 6;
                mTvDwon5.setEnabled(false);
                mTvDwon7.setEnabled(false);
                mTvDwon8.setEnabled(false);
                mTvTop2.setText(mTvDwon6.getText().toString());
                mTvDwon6.setVisibility(View.INVISIBLE);
                frameLayout2.setVisibility(View.VISIBLE);
                switch (yemian) {
                    case 1:
                        jingqiState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 2:
                        aiaiState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 3:
                        xinqingState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 4:
                        zhengzhuangState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    default:
                        break;

                }
                break;
            case R.id.tv_dwon7:
                select2 = 7;
                mTvDwon5.setEnabled(false);
                mTvDwon6.setEnabled(false);
                mTvDwon8.setEnabled(false);
                mTvTop2.setText(mTvDwon7.getText().toString());
                mTvDwon7.setVisibility(View.INVISIBLE);
                frameLayout2.setVisibility(View.VISIBLE);
                switch (yemian) {
                    case 1:
                        jingqiState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 2:
                        aiaiState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 3:
                        xinqingState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 4:
                        zhengzhuangState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    default:
                        break;

                }
                break;
            case R.id.tv_dwon8:
                select2 = 8;
                mTvDwon5.setEnabled(false);
                mTvDwon7.setEnabled(false);
                mTvDwon6.setEnabled(false);
                mTvTop2.setText(mTvDwon8.getText().toString());
                mTvDwon8.setVisibility(View.INVISIBLE);
                frameLayout2.setVisibility(View.VISIBLE);
                switch (yemian) {
                    case 1:
                        jingqiState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 2:
                        aiaiState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 3:
                        xinqingState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    case 4:
                        zhengzhuangState = mTvTop1.getText().toString() + "," + mTvTop2.getText().toString()+",";
                        break;
                    default:
                        break;

                }
                break;
            case R.id.lay_dwon:
                break;

            case R.id.tv_title_jingqi:
                mTvTitleAiai.setCompoundDrawables(null, null, null, null);
                mTvTitleJingqi.setCompoundDrawables(null, null, null, bottom);
                mTvTitleXinqing.setCompoundDrawables(null, null, null, null);
                mTvTitleZhengzhuang.setCompoundDrawables(null, null, null, null);

                yemian = 1;
                mTvTop1.setText("");
                mTvTop2.setText("");
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
                frameLayout1.setVisibility(View.GONE);
                frameLayout2.setVisibility(View.GONE);

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
                mTvTitleAiai.setCompoundDrawables(null, null, null, bottom);
                mTvTitleJingqi.setCompoundDrawables(null, null, null, null);
                mTvTitleXinqing.setCompoundDrawables(null, null, null, null);
                mTvTitleZhengzhuang.setCompoundDrawables(null, null, null, null);
                yemian = 2;
                mTvTop1.setText("");
                mTvTop2.setText("");
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
                frameLayout1.setVisibility(View.GONE);
                frameLayout2.setVisibility(View.GONE);
                break;
            case R.id.tv_title_xinqing:
                mTvTitleAiai.setCompoundDrawables(null, null, null, null);
                mTvTitleJingqi.setCompoundDrawables(null, null, null, null);
                mTvTitleXinqing.setCompoundDrawables(null, null, null, bottom);
                mTvTitleZhengzhuang.setCompoundDrawables(null, null, null, null);
                yemian = 3;
                mTvTop1.setText("");
                mTvTop2.setText("");
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
                frameLayout1.setVisibility(View.INVISIBLE);
                frameLayout2.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_title_zhengzhuang:
                mTvTitleAiai.setCompoundDrawables(null, null, null, null);
                mTvTitleJingqi.setCompoundDrawables(null, null, null, null);
                mTvTitleXinqing.setCompoundDrawables(null, null, null, null);
                mTvTitleZhengzhuang.setCompoundDrawables(null, null, null, bottom);
                yemian = 4;
                mTvTop1.setText("");
                mTvTop2.setText("");
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
                frameLayout1.setVisibility(View.INVISIBLE);
                frameLayout2.setVisibility(View.INVISIBLE);

                break;
        }
    }

    private int yemian = 0;
    private String jingqiState="";
    String aiaiState="";
    String xinqingState="";
    String zhengzhuangState="";
    private int select = 0;
    private int select2 = 0;

    public interface OnCloseListener {
        void onClick(Dialog dialog, boolean is, String confirm);
    }
}
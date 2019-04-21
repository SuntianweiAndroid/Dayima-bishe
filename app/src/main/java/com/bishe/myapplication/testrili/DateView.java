package com.bishe.myapplication.testrili;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bishe.myapplication.R;
import com.bishe.myapplication.utils.MySharedPreferences;


/**
 * author：Administrator on 2017/4/11 15:43
 * description:文件说明
 * version:版本
 */
public class DateView extends LinearLayout implements View.OnClickListener {
    private View conentView;
    private GridView gridView;
    private TextView frontMonthTv;
    private TextView nextMonthTv;
    private TextView currentDateTv;
    private DateMonthAdapter adapter;
    private TextView ok;
    public String date;
    private int currentPosition = -1;
    final int RIGHT = 0;
    final int LEFT = 1;
    private GestureDetector gestureDetector;

    public DateView(Context context) {
        super(context, null);

    }

    public DateView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {
        conentView = LayoutInflater.from(getContext()).inflate(R.layout.view_poup, null, false);
        gridView = (GridView) conentView.findViewById(R.id.list);
        frontMonthTv = (TextView) conentView.findViewById(R.id.front_month);
        frontMonthTv.setOnClickListener(this);
        nextMonthTv = (TextView) conentView.findViewById(R.id.next_month);
        nextMonthTv.setOnClickListener(this);
        currentDateTv = (TextView) conentView.findViewById(R.id.now_month);
        ok = (TextView) conentView.findViewById(R.id.ok);
        ok.setOnClickListener(this);
        addView(conentView);
    }

    public void initDate(String dateString) {
        currentDateTv.setText(dateString);
        gestureDetector = new GestureDetector(getContext(), onGestureListener);
        this.date = dateString;
        if (TextUtils.isEmpty(date)) {
            this.date = DataUtils.getCurrDate("yyyy-MM-dd");
        }
        adapter = new DateMonthAdapter(getContext());
        adapter.setData(DataUtils.getMonth(date));
        gridView.setAdapter(adapter);
        adapter.setDateString(date);
        adapter.setSelectedPosition(DataUtils.getSelectPosition());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onItemClick != null && !TextUtils.isEmpty(adapter.getItem(position).date)) {
                    adapter.setSelectedPosition(position);
                    currentDateTv.setText("当前月份：" + DataUtils.formatDate(adapter.getItem(position).date, "yyyy-MM"));
                    date = adapter.getItem(position).date;
                }
            }
        });
        gridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    private void jisuan() {

    }
    /**
     * 手势监听是否是左右滑动
     */
    private GestureDetector.OnGestureListener onGestureListener =
            new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                       float velocityY) {
                    float x = e2.getX() - e1.getX();
                    float y = e2.getY() - e1.getY();

                    if (x > 100) {
                        doResult(RIGHT);
                    } else if (x < -100) {
                        doResult(LEFT);
                    }
                    return true;
                }
            };

    public void doResult(int action) {

        switch (action) {
            case RIGHT:
                date = DataUtils.getSomeMonthDay(date, -1);
                adapter.setData(DataUtils.getMonth(date));
                adapter.setDateString(date);
                adapter.setSelectedPosition(DataUtils.getSelectPosition());
                currentDateTv.setText("当前月份：" + DataUtils.formatDate(date, "yyyy-MM"));
                Log.e("wenzihao", "go right");
                break;
            case LEFT:
                date = DataUtils.getSomeMonthDay(date, +1);
                adapter.setData(DataUtils.getMonth(date));
                adapter.setDateString(date);
                adapter.setSelectedPosition(DataUtils.getSelectPosition());
                currentDateTv.setText("当前月份：" + DataUtils.formatDate(date, "yyyy-MM"));
                Log.e("wenzihao", "go left");
                break;

        }
    }

    public OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == frontMonthTv.getId()) {
            date = DataUtils.getSomeMonthDay(date, -1);
            adapter.setData(DataUtils.getMonth(date));
            adapter.setDateString(date);
            adapter.setSelectedPosition(DataUtils.getSelectPosition());
            currentDateTv.setText("当前月份：" + DataUtils.formatDate(date, "yyyy-MM"));
        } else if (id == nextMonthTv.getId()) {
            date = DataUtils.getSomeMonthDay(date, +1);
            adapter.setData(DataUtils.getMonth(date));
            adapter.setDateString(date);
            adapter.setSelectedPosition(DataUtils.getSelectPosition());
            currentDateTv.setText("当前月份：" + DataUtils.formatDate(date, "yyyy-MM"));
        } else if (id == ok.getId()) {
            if (onItemClick != null) {
                onItemClick.onItemClick(date);
            }

        }
    }

    /**
     * 点击回调接口
     */
    public interface OnItemClick {
        void onItemClick(String date);
    }

}

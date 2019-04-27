package com.bishe.myapplication;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * 自定义 listview 适配器
 */
public class Myadapter extends BaseAdapter {
    private List<String> data;
    private boolean isSelect = false;
    private int positions = -1;
    Context context;

    public Myadapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 当前listview选择的 position
     * @param position
     */
    public void setSelect( int position) {
        this.positions = position;
    }

    /**
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            //解析布局
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_register_item, null);
            //创建ViewHolder持有类
            holder = new ViewHolder();
            //将每个控件的对象保存到持有类中
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_item);
            //将每个convertView对象中设置这个持有类对象
            convertView.setTag(holder);
        }
        //每次需要使用的时候都会拿到这个持有类
        holder = (ViewHolder) convertView.getTag();
        //然后可以直接使用这个类中的控件，对控件进行操作，而不用重复去findViewById了
        holder.tvName.setText(data.get(position));
        //设置选择后的颜色
        if (positions == position) {
            holder.tvName.setTextColor(context.getResources().getColor(R.color.date_picker_text_light));
        } else {
            holder.tvName.setTextColor(context.getResources().getColor(R.color.date_picker_text_dark));
        }
        return convertView;
    }

    /**
     * 通过这个类来保存当前所有的控件id
     */
    static class ViewHolder {
        TextView tvName;
    }

}
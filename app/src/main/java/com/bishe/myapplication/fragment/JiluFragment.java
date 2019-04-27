package com.bishe.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.bishe.myapplication.R;
import com.bishe.myapplication.SlvSuggestAdapter;
import com.bishe.myapplication.dayimarili.HistogramView;
import com.bishe.myapplication.dayimarili.db.MenstruationCycle;
import com.bishe.myapplication.dayimarili.db.MenstruationModel;
import com.bishe.myapplication.dayimarili.db.MenstruationDao;

import java.util.List;

/**
 * 记录界面
 */
public class JiluFragment extends Fragment {
    private HistogramView hv;
    private MenstruationDao mtDao;
    private ListView slvSuggest;
    /**
     * 7
     */
    private TextView mJingqiDay;
    /**
     * 35
     */
    private TextView mZhouqiDay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jilu, container, false);
        initView(view);
        hv = (HistogramView) view.findViewById(R.id.hv);
        slvSuggest = (ListView) view.findViewById(R.id.slv_suggest);
        mtDao = new MenstruationDao(getActivity());
        MenstruationCycle mtCycle = mtDao.getMTCycle();
        List<MenstruationModel> mtmList = mtDao.getMTModelList(0, 0);
        //设置历史记录列表数据
        slvSuggest.setAdapter(new SlvSuggestAdapter(getActivity(), mtmList));
        //设置历史记录数据 柱形图
        hv.setHistogramList(mtmList);
        //显示平均天数与周期
        mJingqiDay.setText(mtCycle.getNumber()+"");
        mZhouqiDay.setText(mtCycle.getCycle()+"");
        return view;
    }

    private void initView(View view) {
        mJingqiDay = (TextView) view.findViewById(R.id.jingqi_day);
        mZhouqiDay = (TextView) view.findViewById(R.id.zhouqi_day);
    }
}

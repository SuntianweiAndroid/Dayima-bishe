package com.bishe.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bishe.myapplication.R;
import com.bishe.myapplication.testrili.DateView;
import com.bishe.myapplication.utils.MySharedPreferences;

public class HomeFragment extends Fragment {
    TextView info;
    TextView yuejingtishi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        DateView datePopupWindow = v.findViewById(R.id.RILI);
        info = v.findViewById(R.id.infor);

        datePopupWindow.initDate(MySharedPreferences.getRiqiTime2());
        datePopupWindow.setOnItemClick(new DateView.OnItemClick() {
            @Override
            public void onItemClick(String date) {
                info.setText(date);
            }
        });
        return v;
    }

}

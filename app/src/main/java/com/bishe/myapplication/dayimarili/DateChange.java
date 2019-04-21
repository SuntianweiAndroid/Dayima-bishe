package com.bishe.myapplication.dayimarili;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2015/5/21.
 */
public class DateChange {
    /**
     * ʱ���ת�������ڸ�ʽ�ַ�??
     * hh??2Сʱ??
     * HH??4Сʱ??
     *
     * @param seconds ��ȷ������ַ���
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }

        if (format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    /**
     * ���ڸ�ʽ�ַ���ת����ʱ��??
     *
     * @param format �磺yyyy-MM-dd HH:mm:ss
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * ���ڸ�ʽ�ַ���ת����ʱ��??
     *
     * @param format �磺yyyy-MM-dd HH:mm:ss
     */
    public static long dateTimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date_str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * ��ȡ��������ʱ��??
     *
     * @return
     */
    public static Long getDate() {
        int y, m, d;
        Calendar cal = Calendar.getInstance();
        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DATE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long l = 0;
        try {
            l = sdf.parse(y + "-" + (m + 1) + "-" + d).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l;
    }

    /**
     * ȡ�õ�ǰʱ�������ȷ����??
     */
    public static String timeStamp() {
        long time = System.currentTimeMillis();
        String t = String.valueOf(time / 1000);
        return t;
    }

    /**
     * �������������������
     *
     * @return **��**��
     */
    public static String getTime(long endTime, long startTime) {
        int M = (int) ((endTime - startTime) / 86400000l / 7);
        int D = (int) ((endTime - startTime) / 86400000l % 7);
        if (M != 0 && D != 0) {
            return M + "��" + D + "��";
        } else if (M == 0 && D != 0) {
            return D + "��";
        } else if (M != 0 && D == 0) {
            return M + "��";
        }
        return "1��";
    }

    //  ������??
    //  timeStamp=1417792627
    //  date=2014-12-05 23:17:07
    //  1417792627
    public static void main(String[] args) {
        String timeStamp = timeStamp();
        System.out.println("timeStamp=" + timeStamp);

        String date = timeStamp2Date(timeStamp, "yyyy-MM-dd HH:mm:ss");
        System.out.println("date=" + date);

        String timeStamp2 = date2TimeStamp(date, "yyyy-MM-dd HH:mm:ss");
        System.out.println(timeStamp2);
    }
}

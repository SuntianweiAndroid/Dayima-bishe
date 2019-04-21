package com.bishe.myapplication.testrili;

public class DateEntity {
    public long million; //时间戳
    public String weekName;  //周几
    public int weekNum;  //一周中第几天，非中式
    public String date; //日期
    public boolean isToday;  //是否今天
    public String day;  //天
    public String luna;  //阴历
    public String startDay;
    public String finishDay;
    public int type; //显示状态：1为月经期，2为预测期，3为安全期，4为易孕期, 0为其他
    public int isStart = 1;//1表示开始，2表示结束，0表示其他

    @Override
    public String toString() {
        return "DateEntity{" +
                "million=" + million +
                ", weekName='" + weekName + '\'' +
                ", weekNum=" + weekNum +
                ", date='" + date + '\'' +
                ", isToday=" + isToday +
                ", day='" + day + '\'' +
                ", luna='" + luna + '\'' +
                '}';
    }
}

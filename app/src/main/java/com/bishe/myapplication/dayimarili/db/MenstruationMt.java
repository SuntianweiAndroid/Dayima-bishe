package com.bishe.myapplication.dayimarili.db;

/**
 * 大姨妈日期 和记录 数据库表格
 */
public class MenstruationMt {
    private long date;//日期
    private String quantity;//记录

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "MenstruationMt{" +
                "date=" + date +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}

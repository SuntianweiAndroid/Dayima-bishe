package com.bishe.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {

    private static SharedPreferences.Editor editor;
    private static SharedPreferences sharedPreferences;

    private static MySharedPreferences MySharedPreferences;

    public MySharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("myproject", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public static MySharedPreferences getInstance(Context context) {
        if (MySharedPreferences == null) {
            MySharedPreferences = new MySharedPreferences(context);
        }
        return MySharedPreferences;
    }

    public static void setName(String str) {
        editor.putString("name", str);
        editor.commit();
    }

    public static String getName() {
        return sharedPreferences.getString("name", "");
    }

    public static void setPwd(String str) {
        editor.putString("pwd", str);
        editor.commit();
    }

    public static String getPwd() {
        return sharedPreferences.getString("pwd", "");
    }

    public static void setIslogin(boolean islogin) {
        editor.putBoolean("islogin", islogin);
        editor.commit();
    }

    public static boolean getIslogin() {
        return sharedPreferences.getBoolean("islogin", false);
    }

    public static void setJingqiTime(int jingqi) {
        editor.putInt("jingqi", jingqi);
        editor.commit();
    }

    public static int getJingqiTime() {
        return sharedPreferences.getInt("jingqi", 0);
    }

    public static void setZhouqiTime(int jingqi) {
        editor.putInt("Zhouqi", jingqi);
        editor.commit();
    }

    public static int getZhouqiTime() {
        return sharedPreferences.getInt("Zhouqi", 0);
    }

    public static void setRiqiTime(long jingqi) {
        editor.putLong("Riqi", jingqi);
        editor.commit();
    }

    public static long getRiqiTime() {
        return sharedPreferences.getLong("Riqi", 0);
    }
}

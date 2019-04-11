package com.bishe.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {

    private static SharedPreferences.Editor editor;
    private static SharedPreferences sharedPreferences;

    private static MySharedPreferences MySharedPreferences;

    public  MySharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("myproject", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }
    public static MySharedPreferences getInstance(Context context) {
        if (MySharedPreferences == null) {
            MySharedPreferences = new MySharedPreferences(context);
        }
        return MySharedPreferences;
    }
    public static void setString(String key,String str){
        editor.putString(key, str);
        editor.commit();
    }
    public static String getString(String key){
       return sharedPreferences.getString(key, "");
    }
}

package com.bishe.myapplication.dayimarili.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

/**
 * 大姨妈数据表
 *
 * @author Administrator zxm
 */
public class MenstruationDBHelper extends SQLiteOpenHelper {

    public static final String TB_NAME_MT = "menstruation";
    public static final String TB_NAME_MT_CYCLE = "menstruation_cycle";
    public static final String TB_NAME_MT_TIME = "menstruation_time";

    public MenstruationDBHelper(Context context) {
        super(context, "dayima.db", null, 1);
    }
//参数说明
    //context:上下文对象
    //name:数据库名称
    //param:factory
    //version:当前数据库的版本，值必须是整数并且是递增的状态

    public MenstruationDBHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    public MenstruationDBHelper(Context context, String name,
                                SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "dayima.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /**
         * 大姨妈平均周期与平均天数�?
         */
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                TB_NAME_MT_CYCLE + " ( " +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " number  INTEGER, " + //月经天数
                " cycle INTEGER " + //月经周期
                " )"
        );
        /**
         * 大姨妈开始结束时间等数据�?
         */
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                TB_NAME_MT_TIME + " ( " +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " date INTEGER, " + //月份
                " startTime  INTEGER, " + //月经�?��时间
                " endTime INTEGER, " + //月经结束时间
                " cycle  INTEGER, " + //月经周期
                " number  INTEGER " + //月经天数
                " )"
        );
        /**
         * 大姨妈流量痛经表
         */
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                TB_NAME_MT + " ( " + " _id INTEGER PRIMARY KEY AUTOINCREMENT, " + " date INTEGER," + " quantity  STRING" + " )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME_MT);
        onCreate(db);

    }

    public void deleteAll(Context context) {

        context.deleteDatabase("dayima.db");
//		final File file = context.getDatabasePath("dayima.db");
//		file.delete();


    }

}

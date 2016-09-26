package com.example.railway.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HuoyunyuanOperHelper extends SQLiteOpenHelper {
    public static final String NAME = "huoyunyuan";
    public static final String DB_NAME = "data.db";
    /**
     * 收藏的表
     */
    public static final String FAULT_QUESTION_NAME = "fault_question";
    /**
     * 做题记录
     */
    public static final String HOMEWORK_RECORD = "homework_record";

    public HuoyunyuanOperHelper(Context context) {
        super(context, DB_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "
                + NAME
                + " (id integer primary key autoincrement,type varchar(20),content varchar(100),check0 varchar(100),check1 varchar(100),check2 varchar(100),check3 varchar(100),"
                + "answer varchar(4),order_hyu integer,nandu integer,from_hyu varchar(20),title1 varchar(20),title2 varchar(20),title3 varchar(20))");
        // type表示是哪一张表，参考MainActivity.mContents(0表示电瓶叉车司机，1表示货运安全员...)。type_id表示这道题在该表里的id方便从该表将这条字段取出
        db.execSQL("create table " + FAULT_QUESTION_NAME + " (type integer,type_id integer,constraint key_id primary key (type,type_id))");
//建立做题记录数据库。记录哪一张表下哪一道题的对错。homework_id为0表示正确，1表示错误
        db.execSQL("create table " + HOMEWORK_RECORD + " (type integer,type_id integer,homework_id integer,myanswer varchar(4),constraint key_id primary key (type,type_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

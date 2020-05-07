package com.example.a23534.account;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 23534 on 2020/4/29.
 */

public class DBHelper extends SQLiteOpenHelper{
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,"MoneyDatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {           //在创建数据库时创建一次表
        String sql = "create table money(id interget,date varchar(20),money double,place varchar(20),whichway varchar(20),classify varchar(20))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

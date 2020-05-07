package com.example.a23534.account;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity {
    DBHelper dbHelper;
    SQLiteDatabase db;
    List<Info> list = new ArrayList<Info>();
    ListView listView;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_activity);
        InfoMoneyAdapter infoMoneyAdapter = new InfoMoneyAdapter(this, R.layout.item_money,list);
        listView = (ListView) findViewById(R.id.lv_select);
        listView.setAdapter(infoMoneyAdapter);
        dbHelper = new DBHelper(this,"MoneyDatabase.db",null,1);
        db = dbHelper.getReadableDatabase();
        setCome("select",0);
    }

    private void setCome(String to, int flag) {                         //select
        cursor = db.rawQuery("select * from money",null);           //rawQuery方法
        for(int i = 0 ; i < cursor.getCount(); i ++ ){
            cursor.moveToNext();
            String id       = cursor.getString(0);
            String date     = cursor.getString(1);
            Double money    = cursor.getDouble(2);
            String place    = cursor.getString(3);
            String whichway = cursor.getString(4);
            String classify = cursor.getString(5);
            Info info = new Info(id,date,money,place,whichway,classify);
            list.add(info);
        }
    }
}

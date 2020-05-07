package com.example.a23534.account;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UpdateActivity extends AppCompatActivity {
    DBHelper dbHelper;
    SQLiteDatabase db;
    List<Info> list = new ArrayList<Info>();
    ListView listView;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);
        final InfoMoneyAdapter infoMoneyAdapter = new InfoMoneyAdapter(this, R.layout.item_money,list);
        listView = (ListView) findViewById(R.id.lv_select);
        listView.setAdapter(infoMoneyAdapter);
        dbHelper = new DBHelper(this,"MoneyDatabase.db",null,1);
        db = dbHelper.getReadableDatabase();
        setCome("select",0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final EditText et_update = new EditText(UpdateActivity.this);
                new AlertDialog.Builder(UpdateActivity.this).setTitle("请输入要修改的金额")
                        .setView(et_update).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Info update = (Info) listView.getItemAtPosition(position);
                        String id = update.id;
                        String newValue = et_update.getText().toString();
                        db.execSQL("update money set money = '" + newValue + "' where id = '" + id + "'");
                        Toast.makeText(UpdateActivity.this,"修改好啦",Toast.LENGTH_SHORT).show();
                        refresh();
                    }
                }).setNegativeButton("取消",null).show();
            }
        });
    }
    private void refresh(){                                              //刷新
        db = dbHelper.getReadableDatabase();
        list = new ArrayList<Info>();
        InfoMoneyAdapter infoAdapter = new InfoMoneyAdapter(this, R.layout.item_money, list);
        listView = (ListView) findViewById(R.id.lv_select);
        listView.setAdapter(infoAdapter);
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
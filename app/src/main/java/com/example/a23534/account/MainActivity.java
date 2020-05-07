package com.example.a23534.account;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper;
    SQLiteDatabase db;
    List<Info> list = new ArrayList<Info>();
    ListView listView;
    Cursor cursor;
    private Button bt_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Intentto(R.id.bt_add , InsertActivity.class);
        Intentto(R.id.bt_search,SelectActivity.class);
        Intentto(R.id.bt_edit,UpdateActivity.class);
        Intentto(R.id.bt_delete,DeleteActivity.class);
        dbHelper = new DBHelper(this, "MoneyDatabase.db",null,1);
        db = dbHelper.getReadableDatabase();
        double input = ioput("收入");
        double output = ioput("支出");
        TextView tv_input = (TextView)findViewById(R.id.tv_input);
        TextView tv_output = (TextView)findViewById(R.id.tv_output);
        String in  = String.format("%.2f",input);
        String out = String.format("%.2f",output);
        tv_input.setText(in);
        tv_output.setText(out);
        Button button = (Button) findViewById(R.id.bt_exit);
        Exit(button);
    }


    private double ioput (String way){
        Cursor cursor = db.rawQuery("select * from money where whichway = '" + way + "'",null);
        double sum = 0.0;
        for(int i = 0; i < cursor.getCount();i++){
            cursor.moveToNext();
            sum += cursor.getDouble(2);
        }
        return sum ;
    }

    private void Intentto (int button ,final Class target){     //intent函数
        Button bt =(Button) findViewById (button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,target);
                startActivity(intent);
            }
        });
    }
    private void Exit(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}

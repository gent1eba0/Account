package com.example.a23534.account;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.UUID;

import static com.example.a23534.account.R.id.sp_classify;
public class InsertActivity extends AppCompatActivity {

     DBHelper dbHelper;
     SQLiteDatabase db;
     String Classify = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_activity);
        dbHelper = new DBHelper(this,"MoneyDatabase.db",null,1);
        db = dbHelper.getReadableDatabase();
        final DatePicker datePicker = (DatePicker) findViewById(R.id.dp_timechoose);
        final EditText editText_money = (EditText) findViewById(R.id.et_money);
        final EditText editText_place = (EditText) findViewById(R.id.et_place);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rg);
        final Spinner spinner = (Spinner) findViewById(sp_classify);
        spinner.getSelectedItem();
        final String array[]={"出行","餐饮","娱乐","学习","其他"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this,R.layout.support_simple_spinner_dropdown_item,array);
        spinner.setAdapter(arrayAdapter);       //spinner适配器绑定
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Classify = array[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button button_submit = (Button) findViewById(R.id.bt_submit);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = UUID.randomUUID().toString();
                String money = editText_money.getText().toString();
                String place = editText_place.getText().toString();
                RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                String whichway = radioButton.getText().toString();
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();
                String date = year + "-" + month + "-" + day;
                db.execSQL("insert into money values('" + id + "','" + date + "','" + money + "','" + place + "','" + whichway + "','" + Classify + "')");
                Toast.makeText(InsertActivity.this,"数据添加成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

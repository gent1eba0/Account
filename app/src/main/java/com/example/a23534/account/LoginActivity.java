package com.example.a23534.account;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText ed_username;
    private EditText ed_password;
    private Button bt_login;
    private Button bt_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ed_username = (EditText) findViewById(R.id.login_username);
        ed_password = (EditText) findViewById(R.id.login_password);
        bt_login = (Button) findViewById(R.id.login_button);
        bt_exit = (Button) findViewById(R.id.login_exit);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ed_username.getText().toString();
                String password = ed_password.getText().toString();
                if(username.equals("1910242") && password.equals("123456")) {
                    Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                      startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this,"再想想看账户密码！",Toast.LENGTH_SHORT).show();
                }
           }
        });
        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

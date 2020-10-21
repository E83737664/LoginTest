package com.example.firstweekends;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ExtraHub extends AppCompatActivity {
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    boolean memory = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extrahub_layou);

        //pref = PreferenceManager.getDefaultSharedPreferences(this);
        pref=this.getSharedPreferences("data", Activity.MODE_PRIVATE);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        login = (Button)findViewById(R.id.button_denglu);
        //调试中使用 edit.clear只能清除暂存区里面的内容，类似于git，想要更改文件内容还要commit
//        editor = pref.edit();
//        editor.clear();
//        editor.commit();


        memory = pref.getBoolean("Memory",false);

        if(memory == true){
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            if(account.equals("admin") && password.equals("0000")){
                Intent intent = new Intent(ExtraHub.this, MyListView.class);
                Toast.makeText(ExtraHub.this, "已经记住密码 登陆成功", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if(account.equals("admin") && password.equals("0000")){
                    Intent intent = new Intent(ExtraHub.this, MyListView.class);
                    Toast.makeText(ExtraHub.this, "登陆成功", Toast.LENGTH_SHORT).show();

                    editor = pref.edit();
                    editor.putString("account", account);
                    editor.putString("password", password);
                    memory = true;
                    editor.putBoolean("Memory", memory);
                    editor.commit();

                    startActivity(intent);
                }else {
                    Toast.makeText(ExtraHub.this, "用户名或密码错误",Toast.LENGTH_SHORT).show();
                     passwordEdit.setText("");
                }
            }
        });


    }



}
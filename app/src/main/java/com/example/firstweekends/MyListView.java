package com.example.firstweekends;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MyListView extends AppCompatActivity {
    private String[] data = {"1","2","3","4","5","6","7","8","9","返回"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylistview_layout);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MyListView.this, android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int positon, long id){
                String pos = String.valueOf(positon+1);
                if(positon + 1 == 10){
                    Intent intent = new Intent(MyListView.this, MainActivity.class);
                    Toast.makeText(MyListView.this,"已返回",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else {
                    Toast.makeText(MyListView.this, pos, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected  void onStop(){
        super.onStop();
        boolean judge = true;
        if(isBackground(MyListView.this) == judge){
            Toast.makeText(MyListView.this,"已退出",Toast.LENGTH_SHORT).show();
        }

    }
    public static boolean isBackground(@org.jetbrains.annotations.NotNull Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Log.i("后台", appProcess.processName);
                    return false;
                }else{
                    Log.i("前台", appProcess.processName);
                    return true;
                }
            }
        }
        return false;
    }

}
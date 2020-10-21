package com.example.firstweekends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    int cnt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button_toList);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyListView.class);
                startActivity(intent);
            }
        });
        Button button1 = findViewById(R.id.button_extra);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExtraHub.class);
                startActivity(intent);
            }
        });
        Button button2 = findViewById(R.id.button_change);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout=(LinearLayout)MainActivity.this.findViewById(R.id.lay1);
                if(cnt % 2 == 0) {
                    layout.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.ex2));
                }else{
                    layout.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.white));
                }
                cnt++;
            }
        });
    }
}
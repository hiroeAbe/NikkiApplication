package com.example.abehiroe.nikkiapp2;


import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
/**
 * Created by AZUSA on 2016/06/15.
 */
public class MenuActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Sub 画面を起動
                Intent intent = new Intent();
                intent.setClassName("com.example.abehiroe.nikkiapp2", "com.example.abehiroe.nikkiapp2.MainActivity");
                startActivity(intent);
            }
        });
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Sub 画面を起動
                Intent intent = new Intent();
                intent.setClassName("com.example.abehiroe.nikkiapp2", "com.example.abehiroe.nikkiapp2.MainActivity2");
                startActivity(intent);
            }
        });
        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Sub 画面を起動
                Intent intent = new Intent();
                intent.setClassName("com.example.abehiroe.nikkiapp2", "com.example.abehiroe.nikkiapp2.MainActivity3");
                startActivity(intent);
            }
        });
        Button button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Sub 画面を起動
                Intent intent = new Intent();
                intent.setClassName("com.example.abehiroe.nikkiapp2", "com.example.abehiroe.nikkiapp2.MainActivity4");
                startActivity(intent);
            }
        });

    }
}
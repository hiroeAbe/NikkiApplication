package com.example.abehiroe.nikkiapp2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class NikkiOpen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Sub 画面を起動
                Intent intent = new Intent();
                intent.setClassName("com.example.abehiroe.nikkiapp2", "com.example.abehiroe.nikkiapp2.MenuActivity");
                startActivity(intent);
            }
        });
        Button tweetBt = (Button)findViewById(R.id.tweetBt);
        tweetBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String strTweet = "";
                String strMessage = "今日の出来事！";
                String strHashTag = "#日記アプリ";
                //String strUrl = "http://androidstudio.hatenablog.com/";

                try {
                    strTweet = "http://twitter.com/intent/tweet?text="
                            + URLEncoder.encode(strMessage, "UTF-8")
                            + "+"
                            + URLEncoder.encode(strHashTag, "UTF-8");
                           // + "&url="
                           // + URLEncoder.encode(strUrl, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(strTweet));
                startActivity(intent);
            }
        });

    }
}
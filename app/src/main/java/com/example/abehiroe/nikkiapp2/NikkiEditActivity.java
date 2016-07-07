package com.example.abehiroe.nikkiapp2;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class NikkiEditActivity extends AppCompatActivity {

    EditText mDateEdit;
    EditText mGoodEdit;
    EditText mBadEdit;
    EditText mDecisionEdit;
    Button mDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nikki_edit);
        mDateEdit = (EditText) findViewById(R.id.dateEdit);
        mGoodEdit = (EditText) findViewById(R.id.goodEdit);
        mBadEdit = (EditText) findViewById(R.id.badEdit);
        mDecisionEdit = (EditText) findViewById(R.id.decisionEdit);
        mDelete = (Button) findViewById(R.id.delete);
        long nikkiId = getIntent().getLongExtra("nikki_id", -1);
        if (nikkiId != -1) {
            Realm realm = Realm.getInstance(this);
            RealmResults<Nikki> results = realm.where(Nikki.class)
                    .equalTo("id", nikkiId).findAll();
            Nikki nikki = results.first();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String date = sdf.format(nikki.getDate());
            mDateEdit.setText(date);
            mGoodEdit.setText(nikki.getGood());
            mBadEdit.setText(nikki.getBad());
            mDecisionEdit.setText(nikki.getDecision());
            mDelete.setVisibility(View.VISIBLE);
        } else {
            mDelete.setVisibility(View.INVISIBLE);
        }

    }

    public void onSaveTapped(View view) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        try {
            date = sdf.parse(mDateEdit.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long nikkiId = getIntent().getLongExtra("nikki_id", -1);
        if (nikkiId != -1) {
            Realm realm = Realm.getInstance(this);
            RealmResults<Nikki> results = realm.where(Nikki.class)
                    .equalTo("id", nikkiId).findAll();
            realm.beginTransaction();
            Nikki nikki = results.first();
            nikki.setDate(date);
            nikki.setGood(mGoodEdit.getText().toString());
            nikki.setBad(mBadEdit.getText().toString());
            nikki.setDecision(mDecisionEdit.getText().toString());
            realm.commitTransaction();
            Snackbar.make(findViewById(android.R.id.content), "アップデートしました"
                    , Snackbar.LENGTH_SHORT)
                    .setAction("戻る", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    })
                    .setActionTextColor(Color.YELLOW)
                    .show();
        } else {
            Realm realm = Realm.getInstance(this);
            realm.beginTransaction();
            Number maxId = realm.where(Nikki.class).max("id");
            long nextId = 1;
            if (maxId != null) nextId = maxId.longValue() + 1;
            Nikki nikki = realm.createObject(Nikki.class);
            nikki.setId(nextId);
            nikki.setDate(date);
            nikki.setGood(mGoodEdit.getText().toString());
            nikki.setBad(mBadEdit.getText().toString());
            nikki.setDecision(mDecisionEdit.getText().toString());
            realm.commitTransaction();
            Toast.makeText(this, "追加しました", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void onDeleteTapped(View view) {
        long nikkiId = getIntent().getLongExtra("nikki_id", -1);
        if (nikkiId != -1) {
            Realm realm = Realm.getInstance(this);
            RealmResults<Nikki> results = realm.where(Nikki.class)
                    .equalTo("id", nikkiId).findAll();
            realm.beginTransaction();
            results.remove(0);
            realm.commitTransaction();
            Toast.makeText(this, "削除しました", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

   public void onTweetTapped(View view) {
        Button tweetBt = (Button)findViewById(R.id.tweet);
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

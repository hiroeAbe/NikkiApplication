package com.example.abehiroe.nikkiapp2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

public class NikkiAdapter4 extends RealmBaseAdapter<Nikki> {

    private static class ViewHolder {
        TextView decision;
        TextView good;

    }

    public NikkiAdapter4(Context context, RealmResults<Nikki> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.decision = (TextView) convertView.findViewById(android.R.id.text1);
            //viewHolder.good = (TextView)convertView.findViewById(android.R.id.text2);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Nikki nikki = realmResults.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String formatDate = sdf.format(nikki.getDate());
        viewHolder.decision.setText(nikki.getDecision());
        //viewHolder.good.setText(nikki.getGood());


        return convertView;
    }
}

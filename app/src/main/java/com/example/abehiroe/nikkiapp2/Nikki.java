package com.example.abehiroe.nikkiapp2;


import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Nikki extends RealmObject {
    @PrimaryKey
    private long id;
    private Date date;
    private String good;
    private String bad;
    private String decision;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getBad() {
        return bad;
    }

    public void setBad(String bad) {
        this.bad = bad;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}
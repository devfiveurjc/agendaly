package com.devfiveurjc.agendaly.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject {

    @PrimaryKey
    private int id;

    private String title;
    private String description;
    private Date date;
    private boolean check = false;

    public Task() { }

    public Task(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getDate() { return this.date;}

    public int getDay() {
        SimpleDateFormat ftDay = new SimpleDateFormat("dd");
        return Integer.parseInt(ftDay.format(this.date));}

    public int getMonth() {
        SimpleDateFormat ftMonth = new SimpleDateFormat("MM");
        return Integer.parseInt(ftMonth.format(this.date))-1;}

    public int getYear() {
        SimpleDateFormat ftYear = new SimpleDateFormat("yyyy");
        return Integer.parseInt(ftYear.format(this.date));}

    public int getMinutes() {
        SimpleDateFormat ftMinutes = new SimpleDateFormat("mm");
        return Integer.parseInt(ftMinutes.format(this.date));}

    public int getHour() {
        SimpleDateFormat ftHour = new SimpleDateFormat("hh");
        return Integer.parseInt(ftHour.format(this.date));}

    public boolean isCheck() {
        return this.check;
    }

}

package com.devfiveurjc.agendaly.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject {

    @PrimaryKey
    private int id;

    private String title;
    private String description;
    private Date date;
    private boolean checked = false;

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

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    public Date getDate() {
        return this.date;
    }

    public boolean isChecked() {
        return this.checked;
    }

}

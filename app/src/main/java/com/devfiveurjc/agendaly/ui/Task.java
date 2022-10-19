package com.devfiveurjc.agendaly.ui;

import java.util.Calendar;

public class Task {
    private String title;
    private String description;
    private Calendar date;
    private Calendar hour;
    private boolean checked;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Task(String title, String description, Calendar date, Calendar hour) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.hour = hour;
    }

    public String getDescription() {
        return description;
    }

    public Calendar getDate() {
        return date;
    }

    public Calendar getHour() {
        return hour;
    }

    public String getTitle() {
        return title;
    }
}

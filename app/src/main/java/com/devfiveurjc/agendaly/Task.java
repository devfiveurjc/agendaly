package com.devfiveurjc.agendaly;

import java.util.Calendar;
import java.util.List;

public class Task {
    private String title;
    private String description;
    private Calendar date;
    private Calendar hour;

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

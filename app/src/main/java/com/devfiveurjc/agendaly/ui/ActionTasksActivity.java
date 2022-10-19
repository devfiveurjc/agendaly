package com.devfiveurjc.agendaly.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.devfiveurjc.agendaly.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ActionTasksActivity extends AppCompatActivity {

    private ArrayList<Task> tasks = new ArrayList<>();
    private Task task;
    private int[] date = new int[3];
    private int[] hour = new int[2];
    private TextView titleT, descriptionT, dateT, hourT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_tasks);

        titleT = findViewById(R.id.textViewA);
        descriptionT = findViewById(R.id.textViewA2);
        dateT = findViewById(R.id.textViewA3);
        hourT = findViewById(R.id.textViewA6);

        date[0] = task.getDate().get(Calendar.DAY_OF_MONTH);
        date[1] = task.getDate().get(Calendar.MONTH);
        date[2] = task.getDate().get(Calendar.YEAR);
        hour[0] = task.getHour().get(Calendar.HOUR_OF_DAY);
        hour[1] = task.getHour().get(Calendar.MINUTE);

        titleT.setText(task.getTitle());
        descriptionT.setText(task.getDescription());
        dateT.setText(date[0] + "/" + (date[1] + 1) + "/" + date[2]);
        hourT.setText(syncDisplayHour(hourT, hour));
    }

    private String syncDisplayHour(TextView display, int[] hour) {
        if (hour[1] < 10) {
            display.setText(hour[0] + ":0" + hour[1]);
        } else {
            display.setText(hour[0] + ":" + hour[1]);
        }
        return "";
    }

}

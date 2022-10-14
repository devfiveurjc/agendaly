package com.devfiveurjc.agendaly;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class ActionTasksActivity extends AppCompatActivity {

    private NewTaskActivity object;
    private ArrayList<Task> tasks = object.getTasks();
    private Task task;
    private int task_index;
    private int[] date = new int[3];
    private int[] hour = new int[2];
    private TextView titleT, descriptionT, dateT, hourT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_task);

        titleT = findViewById(R.id.textViewA);
        descriptionT = findViewById(R.id.textViewA2);
        dateT = findViewById(R.id.textViewA3);
        hourT = findViewById(R.id.textViewA6);

        //task = (Task) getIntent().getExtras().get("task");
        //task_index = (int) getIntent().getExtras().get("index_calendar");
        date[0] = task.getDate().get(Calendar.DAY_OF_MONTH);
        date[1] = task.getDate().get(Calendar.MONTH);
        date[2] = task.getDate().get(Calendar.YEAR);
        hour[0] = task.getHour().get(Calendar.HOUR_OF_DAY);
        hour[1] = task.getHour().get(Calendar.MINUTE);

        titleT.setText(task.getTitle());
        descriptionT.setText(task.getDescription());
        dateT.setText(date[0]+"/"+(date[1]+1)+"/"+date[2]);
        hourT.setText(syncDisplayHour(hourT,hour));
    }

    private String syncDisplayHour(TextView display, int[] hour) {
        if(hour[1]<10){
            display.setText(hour[0]+":0"+hour[1]);
        }else {
            display.setText(hour[0] + ":" + hour[1]);
        }
        return "";
    }


}
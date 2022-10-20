package com.devfiveurjc.agendaly.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.devfiveurjc.agendaly.R;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
    }

    public void switchViewTasksActivity(View view) {
        Intent switchActivityIntent = new Intent(this, TaskListActivity.class);
        startActivity(switchActivityIntent);
    }

}

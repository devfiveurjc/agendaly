package com.devfiveurjc.agendaly;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private ArrayList<Task> tasks = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void switchNewTask(View view) {
        Intent switchActivityIntent = new Intent(this, NewTaskActivity.class);
        startActivity(switchActivityIntent);
    }

    public void switchMyTasks(View view) {
        if (TaskData.getTasks().size() > 0) {
            Intent switchActivityIntent = new Intent(this, ViewTasksActivity.class);
            startActivity(switchActivityIntent);
        } else {
            Toast.makeText(this, "No tasks to show", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("")
    public void exit(View view) {
        finishAffinity();
    }

}

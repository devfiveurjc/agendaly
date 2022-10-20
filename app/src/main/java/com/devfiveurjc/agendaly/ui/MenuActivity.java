package com.devfiveurjc.agendaly.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.devfiveurjc.agendaly.R;
import com.devfiveurjc.agendaly.crud.CRUDTask;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void switchNewTask(View view) {
        Intent switchActivityIntent = new Intent(this, TaskAddActivity.class);
        startActivity(switchActivityIntent);
    }

    public void switchMyTasks(View view) {
        if (CRUDTask.getAllTasks().size() > 0) {
            Intent switchActivityIntent = new Intent(this, TaskListActivity.class);
            startActivity(switchActivityIntent);
        } else {
            Toast.makeText(this, "No tasks to show", Toast.LENGTH_LONG).show();
        }
    }

    public void exit(View view) {
        finishAffinity();
    }

}

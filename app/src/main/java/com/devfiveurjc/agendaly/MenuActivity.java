package com.devfiveurjc.agendaly;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void switchNewTask(View view) {
        Intent switchActivityIntent = new Intent(this, NewTaskActivity.class);
        startActivity(switchActivityIntent);
    }

    public void switchMyTasks (View view) {
        Intent switchActivityIntent = new Intent(this, ViewTasksActivity.class);
        startActivity(switchActivityIntent);
    }

    @SuppressLint("")
    public void exit(View view){
        finishAffinity();

    }

}

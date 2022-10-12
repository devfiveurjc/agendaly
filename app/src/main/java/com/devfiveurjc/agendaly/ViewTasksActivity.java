package com.devfiveurjc.agendaly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewTasksActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tasks);
    }

    public void switchMenuActivity(View view) {
        Intent switchActivityIntent = new Intent(this, MenuActivity.class);
        startActivity(switchActivityIntent);
    }

    public void switchCalendarActivity(View view) {
        Intent switchActivityIntent = new Intent(this, CalendarActivity.class);
        startActivity(switchActivityIntent);
    }
    public void toggle(View view){
        CheckedTextView checkedTextView=findViewById(view.getId());
        checkedTextView.toggle();
    }
}


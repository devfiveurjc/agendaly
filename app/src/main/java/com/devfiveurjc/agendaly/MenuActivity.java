package com.devfiveurjc.agendaly;

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
        Intent switchActivityIntent = new Intent(this, NewNoteActivity.class);
        startActivity(switchActivityIntent);
    }

    public void switchMyTasks (View view) {
        Intent switchActivityIntent = new Intent(this, NotesActivity.class);
        startActivity(switchActivityIntent);
    }

}

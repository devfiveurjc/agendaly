package com.devfiveurjc.agendaly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MainMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void nuevaNota (View view) {
        Intent i = new Intent(MainMenu.this, NewNote.class);
        startActivity(i);
        setContentView(R.layout.activity_notes);
    }

    public void verNotas (View view) {
        Intent i = new Intent(MainMenu.this, NewNote.class);
        startActivity(i);
    }


}

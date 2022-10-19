package com.devfiveurjc.agendaly.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.ContextThemeWrapper;
import android.view.View;

import com.devfiveurjc.agendaly.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switchMenuActivity(View view) {
        Intent switchActivityIntent = new Intent(this, MenuActivity.class);
        startActivity(switchActivityIntent);
    }

    public void showHelp(View view) {
        ContextThemeWrapper newContext = new ContextThemeWrapper(this, R.style.Theme_Agendaly_Dialog);
        AlertDialog.Builder builderAD = new AlertDialog.Builder(newContext);
        builderAD.setTitle(R.string.help);
        builderAD.setMessage(R.string.help_text);
        builderAD.setPositiveButton("OK", null);
        builderAD.create().show();
    }

}

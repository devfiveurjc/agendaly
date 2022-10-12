package com.devfiveurjc.agendaly;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class NewTaskActivity extends AppCompatActivity {

    private int[] date = new int[3];
    private int [] hour = new int [2];
    private TextView displayDate, displayHour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
    }

    public void switchMenuActivity(View view) {
        Intent switchActivityIntent = new Intent(this, MenuActivity.class);
        startActivity(switchActivityIntent);
    }

    /*private void syncDisplayDate() {
        displayDate.setText(StringChange.dateString(date));
    }*/

    public void openDate(View v) {
        DatePickerDialog dialog = new DatePickerDialog(
                NewTaskActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date[0] = dayOfMonth;
                        date[1] = month;
                        date[2] = year;
                        //syncDisplayDate();
                    }
                },
                date[2], date[1], date[0]);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void openHour(View view) {
        ContextThemeWrapper newContext = new ContextThemeWrapper(this, R.style.Theme_Agendaly_Dialog);
        TimePickerDialog tmd = new TimePickerDialog(newContext, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour[0] = hourOfDay;
                hour[1] = minute;
                //syncDisplayHour(displayHour, hour);
            }
        }, hour[0], hour[1], true);
        tmd.show();
    }

}
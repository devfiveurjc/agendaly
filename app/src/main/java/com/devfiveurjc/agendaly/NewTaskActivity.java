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

import com.google.android.material.textview.MaterialTextView;

import java.util.Calendar;

public class NewTaskActivity extends AppCompatActivity {

    private int[] date = new int[3];
    private int [] hour = new int [2];
    private TextView displayDate, displayHour;
    MaterialTextView textView1,textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        textView1 = (MaterialTextView) findViewById(R.id.textView4);
        textView2= (MaterialTextView) findViewById(R.id.textView5);
        textView1.setText("");
        textView2.setText("");

    }

    public void switchMenuActivity(View view) {
        Intent switchActivityIntent = new Intent(this, MenuActivity.class);
        startActivity(switchActivityIntent);
    }

    /*private void syncDisplayDate() {
        displayDate.setText(StringChange.dateString(date));
    }*/

    public void openDate(View view) {
        final Calendar c= Calendar.getInstance();
        date[0]=c.get(Calendar.DAY_OF_MONTH);
        date[1]=c.get(Calendar.MONTH);
        date[2]=c.get(Calendar.YEAR);
        DatePickerDialog dialog = new DatePickerDialog(NewTaskActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date[0]=dayOfMonth;
                        date[1]=month;
                        date[2]=year;
                        textView1.setText(date[0]+"/"+(date[1]+1)+"/"+date[2]);
                    }
                },
                date[2], date[1], date[0]);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void openHour(View view) {
        final Calendar c= Calendar.getInstance();
        hour[0]=c.get(Calendar.HOUR_OF_DAY);
        hour[1]=c.get(Calendar.MINUTE);
        ContextThemeWrapper newContext = new ContextThemeWrapper(this, R.style.Theme_Agendaly_Dialog);
        TimePickerDialog tmd = new TimePickerDialog(newContext, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour[0]=hourOfDay;
                hour[1]=minute;
                if(hour[1]<10){
                    textView2.setText(hour[0]+":0"+hour[1]);
                }else {
                    textView2.setText(hour[0] + ":" + hour[1]);
                }

            }
        }, hour[0], hour[1], true);
        tmd.show();
    }
/*
    public void openDate(View view) {
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
        MaterialTextView textView = findViewById(R.id.textView4);
    }
    */
    /*
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

     */

}
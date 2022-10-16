package com.devfiveurjc.agendaly;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textview.MaterialTextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;



public class NewTaskActivity extends AppCompatActivity {


    private int[] date = new int[3];
    private int [] hour = new int [2];
    private EditText title, description;
    private TextView displayDate, displayHour;
    MaterialTextView textView1,textView2;
    private ArrayList<Task> tasks= new ArrayList<>();


    public NewTaskActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        textView1 = (MaterialTextView) findViewById(R.id.textView4);
        textView2= (MaterialTextView) findViewById(R.id.textView5);
        textView1.setText("");
        textView2.setText("");
        displayHour = findViewById(R.id.textView5);
        displayDate = findViewById(R.id.textView4);

        Calendar calendar = Calendar.getInstance();  //current date and time
        hour[0] = calendar.get(Calendar.HOUR_OF_DAY);
        hour[1] = calendar.get(Calendar.MINUTE);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        date[2] = calendar.get(Calendar.YEAR);
        date[1] = calendar.get(Calendar.MONTH);
        date[0] = calendar.get(Calendar.DAY_OF_MONTH);

        syncDisplayDate();
        syncDisplayHour();

    }

    public void switchMenuActivity(View view) {
        Intent switchActivityIntent = new Intent(this, MenuActivity.class);
        startActivity(switchActivityIntent);

    }

    private void syncDisplayDate() {
        displayDate.setText(date[0]+"/"+(date[1]+1)+"/"+date[2]);
    }

    private void syncDisplayHour() {
        if(hour[1]<10){
            displayHour.setText(hour[0]+":0"+hour[1]);
        }else {
            displayHour.setText(hour[0] + ":" + hour[1]);
        }
    }

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
                        syncDisplayDate();
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
                syncDisplayHour();
            }
        }, hour[0], hour[1], true);
        tmd.show();
    }



    public void saveTask (View view) throws IOException {
        title = findViewById(R.id.editTextTextPersonName3);
        description = findViewById(R.id.editTextTextPersonName2);
        if(!title.getText().toString().equals("")) {
            Calendar dateTask = Calendar.getInstance();
            dateTask.set(date[2], date[1], date[0]);
            Calendar hourTask = Calendar.getInstance();
            hourTask.set(Calendar.HOUR_OF_DAY, hour[0]);
            hourTask.set(Calendar.MINUTE, hour[1]);
            Task task = new Task(title.getText().toString(), description.getText().toString(), dateTask, hourTask);
            TaskData.getTasks().add(task);
            MenuActivity menu= new MenuActivity();
            //save(tasks);
        }
        else {
            Toast.makeText(this,R.string.noTitle_text, Toast.LENGTH_LONG).show();
        }
        switchMenuActivity(view);
    }

    /*public void save (ArrayList tasks) throws IOException {
        FileOutputStream fileStream = new FileOutputStream("..\\Informacion.txt");
        ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
        objectStream.writeObject(tasks);
        objectStream.close();
    }*/



}
package com.devfiveurjc.agendaly.ui;

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

import com.devfiveurjc.agendaly.R;
import com.devfiveurjc.agendaly.crud.CRUDTask;
import com.devfiveurjc.agendaly.model.Task;
import com.google.android.material.textview.MaterialTextView;

import java.util.Calendar;
import java.util.Date;

public class NewTaskActivity extends AppCompatActivity {

    private int[] date = new int[3];
    private int[] hour = new int[2];
    private EditText title, description;
    private TextView displayDate, displayHour;
    MaterialTextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        textView1 = (MaterialTextView) findViewById(R.id.textView4);
        textView2 = (MaterialTextView) findViewById(R.id.textView5);
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

        title = findViewById(R.id.editTextTextPersonName3);
        description = findViewById(R.id.editTextTextPersonName2);

        syncDisplayDate();
        syncDisplayHour();

    }

    public void switchMenuActivity() {
        Intent switchActivityIntent = new Intent(this, MenuActivity.class);
        startActivity(switchActivityIntent);
    }

    private void syncDisplayDate() {
        displayDate.setText(date[0] + "/" + (date[1] + 1) + "/" + date[2]);
    }

    private void syncDisplayHour() {
        if (hour[1] < 10) {
            displayHour.setText(hour[0] + ":0" + hour[1]);
        } else {
            displayHour.setText(hour[0] + ":" + hour[1]);
        }
    }

    public void openDate(View view) {
        final Calendar c = Calendar.getInstance();
        date[0] = c.get(Calendar.DAY_OF_MONTH);
        date[1] = c.get(Calendar.MONTH);
        date[2] = c.get(Calendar.YEAR);
        DatePickerDialog dialog = new DatePickerDialog(NewTaskActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date[0] = dayOfMonth;
                        date[1] = month;
                        date[2] = year;
                        syncDisplayDate();
                    }
                },
                date[2], date[1], date[0]);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void openHour(View view) {
        final Calendar c = Calendar.getInstance();
        hour[0] = c.get(Calendar.HOUR_OF_DAY);
        hour[1] = c.get(Calendar.MINUTE);
        ContextThemeWrapper newContext = new ContextThemeWrapper(this, R.style.Theme_Agendaly_Dialog);
        TimePickerDialog tmd = new TimePickerDialog(newContext, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour[0] = hourOfDay;
                hour[1] = minute;
                syncDisplayHour();
            }
        }, hour[0], hour[1], true);
        tmd.show();
    }

    public void saveTask(View view) {
        title = findViewById(R.id.editTextTextPersonName3);
        description = findViewById(R.id.editTextTextPersonName2);
        if (!title.getText().toString().equals("")) {
            Calendar dateTaskCalendar = Calendar.getInstance();
            dateTaskCalendar.set(date[2], date[1], date[0]);
            Date dateTask = dateTaskCalendar.getTime();
            /* old with hour
            Calendar hourTask = Calendar.getInstance();
            hourTask.set(Calendar.HOUR_OF_DAY, hour[0]);
            hourTask.set(Calendar.MINUTE, hour[1]);
            */
            // realm
            Task task = new Task();
            task.setTitle(title.getText().toString());
            task.setDescription(description.getText().toString());
            task.setDate(dateTask);
            CRUDTask.addTask(task);
            // List<Task> tasks = CRUDTask.getAllTasks();
            /* old
            Task task = new Task(title.getText().toString(), description.getText().toString(),
                    dateTask, hourTask);
            TaskData.getTasks().add(task);

            if (date[0] == c.get(Calendar.DAY_OF_MONTH)) {
                TaskData.getTasksToday().add(task);
            } else if (date[0] == (c.get(Calendar.DAY_OF_MONTH) + 1)) {
                TaskData.getTasksTmrw().add(task);
            } else {
                TaskData.getTasksWeek().add(task);
            }
            */
            switchMenuActivity();
        } else {
            Toast.makeText(this, R.string.noTitle_text, Toast.LENGTH_LONG).show();
        }
    }

}

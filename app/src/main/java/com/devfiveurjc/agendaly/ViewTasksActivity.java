package com.devfiveurjc.agendaly;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewTasksActivity extends AppCompatActivity {
    private ArrayList<CheckedTextView> textListToday = new ArrayList();
    private ArrayList<CheckedTextView> textListTmrw = new ArrayList();
    private ArrayList<CheckedTextView> textListWeek = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tasks);
        textListToday.clear();
        textListTmrw.clear();
        textListWeek.clear();
        CheckedTextView task1 = (CheckedTextView) findViewById(R.id.checkedTextView1);
        textListToday.add(task1);
        CheckedTextView task2 = (CheckedTextView) findViewById(R.id.checkedTextView2);
        textListToday.add(task2);
        CheckedTextView task3 = (CheckedTextView) findViewById(R.id.checkedTextView3);
        textListToday.add(task3);
        CheckedTextView task4 = (CheckedTextView) findViewById(R.id.checkedTextView4);
        textListTmrw.add(task4);
        CheckedTextView task5 = (CheckedTextView) findViewById(R.id.checkedTextView5);
        textListTmrw.add(task5);
        CheckedTextView task6 = (CheckedTextView) findViewById(R.id.checkedTextView6);
        textListTmrw.add(task6);
        CheckedTextView task7 = (CheckedTextView) findViewById(R.id.checkedTextView7);
        textListWeek.add(task7);
        CheckedTextView task8 = (CheckedTextView) findViewById(R.id.checkedTextView8);
        textListWeek.add(task8);
        CheckedTextView task9 = (CheckedTextView) findViewById(R.id.checkedTextView9);
        textListWeek.add(task9);

        view();
    }


    private ArrayList<Task> tasks=new ArrayList<>();
    private TextView displayDate, displayHour;

    private void syncDisplayHour(TextView display, int[] hour) {
        if (hour[1] < 10) {
            display.setText(hour[0] + ":0" + hour[1]);
        } else {
            display.setText(hour[0] + ":" + hour[1]);
        }
    }

    public void view() {
        tasks = TaskData.getTasks();
        int size = tasks.size();

            final Calendar c = Calendar.getInstance();
            for (int i = 0; i < size && i < 9; i++) {
                Task task = tasks.get(i);
                int day = task.getDate().get(Calendar.DAY_OF_MONTH);

                if (day == c.get(Calendar.DAY_OF_MONTH) && textListToday.size() > 0) {
                    textListToday.get(0).setText(task.getTitle());
                    textListToday.remove(0);

                } else if (day == (c.get(Calendar.DAY_OF_MONTH) + 1) && textListTmrw.size() > 0) {
                    textListTmrw.get(0).setText(task.getTitle());
                    textListTmrw.remove(0);

                }//aqui habria que comparar si la tarea es de esta semana
                else if (textListWeek.size() > 0) {
                    textListWeek.get(0).setText(task.getTitle());
                    textListWeek.remove(0);

                }
            }
        }



    public void toggle(View view) {
        CheckedTextView checkedTextView = findViewById(view.getId());
        checkedTextView.toggle();
    }

    public void switchMenuActivity(View view) {
        Intent switchActivityIntent = new Intent(this, MenuActivity.class);
        startActivity(switchActivityIntent);
    }

    public void switchCalendarActivity(View view) {
        Intent switchActivityIntent = new Intent(this, CalendarActivity.class);
        startActivity(switchActivityIntent);
    }

    public void switchActionTasksActivity(View view) {
        Intent switchActivityIntent = new Intent(ViewTasksActivity.this, ActionTasksActivity.class);
        startActivity(switchActivityIntent);
    }
}

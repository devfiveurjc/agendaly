package com.devfiveurjc.agendaly;

import static java.lang.String.valueOf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewTasksActivity extends AppCompatActivity {

    private ArrayList<CheckedTextView> textListToday = new ArrayList();
    private ArrayList<CheckedTextView> textListTmrw = new ArrayList();
    private ArrayList<CheckedTextView> textListWeek = new ArrayList();
    private ArrayList<Task> tasks=new ArrayList<>();
    private TextView displayDate, displayHour;
    private boolean checkedStates[] = new boolean[9];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tasks);
        init();
        view();
    }


    private void syncDisplayHour(TextView display, int[] hour) {
        if (hour[1] < 10) {
            display.setText(hour[0] + ":0" + hour[1]);
        } else {
            display.setText(hour[0] + ":" + hour[1]);
        }
    }
    public void init(){
        textListToday.clear();
        textListTmrw.clear();
        textListWeek.clear();
        textListToday.add((CheckedTextView) findViewById(R.id.checkedTextView1));
        textListToday.add((CheckedTextView) findViewById(R.id.checkedTextView2));
        textListToday.add((CheckedTextView) findViewById(R.id.checkedTextView3));
        textListTmrw.add((CheckedTextView) findViewById(R.id.checkedTextView4));
        textListTmrw.add((CheckedTextView) findViewById(R.id.checkedTextView5));
        textListTmrw.add((CheckedTextView) findViewById(R.id.checkedTextView6));
        textListWeek.add((CheckedTextView) findViewById(R.id.checkedTextView7));
        textListWeek.add((CheckedTextView) findViewById(R.id.checkedTextView8));
        textListWeek.add((CheckedTextView) findViewById(R.id.checkedTextView9));
        for (int i=0;i<textListToday.size();i++){
            textListToday.get(i).setText("Empty space");
        }
        for (int i=0;i<textListTmrw.size();i++){
            textListTmrw.get(i).setText("Empty space");
        }
        for (int i=0;i<textListWeek.size();i++){
            textListWeek.get(i).setText("Empty space");
        }

    }

    public void view() {
        tasks = TaskData.getTasks();
        int todayCount=0,tmrwCount=0,weekCount=0;
        int size = tasks.size();
        int taskDate[]= new int[3];

            final Calendar c = Calendar.getInstance();
            for (int i = 0; i < size && i < 9; i++) {
                Task task = tasks.get(i);
                taskDate[0] = task.getDate().get(Calendar.DAY_OF_MONTH);
                taskDate[1] = task.getDate().get(Calendar.MONTH);
                taskDate[2] = task.getDate().get(Calendar.YEAR);

                if (taskDate[0] == c.get(Calendar.DAY_OF_MONTH) && taskDate[1] == c.get(Calendar.MONTH)
                     && taskDate[2]==c.get(Calendar.YEAR) && todayCount<3) {
                    textListToday.get(todayCount).setText(task.getTitle());
                    todayCount+=1;

                } else if (taskDate[0] == (c.get(Calendar.DAY_OF_MONTH)+1) && taskDate[1] == c.get(Calendar.MONTH)
                        && taskDate[2]==c.get(Calendar.YEAR) && tmrwCount<3) {
                    textListTmrw.get(tmrwCount).setText(task.getTitle());
                    tmrwCount+=1;

                }//aqui habria que comparar si la tarea es de esta semana
                else if (weekCount<3) {
                    textListWeek.get(weekCount).setText(task.getTitle());
                   weekCount+=1;

                }

            }
        }

    public void toggle(View view) {
        CheckedTextView checkedTextView = findViewById(view.getId());
        boolean valid = false;
        if (textListToday.contains(checkedTextView) &&
                (textListToday.indexOf(checkedTextView)+1) <= TaskData.getTasksToday().size()) {
            valid = true;

        } else if (textListTmrw.contains(checkedTextView) &&
                (textListTmrw.indexOf(checkedTextView)+1) <= TaskData.getTasksTmrw().size()) {
            valid = true;
        } else if (textListWeek.contains(checkedTextView) &&
                (textListWeek.indexOf(checkedTextView)+1) <= TaskData.getTasksWeek().size()) {
            valid = true;
        }
        if (valid == true) {
            checkedTextView.toggle();
        }
    }

    public void delete(View view) {

        for (int i = 0; i < textListToday.size(); i++) {
            if (textListToday.get(i).isChecked()) {
                TaskData.getTasks().remove(TaskData.getTasksToday().get(i));
                TaskData.getTasksToday().remove(i);
            }
        }
        for (int i = 0; i < textListTmrw.size(); i++) {
            if (textListTmrw.get(i).isChecked()) {
                TaskData.getTasks().remove(TaskData.getTasksTmrw().get(i));
                TaskData.getTasksTmrw().remove(i);
            }
        }
        for (int i = 0; i < textListWeek.size(); i++) {
            if (textListWeek.get(i).isChecked()) {
                TaskData.getTasks().remove(TaskData.getTasksWeek().get(i));
                TaskData.getTasksWeek().remove(i);
            }
        }
        switchMyTasksOrMenu(view);
    }
    public void switchMyTasksOrMenu (View view) {

        if (TaskData.getTasks().size()>0) {
            Intent switchActivityIntent = new Intent(this, ViewTasksActivity.class);
            startActivity(switchActivityIntent);
        }else {
            Toast.makeText(this, "There are no longer tasks to show", Toast.LENGTH_LONG).show();
            Intent switchActivityIntent = new Intent(this, MenuActivity.class);
            startActivity(switchActivityIntent);
        }
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

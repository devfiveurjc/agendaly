package com.devfiveurjc.agendaly;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewTasksActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tasks);
        view();
    }

    private NewTaskActivity object;
    private ArrayList<Task> tasks = object.getTasks();
    private TextView displayDate, displayHour;

    private void syncDisplayHour(TextView display, int[] hour) {
        if(hour[1]<10){
            display.setText(hour[0]+":0"+hour[1]);
        }else {
            display.setText(hour[0] + ":" + hour[1]);
        }
    }

    public void view (){
        LinearLayout layout = findViewById(com.google.android.material.R.id.scrollView);
        layout.removeAllViewsInLayout();
        ContextThemeWrapper newContext;
        if(tasks.size() == 0) {
            newContext = new ContextThemeWrapper(this, R.style.Theme_Agendaly_PlainText);
            AppCompatTextView noTaskText = new AppCompatTextView(newContext);
            noTaskText.setText("You dont have tasks");
            layout.addView(noTaskText);
        }
        else {
            newContext = new ContextThemeWrapper(this, R.style.Theme_Agendaly_PlainText);
            for (int i=0; i<tasks.size(); i++) {
                Task task = tasks.get(i);
                displayHour = findViewById(R.id.textView5);
                int[] hourTask = {task.getHour().get(Calendar.HOUR_OF_DAY), task.getHour().get(Calendar.MINUTE)};
                String text = task.getTitle() ;//+ "   " + syncDisplayHour(displayHour, hourTask);
                AppCompatButton button = new AppCompatButton(newContext);
                button.setText(text);
                button.setGravity(Gravity.START);
                button.setGravity(Gravity.CENTER_VERTICAL);
                button.setOnClickListener(new View.OnClickListener() {        //PARA HACER CLICK Y EDITAR O BORRAR
                    @Override
                    public void onClick(View v) {
                        Intent switchActivityIntent = new Intent(ViewTasksActivity.this, ActionTasksActivity.class);
                        //intent.putExtra("task", task);
                        //intent.putExtra("index_calendar", index);  //PARA BORRAR
                        startActivity(switchActivityIntent);
                    }
                });
                layout.addView(button);
            }
        }
    }

    public void toggle(View view){
        CheckedTextView checkedTextView=findViewById(view.getId());
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

}

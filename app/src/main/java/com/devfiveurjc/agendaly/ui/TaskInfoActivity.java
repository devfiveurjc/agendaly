package com.devfiveurjc.agendaly.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.devfiveurjc.agendaly.R;
import com.devfiveurjc.agendaly.crud.CRUDTask;
import com.devfiveurjc.agendaly.model.Task;

public class TaskInfoActivity extends AppCompatActivity {

    /* old
    private int[] date = new int[3];
    private int[] hour = new int[2];
    */

    int taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        TextView title = findViewById(R.id.taskTitle);
        TextView description = findViewById(R.id.taskDescription);
        TextView date = findViewById(R.id.taskDate);
        TextView status = findViewById(R.id.taskStatus);

        /* commented bc old and gives error
        date[0] = task.getDate().get(Calendar.DAY_OF_MONTH);
        date[1] = task.getDate().get(Calendar.MONTH);
        date[2] = task.getDate().get(Calendar.YEAR);
        hour[0] = task.getHour().get(Calendar.HOUR_OF_DAY);
        hour[1] = task.getHour().get(Calendar.MINUTE);

        titleT.setText(task.getTitle());
        descriptionT.setText(task.getDescription());
        dateT.setText(date[0] + "/" + (date[1] + 1) + "/" + date[2]);
        hourT.setText(syncDisplayHour(hourT, hour));
        */

        // retrieve bundle
        Bundle bundle = getIntent().getExtras();
        taskId = bundle.getInt("taskId");
        Task task = CRUDTask.getTask(taskId);
        // show task info
        title.setText(task.getTitle());
        description.setText(task.getDescription());
        date.setText(task.getDate().toString());
        status.setText((task.isChecked()) ? "Completed" : "Uncompleted");
    }

    public void deleteTask(View view) {
        CRUDTask.deleteTask(taskId);
        switchViewTasksActivity(view);
    }

    public void switchViewTasksActivity(View view) {
        Intent switchActivityIntent = new Intent(this, TaskListActivity.class);
        startActivity(switchActivityIntent);
    }

    /* idk what is this
    private String syncDisplayHour(TextView display, int[] hour) {
        if (hour[1] < 10) {
            display.setText(hour[0] + ":0" + hour[1]);
        } else {
            display.setText(hour[0] + ":" + hour[1]);
        }
        return "";
    }
    */

}

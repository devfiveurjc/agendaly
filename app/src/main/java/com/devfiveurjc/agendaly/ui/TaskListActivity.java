package com.devfiveurjc.agendaly.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.devfiveurjc.agendaly.R;
import com.devfiveurjc.agendaly.crud.CRUDTask;
import com.devfiveurjc.agendaly.databinding.ActivityTaskListBinding;
import com.devfiveurjc.agendaly.model.Task;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class TaskListActivity extends AppCompatActivity {

    private ActivityTaskListBinding binding;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTaskListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // show list
        listView = findViewById(R.id.tasks);
        List<Task> tasks = CRUDTask.getAllTasks();
        List<String> tasksTitle = new ArrayList<>();
        for (Task task : tasks) {
            tasksTitle.add(task.getTitle());
        }
        ArrayAdapter listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tasksTitle);
        listView.setAdapter(listAdapter);
        // click item in list
        listView.setOnItemClickListener((adapterView, view, id, l) -> {
            switchActionTasksActivity(view, id);
        });
    }

    /* in progress later for checkbox
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_done)
            return super.onOptionsItemSelected(item);
    }
    */

    public void switchActionTasksActivity(View view, int taskId) {
        Intent switchActivityIntent = new Intent(TaskListActivity.this, TaskInfoActivity.class);
        // pass task id to action tasks inside bundle
        Bundle bundle = new Bundle();
        bundle.putInt("taskId", taskId);
        switchActivityIntent.putExtras(bundle);
        startActivity(switchActivityIntent);
    }

}

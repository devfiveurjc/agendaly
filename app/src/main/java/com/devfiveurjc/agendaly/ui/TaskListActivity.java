package com.devfiveurjc.agendaly.ui;

import android.content.Intent;
import android.os.Bundle;

import com.devfiveurjc.agendaly.R;
import com.devfiveurjc.agendaly.crud.CRUDTask;
import com.devfiveurjc.agendaly.databinding.ActivityTaskListBinding;
import com.devfiveurjc.agendaly.model.Task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class TaskListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTaskListBinding binding = ActivityTaskListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // tasks card list with recycler view
        List<Task> tasks = CRUDTask.getAllTasks();
        ListAdapter listAdapter = new ListAdapter(tasks, this, task -> switchActionTasksActivity(task.getId()));
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    public void switchActionTasksActivity(int taskId) {
        Intent switchActivityIntent = new Intent(TaskListActivity.this, TaskInfoActivity.class);
        // pass task id to task info activity inside bundle
        Bundle bundle = new Bundle();
        bundle.putInt("taskId", taskId);
        switchActivityIntent.putExtras(bundle);
        startActivity(switchActivityIntent);
    }

}

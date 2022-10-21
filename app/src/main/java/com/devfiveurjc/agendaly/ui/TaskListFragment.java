package com.devfiveurjc.agendaly.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devfiveurjc.agendaly.R;
import com.devfiveurjc.agendaly.crud.CRUDTask;
import com.devfiveurjc.agendaly.model.Task;

import java.util.List;


public class TaskListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // tasks card list with recycler view
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        List<Task> tasks = CRUDTask.getAllTasks();
        ListAdapter listAdapter = new ListAdapter(tasks, requireContext(), task -> switchActionTasksActivity(task.getId()));
        RecyclerView recyclerView = view.findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
        return view;
    }

    public void switchActionTasksActivity(int taskId) {
        NavHostFragment.findNavController(TaskListFragment.this)
                .navigate(R.id.action_TaskListFragment_to_TaskInfoFragment);
        // Intent switchActivityIntent = new Intent(this, TaskInfoActivity.class);
        // pass task id to task info activity inside bundle
        /*
        Bundle bundle = new Bundle();
        bundle.putInt("taskId", taskId);
        switchActivityIntent.putExtras(bundle);
        startActivity(switchActivityIntent);
        */
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}

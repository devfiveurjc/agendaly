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
import com.devfiveurjc.agendaly.databinding.FragmentTaskListBinding;
import com.devfiveurjc.agendaly.model.Task;

import java.util.List;


public class TaskListFragment extends Fragment {

    private FragmentTaskListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState
    ) {
        // tasks card list with recycler view
        this.binding = FragmentTaskListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        List<Task> tasks = CRUDTask.getAllTasks();
        ListAdapter listAdapter = new ListAdapter(tasks, requireContext(), task -> switchTaskListFragment(task.getId()));
        RecyclerView recyclerView = view.findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
        return view;
    }

    public void switchTaskListFragment(int taskId) {
        // binding.addFloatingButton.setVisibility(View.INVISIBLE);
        NavHostFragment.findNavController(this)
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

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // button to task add fragment
        this.binding.addFloatingButton.setOnClickListener(v -> {
            // binding.addFloatingButton.setVisibility(View.GONE);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_TaskListFragment_to_TaskAddFragment);
        });
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

package com.devfiveurjc.agendaly.fragments;

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
import com.devfiveurjc.agendaly.adapters.ListAdapter;
import com.devfiveurjc.agendaly.crud.CRUDTask;
import com.devfiveurjc.agendaly.databinding.FragmentTaskListBinding;
import com.devfiveurjc.agendaly.models.Task;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class TaskListFragment extends Fragment {

    private FragmentTaskListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // tasks card list with recycler view
        this.binding = FragmentTaskListBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        List<Task> tasks = CRUDTask.getAllTasks();
        tasks = tasks.stream().sorted(Comparator.comparing(Task::getDate)).collect(Collectors.toList());
        ListAdapter listAdapter = new ListAdapter(tasks, requireContext(), task -> switchTaskInfoFragment(task.getId()));
        RecyclerView recyclerView = view.findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(listAdapter);
        return view;
    }

    public void switchTaskInfoFragment(int taskId) {
        Bundle bundle = new Bundle();
        bundle.putInt("taskId", taskId);
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_TaskListFragment_to_TaskInfoFragment, bundle);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.binding.addFloatingButton.setOnClickListener(v -> {
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

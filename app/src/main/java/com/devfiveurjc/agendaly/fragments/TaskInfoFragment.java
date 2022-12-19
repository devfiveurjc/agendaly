package com.devfiveurjc.agendaly.fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devfiveurjc.agendaly.R;
import com.devfiveurjc.agendaly.crud.CRUDTask;
import com.devfiveurjc.agendaly.databinding.FragmentTaskInfoBinding;
import com.devfiveurjc.agendaly.models.Task;


public class TaskInfoFragment extends Fragment {

    private FragmentTaskInfoBinding binding;
    private int taskId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // tasks card list with recycler view
        this.binding = FragmentTaskInfoBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        TextView title = view.findViewById(R.id.taskInfoTitle);
        TextView description = view.findViewById(R.id.taskInfoDescription);
        TextView date = view.findViewById(R.id.taskInfoDate);
        TextView status =  view.findViewById(R.id.taskInfoStatus);
        // retrieve bundle
        assert this.getArguments() != null;
        this.taskId = this.getArguments().getInt("taskId");
        Task task = CRUDTask.getTask(this.taskId);
        // show task info
        title.setText(task.getTitle());
        description.setText(task.getDescription());
        date.setText(task.getDate().toString());
        status.setText((task.isCheck()) ? "Completed" : "Uncompleted");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.binding.taskInfoDeleteButton.setOnClickListener(v -> {
            this.deleteCautionMessage();
        });
        this.binding.taskInfoEditButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("taskId", this.taskId);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_TaskInfoFragment_to_TaskEditFragment, bundle);
        });
    }

    private void deleteCautionMessage() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this.getContext());
        alert.setMessage(getString(R.string.caution_text_delete) + getString(R.string.caution_advise))
            .setCancelable(false)
            .setTitle(R.string.caution)
            .setPositiveButton(R.string.confirm, (dialogInterface, i) -> {
                CRUDTask.deleteTask(this.taskId);
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_TaskInfoFragment_to_TaskListFragment);
            })
            .setNegativeButton(R.string.goback, (dialogInterface, i) -> dialogInterface.cancel());
        alert.show();
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

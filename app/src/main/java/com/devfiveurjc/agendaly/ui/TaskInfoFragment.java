package com.devfiveurjc.agendaly.ui;

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
import com.devfiveurjc.agendaly.model.Task;


public class TaskInfoFragment extends Fragment {

    private FragmentTaskInfoBinding binding;
    int taskId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState
    ) {
        // tasks card list with recycler view
        this.binding = FragmentTaskInfoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        TextView title = view.findViewById(R.id.taskInfoTitle);
        TextView description = view.findViewById(R.id.taskInfoDescription);
        TextView date = view.findViewById(R.id.taskInfoDate);
        TextView status =  view.findViewById(R.id.taskInfoStatus);

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
        assert getArguments() != null;
        taskId = getArguments().getInt("taskId");
        Task task = CRUDTask.getTask(taskId);
        // show task info
        title.setText(task.getTitle());
        description.setText(task.getDescription());
        date.setText(task.getDate().toString());
        status.setText((task.isCheck()) ? "Completed" : "Uncompleted");
        switchTaskEditFragment(taskId);
        return view;
    }

    public void deleteTask() {
        CRUDTask.deleteTask(taskId);
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

    public void switchTaskEditFragment(int taskId) {
        /*Bundle bundle = new Bundle();
        bundle.putInt("taskId", taskId);
        NavHostFragment.findNavController(this)
                .navigate(); //crear action*/
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // button deleteTask
        this.binding.taskInfoDeleteButton.setOnClickListener(v -> {
            deleteTask();
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_TaskInfoFragment_to_TaskListFragment);
        });
        this.binding.taskInfoEditButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("taskId", taskId);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_TaskInfoFragment_to_TaskEditFragment,bundle);
        });

        this.binding.taskInfoBackButton.setOnClickListener(v -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_TaskInfoFragment_to_TaskListFragment);
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

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
import android.widget.TextView;

import com.devfiveurjc.agendaly.R;
import com.devfiveurjc.agendaly.crud.CRUDTask;
import com.devfiveurjc.agendaly.model.Task;

import java.util.List;


public class TaskInfoFragment extends Fragment {

    int taskId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState
    ) {
        // tasks card list with recycler view
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        return view;
        /*
        TextView title = requireView().findViewById(R.id.taskTitle);
        TextView description = requireView().findViewById(R.id.taskDescription);
        TextView date = requireView().findViewById(R.id.taskDate);
        TextView status = requireView().findViewById(R.id.taskStatus);
         */

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
        /*
        Bundle bundle = getIntent().getExtras();
        taskId = bundle.getInt("taskId");
        Task task = CRUDTask.getTask(taskId);

        // show task info
        title.setText(task.getTitle());
        description.setText(task.getDescription());
        date.setText(task.getDate().toString());
        status.setText((task.isCheck()) ? "Completed" : "Uncompleted");
         */
        // return view;
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

    public void deleteTask(View view) {
        CRUDTask.deleteTask(taskId);
        // switchViewTasksActivity(view);
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

package com.devfiveurjc.agendaly.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devfiveurjc.agendaly.R;
import com.devfiveurjc.agendaly.crud.CRUDTask;
import com.devfiveurjc.agendaly.databinding.FragmentTaskAddBinding;
import com.devfiveurjc.agendaly.model.Task;
import com.devfiveurjc.agendaly.dialogs.DateTimeDialog;

import java.util.Calendar;
import java.util.Date;


public class TaskAddFragment extends Fragment {

    private FragmentTaskAddBinding binding;
    private final int[] date = new int[3];
    private final int[] time = new int[2];
    private EditText titleInputText, descriptionInputText;
    private TextView dateDisplay, timeDisplay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentTaskAddBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        this.timeDisplay = view.findViewById(R.id.taskAddTime);
        this.dateDisplay = view.findViewById(R.id.taskAddDate);
        this.titleInputText = view.findViewById(R.id.taskAddEditTitle);
        this.descriptionInputText = view.findViewById(R.id.taskAddEditDescription);
        //current date and time
        Calendar calendar = Calendar.getInstance();
        this.time[0] = calendar.get(Calendar.HOUR_OF_DAY);
        this.time[1] = calendar.get(Calendar.MINUTE);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        this.date[2] = calendar.get(Calendar.YEAR);
        this.date[1] = calendar.get(Calendar.MONTH);
        this.date[0] = calendar.get(Calendar.DAY_OF_MONTH);
        DateTimeDialog.syncDisplayDate(this.dateDisplay, this.date);
        DateTimeDialog.syncDisplayTime(this.timeDisplay, this.time);
        return view;
    }

    public void saveTask(View view) {
        this.titleInputText = view.findViewById(R.id.taskAddEditTitle);
        this.descriptionInputText = view.findViewById(R.id.taskAddEditDescription);
        boolean isTitleInputEmpty = this.titleInputText.getText().toString().equals("");
        if (!isTitleInputEmpty) {
            Calendar dateTaskCalendar = Calendar.getInstance();
            dateTaskCalendar.set(this.date[2], this.date[1], this.date[0], this.time[0], this.time[1]);
            Date dateTask = dateTaskCalendar.getTime();
            // realm
            String titleText = this.titleInputText.getText().toString();
            String descriptionText = this.descriptionInputText.getText().toString();
            Task task = new Task(titleText, descriptionText, dateTask);
            CRUDTask.addTask(task);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_TaskAddFragment_to_TaskListFragment);
        } else {
            Toast.makeText(this.getContext(), R.string.noTitle_text, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.binding.taskAddEditDateButton.setOnClickListener(v -> {
            DateTimeDialog.editDate(this.getContext(), this.dateDisplay, this.date);
        });
        this.binding.taskAddEditTimeButton.setOnClickListener(v -> {
            DateTimeDialog.editTime(this.getContext(), this.timeDisplay, this.time);
        });
        this.binding.taskAddSaveButton.setOnClickListener(v -> {
            this.saveTask(view);
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

package com.devfiveurjc.agendaly.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.devfiveurjc.agendaly.R;
import com.devfiveurjc.agendaly.crud.CRUDTask;
import com.devfiveurjc.agendaly.databinding.FragmentTaskEditBinding;
import com.devfiveurjc.agendaly.models.Task;
import com.devfiveurjc.agendaly.dialogs.DateTimeDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TaskEditFragment extends Fragment {

    private FragmentTaskEditBinding binding;
    private final int[] date = new int[3];
    private final int[] time = new int[2];
    private TextView dateDisplay, timeDisplay;
    private Task task;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentTaskEditBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        TextInputEditText title = view.findViewById(R.id.taskEditEditTitle);
        TextInputEditText description = view.findViewById(R.id.taskEditEditDescription);
        this.dateDisplay = view.findViewById(R.id.taskEditDate);
        this.timeDisplay = view.findViewById(R.id.taskEditTime);
        // realm
        assert getArguments() != null;
        int taskId = getArguments().getInt("taskId");
        this.task = CRUDTask.getTask(taskId);
        this.initDate(this.task);
        // display
        title.setText(this.task.getTitle());
        description.setText(this.task.getDescription());
        Date dateT = this.task.getDate();
        SimpleDateFormat ftDate = new SimpleDateFormat("dd/MM/yyyy");
        this.dateDisplay.setText(ftDate.format(dateT));
        SimpleDateFormat ftHour = new SimpleDateFormat("hh:mm");
        this.timeDisplay.setText(ftHour.format(dateT));
        return view;
    }

    private void initDate(Task task) {
        this.date[0] = task.getDay();
        this.date[1] = task.getMonth();
        this.date[2] = task.getYear();
        this.time[0] = task.getHour();
        this.time[1] = task.getMinutes();
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.binding.taskEditSaveButton.setOnClickListener(v -> {
            this.editCautionMessage(view);
        });
        this.binding.taskEditEditDateButton.setOnClickListener(v -> {
            DateTimeDialog.editDate(this.getContext(), this.dateDisplay, this.date);
        });
        this.binding.taskEditEditTimeButton.setOnClickListener(v -> {
            DateTimeDialog.editTime(this.getContext(), this.timeDisplay, this.time);
        });
    }

    private void editCautionMessage(View view) {
        ContextThemeWrapper newContext = new ContextThemeWrapper(this.getContext(), R.style.Theme_Agendaly_AlertDialog);
        AlertDialog.Builder alert = new AlertDialog.Builder(newContext);
        alert.setMessage(getString(R.string.caution_text_edit) + getString(R.string.caution_advise))
            .setCancelable(false)
            .setTitle(R.string.caution)
            .setPositiveButton(R.string.confirm, (dialogInterface, i) -> modifyTask(view))
            .setNegativeButton(R.string.goback, (dialogInterface, i) -> dialogInterface.cancel());
        alert.show();
    }

    private void modifyTask(View view) {
        EditText titleInputText = view.findViewById(R.id.taskEditEditTitle);
        EditText descriptionInputText = view.findViewById(R.id.taskEditEditDescription);
        boolean isTitleInputEmpty = titleInputText.getText().toString().equals("");
        if (!isTitleInputEmpty) {
            Calendar dateTaskCalendar = Calendar.getInstance();
            dateTaskCalendar.set(this.date[2], this.date[1], this.date[0], this.time[0], this.time[1]);
            Date dateTask = dateTaskCalendar.getTime();
            // realm
            String titleText = titleInputText.getText().toString();
            String descriptionText = descriptionInputText.getText().toString();
            Task newTask = new Task(titleText, descriptionText, dateTask);
            newTask.setCheck(this.task.isCheck());
            CRUDTask.updateTask(this.task, newTask);
            Toast.makeText(this.getContext(), R.string.successful_edit, Toast.LENGTH_LONG).show();
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_TaskEditFragment_to_TaskListFragment);
        } else {
            Toast.makeText(this.getContext(), R.string.no_title_text, Toast.LENGTH_LONG).show();
        }
    }

}

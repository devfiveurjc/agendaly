package com.devfiveurjc.agendaly.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.devfiveurjc.agendaly.model.Task;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TaskEditFragment extends Fragment {

    private FragmentTaskEditBinding binding;
    private final int[] date = new int[3];
    private final int[] hour = new int[2];
    private TextView dateDisplay, hourDisplay;
    private Task task;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentTaskEditBinding.inflate(inflater, container, false);
        View view = this.binding.getRoot();
        TextInputEditText title = view.findViewById(R.id.taskEditEditTitle);
        TextInputEditText description = view.findViewById(R.id.taskEditEditDescription);
        this.dateDisplay = view.findViewById(R.id.taskEditDate);
        this.hourDisplay = view.findViewById(R.id.taskEditHour);
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
        this.hourDisplay.setText(ftHour.format(dateT));
        return view;
    }

    private void initDate(Task task) {
        this.date[0]=task.getDay();
        this.date[1]=task.getMonth();
        this.date[2]=task.getYear();
        this.hour[0]=task.getHour();
        this.hour[1]=task.getMinutes();
    }

    private void syncDisplayDate() {
        if (date[1] < 9) {
            this.dateDisplay.setText(this.date[0] + "/0" + (this.date[1]+1) + "/" + this.date[2]);
        } else {
            this.dateDisplay.setText(this.date[0] + "/" + (this.date[1]+1) + "/" + this.date[2]);
        }

    }

    private void syncDisplayHour() {
        if (hour[1] < 10) {
            this.hourDisplay.setText(this.hour[0] + ":0" + this.hour[1]);
        } else {
            this.hourDisplay.setText(this.hour[0] + ":" + this.hour[1]);
        }
    }

    public void openDate(View view) {
        final Calendar c = Calendar.getInstance();
        date[0] = c.get(Calendar.DAY_OF_MONTH);
        date[1] = c.get(Calendar.MONTH);
        date[2] = c.get(Calendar.YEAR);
        DatePickerDialog dialog = new DatePickerDialog(requireContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                (view1, year, month, dayOfMonth) -> {
                    date[0] = dayOfMonth;
                    date[1] = month;
                    date[2] = year;
                    syncDisplayDate();
                },
                date[2], date[1], date[0]);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void openHour(View view) {
        final Calendar c = Calendar.getInstance();
        hour[0] = c.get(Calendar.HOUR_OF_DAY);
        hour[1] = c.get(Calendar.MINUTE);
        ContextThemeWrapper newContext = new ContextThemeWrapper(requireContext(), R.style.Theme_Agendaly_Dialog);
        TimePickerDialog tmd = new TimePickerDialog(newContext, (view1, hourOfDay, minute) -> {
            hour[0] = hourOfDay;
            hour[1] = minute;
            syncDisplayHour();
        }, hour[0], hour[1], true);
        tmd.show();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.binding.taskEditSaveButton.setOnClickListener(v -> {
            // TODO: confirmation popup
            //this.showMessage(view); if press OK -> modify
            this.modifyTask(view);
        });
        this.binding.taskEditEditDateButton.setOnClickListener(v -> {
            this.openDate(view);
        });
        this.binding.taskEditEditHourButton.setOnClickListener(v -> {
            this.openHour(view);
        });
    }

    /*
    public void showMessage(View view) {
        ContextThemeWrapper newContext = new ContextThemeWrapper(this, R.style.Theme.Agendaly.Dialog);
        AlertDialog.Builder builderAD = new AlertDialog.Builder(newContext);
        builderAD.setTitle(R.string.caution);
        builderAD.setMessage(R.string.caution_text);
        builderAD.setPositiveButton("OK", null);
        builderAD.setNegativeButton("CANCEL", null);
        builderAD.create().show();
    }
    */

    public void modifyTask(View view) {
        EditText titleInputText = view.findViewById(R.id.taskEditEditTitle);
        EditText descriptionInputText = view.findViewById(R.id.taskEditEditDescription);
        boolean isTitleInputEmpty = titleInputText.getText().toString().equals("");
        if (!isTitleInputEmpty) {
            Calendar dateTaskCalendar = Calendar.getInstance();
            dateTaskCalendar.set(this.date[2], this.date[1], this.date[0], this.hour[0], this.hour[1]);
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
            Toast.makeText(this.getContext(), R.string.noTitle_text, Toast.LENGTH_LONG).show();
        }
    }

}

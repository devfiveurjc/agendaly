package com.devfiveurjc.agendaly.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.ContextThemeWrapper;
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

import java.util.Calendar;
import java.util.Date;

public class TaskAddFragment extends Fragment {

    private FragmentTaskAddBinding binding;
    private final int[] date = new int[3];
    private final int[] hour = new int[2];
    private EditText titleInputText, descriptionInputText;
    private TextView displayDate, displayHour;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentTaskAddBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        this.displayHour = view.findViewById(R.id.taskAddHour);
        this.displayDate = view.findViewById(R.id.taskAddDate);
        this.titleInputText = view.findViewById(R.id.taskAddEditTitle);
        this.descriptionInputText = view.findViewById(R.id.taskAddEditDescription);
        //current date and time
        Calendar calendar = Calendar.getInstance();
        this.hour[0] = calendar.get(Calendar.HOUR_OF_DAY);
        this.hour[1] = calendar.get(Calendar.MINUTE);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        this.date[2] = calendar.get(Calendar.YEAR);
        this.date[1] = calendar.get(Calendar.MONTH);
        this.date[0] = calendar.get(Calendar.DAY_OF_MONTH);
        this.syncDisplayDate();
        this.syncDisplayHour();
        return view;
    }

    private void syncDisplayDate() {
        this.displayDate.setText(date[0] + "/" + (date[1] + 1) + "/" + date[2]);
    }

    private void syncDisplayHour() {
        if (hour[1] < 10) {
            this.displayHour.setText(hour[0] + ":0" + hour[1]);
        } else {
            this.displayHour.setText(hour[0] + ":" + hour[1]);
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

    public void saveTask(View view) {
        this.titleInputText = view.findViewById(R.id.taskAddEditTitle);
        this.descriptionInputText = view.findViewById(R.id.taskAddEditDescription);
        boolean isTitleInputEmpty = this.titleInputText.getText().toString().equals("");
        if (!isTitleInputEmpty) {
            Calendar dateTaskCalendar = Calendar.getInstance();
            dateTaskCalendar.set(date[2], date[1], date[0], hour[0], hour[1]);
            Date dateTask = dateTaskCalendar.getTime();
            // realm
            String titleText = this.titleInputText.getText().toString();
            String descriptionText = this.descriptionInputText.getText().toString();
            Task task = new Task(titleText, descriptionText, dateTask);
            CRUDTask.addTask(task);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_TaskAddFragment_to_TaskListFragment);
        } else {
            Toast.makeText(getContext(), R.string.noTitle_text, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.binding.taskAddEditDateButton.setOnClickListener(v -> {
            this.openDate(view);
        });
        this.binding.taskAddEditHourButton.setOnClickListener(v -> {
            this.openHour(view);
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

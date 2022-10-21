package com.devfiveurjc.agendaly.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.devfiveurjc.agendaly.R;
import com.devfiveurjc.agendaly.crud.CRUDTask;
import com.devfiveurjc.agendaly.model.Task;
import com.google.android.material.textview.MaterialTextView;

import java.util.Calendar;
import java.util.Date;

public class TaskAddFragment extends Fragment {

    private int[] date = new int[3];
    private int[] hour = new int[2];
    private EditText title, description;
    private TextView displayDate, displayHour;
    MaterialTextView textView1, textView2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView1 = (MaterialTextView) requireView().findViewById(R.id.textView4);
        textView2 = (MaterialTextView) requireView().findViewById(R.id.textView5);
        textView1.setText("");
        textView2.setText("");
        displayHour = requireView().findViewById(R.id.textView5);
        displayDate = requireView().findViewById(R.id.textView4);

        Calendar calendar = Calendar.getInstance();  //current date and time
        hour[0] = calendar.get(Calendar.HOUR_OF_DAY);
        hour[1] = calendar.get(Calendar.MINUTE);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        date[2] = calendar.get(Calendar.YEAR);
        date[1] = calendar.get(Calendar.MONTH);
        date[0] = calendar.get(Calendar.DAY_OF_MONTH);

        title = requireView().findViewById(R.id.editTextTextPersonName3);
        description = requireView().findViewById(R.id.editTextTextPersonName2);

        syncDisplayDate();
        syncDisplayHour();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_add, container, false);
    }

    private void syncDisplayDate() {
        displayDate.setText(date[0] + "/" + (date[1] + 1) + "/" + date[2]);
    }

    private void syncDisplayHour() {
        if (hour[1] < 10) {
            displayHour.setText(hour[0] + ":0" + hour[1]);
        } else {
            displayHour.setText(hour[0] + ":" + hour[1]);
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
        title = requireView().findViewById(R.id.editTextTextPersonName3);
        description = requireView().findViewById(R.id.editTextTextPersonName2);
        if (!title.getText().toString().equals("")) {
            Calendar dateTaskCalendar = Calendar.getInstance();
            dateTaskCalendar.set(date[2], date[1], date[0]);
            Date dateTask = dateTaskCalendar.getTime();
            /* old with hour
            Calendar hourTask = Calendar.getInstance();
            hourTask.set(Calendar.HOUR_OF_DAY, hour[0]);
            hourTask.set(Calendar.MINUTE, hour[1]);
            */
            // realm
            String titleText = title.getText().toString();
            String descriptionText = description.getText().toString();
            Task task = new Task(titleText, descriptionText, dateTask);
            CRUDTask.addTask(task);
            // List<Task> tasks = CRUDTask.getAllTasks();
            /* old
            Task task = new Task(title.getText().toString(), description.getText().toString(),
                    dateTask, hourTask);
            TaskData.getTasks().add(task);

            if (date[0] == c.get(Calendar.DAY_OF_MONTH)) {
                TaskData.getTasksToday().add(task);
            } else if (date[0] == (c.get(Calendar.DAY_OF_MONTH) + 1)) {
                TaskData.getTasksTmrw().add(task);
            } else {
                TaskData.getTasksWeek().add(task);
            }
            */
            // switchMenuActivity();
        } else {
            // Toast.makeText(this, R.string.noTitle_text, Toast.LENGTH_LONG).show();
        }
    }

}

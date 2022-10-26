package com.devfiveurjc.agendaly.ui;

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
import com.devfiveurjc.agendaly.databinding.FragmentTaskInfoBinding;
import com.devfiveurjc.agendaly.model.Task;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TaskEditFragment extends Fragment {
    private FragmentTaskEditBinding binding;
    private final int[] date = new int[3];
    private final int[] hour = new int[2];
    private EditText titleInputText, descriptionInputText;
    int taskId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState
    ) {
        this.binding = FragmentTaskEditBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        TextInputEditText title = view.findViewById(R.id.taskEditEditTitle);
        TextInputEditText description = view.findViewById(R.id.taskEditEditDescription);
        TextView dateDisplay = view.findViewById(R.id.taskEditDate);
        TextView hourDisplay = view.findViewById(R.id.taskEditHour);

        Task task = CRUDTask.getTask(taskId);
        Date dateT = task.getDate();
        SimpleDateFormat ftDate = new SimpleDateFormat("dd/MM/yyyy");
        dateDisplay.setText(ftDate.format(dateT));
        SimpleDateFormat ftHour = new SimpleDateFormat("hh:mm");
        hourDisplay.setText(ftHour.format(dateT));

        /*
        Calendar dateTaskCalendar = Calendar.getInstance();
        dateTaskCalendar.set(date[2], date[1], date[0]);
        Date dateTask = dateTaskCalendar.getTime();
        dateDisplay.setText(dateTask.toString()); //ver cómo imprime la fecha (debería ser del tipo 25/10/2022)

        Calendar hourTaskCalendar = Calendar.getInstance();
        hourTaskCalendar.set(hour[0], hour[1]);
        Date hourTask = hourTaskCalendar.getTime();
        hourDisplay.setText(hourTask.toString()); //ver si pone hora actual o de la task
         */
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // button deleteTask
        this.binding.taskEditBackButton.setOnClickListener(v -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_TaskEditFragment_to_TaskListFragment);
        });
        //button saveTask
        this.binding.taskEditSaveButton.setOnClickListener(v -> {
            //this.showMessage(view); if press OK -> modify
            this.modifyTask(view);
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


    //ver si creamos una task nueva y eliminamos la existente o modificamos la task en la bbdd
    public void modifyTask(View view) {
        this.titleInputText = view.findViewById(R.id.taskEditEditTitle);
        this.descriptionInputText = view.findViewById(R.id.taskEditEditDescription);
        boolean isTitleInputEmpty = this.titleInputText.getText().toString().equals("");
        if (!isTitleInputEmpty) {
            Calendar dateTaskCalendar = Calendar.getInstance();
            dateTaskCalendar.set(date[2], date[1], date[0], hour[0], hour[1]);
            Date dateTask = dateTaskCalendar.getTime();
            // realm
            String titleText = this.titleInputText.getText().toString();
            String descriptionText = this.descriptionInputText.getText().toString();

            assert getArguments() != null;
            taskId = getArguments().getInt("taskId");
            Task task = CRUDTask.getTask(taskId);
            Task newTask = new Task(titleText, descriptionText, dateTask);
            CRUDTask.updateTask(task,newTask);
            Toast.makeText(getContext(), R.string.succesful_edit, Toast.LENGTH_LONG).show();
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_TaskEditFragment_to_TaskListFragment);
        } else {
            Toast.makeText(getContext(), R.string.noTitle_text, Toast.LENGTH_LONG).show();
        }
    }
}

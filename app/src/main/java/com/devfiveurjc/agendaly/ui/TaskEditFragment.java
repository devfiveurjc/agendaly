package com.devfiveurjc.agendaly.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.devfiveurjc.agendaly.R;
import com.devfiveurjc.agendaly.crud.CRUDTask;
import com.devfiveurjc.agendaly.databinding.FragmentTaskInfoBinding;
import com.devfiveurjc.agendaly.model.Task;

import java.util.Calendar;
import java.util.Date;

public class TaskEditFragment extends Fragment {
    private FragmentTaskInfoBinding binding;
    private final int[] date = new int[3];
    private final int[] hour = new int[2];
    private EditText titleInputText, descriptionInputText;
    int taskId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState
    ) {
        this.binding = FragmentTaskInfoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        TextView title = view.findViewById(R.id.taskEditEditTitle);
        TextView description = view.findViewById(R.id.taskEditEditDescription);
        TextView dateDisplay = view.findViewById(R.id.taskEditDate);
        TextView hourDisplay = view.findViewById(R.id.taskEditHour);

        assert getArguments() != null;
        taskId = getArguments().getInt("taskId");
        Task task = CRUDTask.getTask(taskId);

        title.setText(task.getTitle());
        description.setText(task.getDescription());


        Calendar dateTaskCalendar = Calendar.getInstance();
        dateTaskCalendar.set(date[2], date[1], date[0]);
        Date dateTask = dateTaskCalendar.getTime();
        dateDisplay.setText(dateTask.toString()); //ver cómo imprime la fecha (debería ser del tipo 25/10/2022)

        Calendar hourTaskCalendar = Calendar.getInstance();
        hourTaskCalendar.set(hour[0], hour[1]);
        Date hourTask = hourTaskCalendar.getTime();
        hourDisplay.setText(hourTask.toString()); //ver si pone hora actual o de la task
        return view;
    }

    //ver si creamos una task nueva y eliminamos la existente o modificamos la task en la bbdd
    /*public void saveTask(View view) {
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
            Task task = new Task(titleText, descriptionText, dateTask);
            CRUDTask.addTask(task);
            NavHostFragment.findNavController(this)
                    .navigate(); //crear action
        } else {
            Toast.makeText(getContext(), R.string.noTitle_text, Toast.LENGTH_LONG).show();
        }
    }*/
}

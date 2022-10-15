package com.devfiveurjc.agendaly;

import static java.lang.String.valueOf;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewTasksActivity extends AppCompatActivity {
    private TextView displayDate, displayHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tasks);
        view();
    }

    public void view (){
        LinearLayout layout = findViewById(R.id.linear2);
        ContextThemeWrapper newContext;

        /////PRUEBA PARA MOSTRAR/////
        ArrayList<String> tasks = new ArrayList();
        String t1 = "Prueba";
        String t2 = "Hola";
        String t3 = "Adios";
        tasks.add(t1);  //COMENTANDO ESTOS ADD VEO QUE FUNCIONA LA PRIMERA PARTE  DEL IF
        tasks.add(t2);
        tasks.add(t3);
        //HABRIA QUE CAMBIAR ESTE ARRAYLIST POR EL GENERADO EN NEWTASKACTIVITY

        if(tasks.size() == 0) {
            newContext = new ContextThemeWrapper(this, R.style.Theme_Agendaly_PlainText);
            AppCompatTextView noTaskText = new AppCompatTextView(newContext);
            noTaskText.setText("You dont have tasks");
            layout.addView(noTaskText);
        }
        else {
            newContext = new ContextThemeWrapper(this, R.style.Theme_Agendaly_PlainText);
            for (String t: tasks) { //TASK T: TASKS
                String text = t; //T.GETTITLE()
                AppCompatButton button = new AppCompatButton(newContext);
                button.setText(text);
                button.setGravity(Gravity.START);
                button.setGravity(Gravity.CENTER_VERTICAL);
                /*button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ViewTasksActivity.this, ActionTasksActivity.class);
                        intent.putExtra("tarea", tarea);
                        intent.putExtra("index_calendario", index);
                        startActivity(intent);
                    }
                });*/
                layout.addView(button);
            }
        }
    }
}

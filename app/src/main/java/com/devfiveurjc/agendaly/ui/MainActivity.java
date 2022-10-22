package com.devfiveurjc.agendaly.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.Menu;
import android.view.MenuItem;

import com.devfiveurjc.agendaly.R;
import com.devfiveurjc.agendaly.crud.CRUDTask;
import com.devfiveurjc.agendaly.databinding.ActivityMainBinding;
import com.devfiveurjc.agendaly.model.Task;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // WARNING db testing purpose stuff
        CRUDTask.deleteAllTasks();
        CRUDTask.addTask(new Task("uwu", "uwu", new Date()));
        CRUDTask.addTask(new Task("owo", "owo", new Date()));
        //
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /*
    public void showHelp(View view) {
        // WARNING delete temp testing
        CRUDTask.deleteAllTasks();
        ContextThemeWrapper newContext = new ContextThemeWrapper(this, R.style.Theme_Agendaly_Dialog);
        AlertDialog.Builder builderAD = new AlertDialog.Builder(newContext);
        builderAD.setTitle(R.string.help);
        builderAD.setMessage(R.string.help_text);
        builderAD.setPositiveButton("OK", null);
        builderAD.create().show();
    }
    */

}

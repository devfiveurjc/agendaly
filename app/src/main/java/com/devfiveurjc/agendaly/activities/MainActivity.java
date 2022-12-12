package com.devfiveurjc.agendaly.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import android.view.Menu;
import android.view.MenuItem;

import com.devfiveurjc.agendaly.R;
import com.devfiveurjc.agendaly.crud.CRUDSetting;
import com.devfiveurjc.agendaly.crud.CRUDTask;
import com.devfiveurjc.agendaly.databinding.ActivityMainBinding;
import com.devfiveurjc.agendaly.models.Setting;
import com.devfiveurjc.agendaly.models.Task;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // CRUDSetting.clearSetting();
        // CRUDTask.deleteAllTasks();
        // default setting
        if (CRUDSetting.isEmpty()) {
            PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);
            CRUDSetting.createSetting("english", false);
        // saved setting
        } else {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            Setting setting = CRUDSetting.getSetting();
            pref.edit().putString("language", setting.getLanguage()).apply();
            pref.edit().putBoolean("dark_mode", setting.isDarkMode()).apply();
        }
        // initial default tasks
        if (CRUDTask.isEmpty()) {
            CRUDTask.addTask(new Task("uwu", "uwu", new Date()));
            CRUDTask.addTask(new Task("owo", "owo", new Date()));
        }
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(this.getLayoutInflater());
        this.setContentView(binding.getRoot());
        this.setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        this.appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, this.appBarConfiguration);
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
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, this.appBarConfiguration)
                || super.onSupportNavigateUp();
    }

}

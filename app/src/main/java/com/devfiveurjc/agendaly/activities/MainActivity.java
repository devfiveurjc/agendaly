package com.devfiveurjc.agendaly.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
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
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // CRUDSetting.clearSetting();
        // CRUDTask.deleteAllTasks();
        // default setting
        if (CRUDSetting.isEmpty()) {
            CRUDSetting.createSetting("en", false);
        }
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        Setting setting = CRUDSetting.getSetting();
        String language = setting.getLanguage();
        this.setLocale(language);
        pref.edit().putString("language", language).apply();
        boolean darkMode = setting.isDarkMode();
        this.setColorMode(darkMode);
        pref.edit().putBoolean("dark_mode", darkMode).apply();
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

    @SuppressWarnings("deprecation")
    private void setLocale(String language) {
        Context context = this.getBaseContext();
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        Locale locale = new Locale(language);
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    private void setColorMode(boolean darkMode) {
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
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
            startActivity(new Intent(this, SettingActivity.class));
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

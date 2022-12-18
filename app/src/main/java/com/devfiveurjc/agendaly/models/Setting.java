package com.devfiveurjc.agendaly.models;

import io.realm.RealmObject;


public class Setting extends RealmObject {

    private String language;
    private boolean darkMode;

    public Setting() { }

    public Setting(String language, boolean darkMode) {
        this.language = language;
        this.darkMode = darkMode;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public String getLanguage() {
        return this.language;
    }

    public boolean isDarkMode() {
        return this.darkMode;
    }

}

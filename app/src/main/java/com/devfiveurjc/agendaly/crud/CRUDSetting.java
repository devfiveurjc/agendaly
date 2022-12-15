package com.devfiveurjc.agendaly.crud;

import com.devfiveurjc.agendaly.models.Setting;

import java.util.Objects;

import io.realm.Realm;


public class CRUDSetting {

    private static final Realm realm = Realm.getDefaultInstance();

    private static void clearSetting() {
        Setting realmSetting = getSetting();
        if (realmSetting != null) {
            realm.executeTransaction(realm -> {
                realmSetting.deleteFromRealm();
            });
        }
    }

    public static void createSetting(String language, boolean darkMode) {
        clearSetting();
        realm.executeTransaction(realm -> {
            Setting setting = new Setting(language, darkMode);
            Setting realmSetting = realm.createObject(Setting.class);
            setRealmSetting(realmSetting, setting);
        });
    }

    private static void setRealmSetting(Setting realmSetting, Setting setting) {
        realmSetting.setLanguage(setting.getLanguage());
        realmSetting.setDarkMode(setting.isDarkMode());
    }

    public static void setLanguage(String language) {
        realm.executeTransaction(realm -> {
            Setting realmSetting = getSetting();
            realmSetting.setLanguage(language);
            realm.insertOrUpdate(realmSetting);
        });
    }

    public static void setDarkMode(boolean darkMode) {
        realm.executeTransaction(realm -> {
            Setting realmSetting = getSetting();
            realmSetting.setDarkMode(darkMode);
            realm.insertOrUpdate(realmSetting);
        });
    }

    public static boolean isEmpty() {
        return getSetting() == null;
    }

    public static Setting getSetting() {
        return realm.where(Setting.class).findFirst();
    }

}

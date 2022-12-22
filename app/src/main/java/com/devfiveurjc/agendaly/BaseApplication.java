package com.devfiveurjc.agendaly;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
            .name("agendaly")
            .schemaVersion(0)
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build();
        Realm.setDefaultConfiguration(config);
    }

}

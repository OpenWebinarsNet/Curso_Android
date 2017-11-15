package com.miguelcr.realmcrud;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by miguelcampos on 29/10/17.
 */

public class MyApp extends Application{

    public static AtomicLong AveriaID = new AtomicLong();

    @Override
    public void onCreate() {
        super.onCreate();

        initRealm();

        Realm realm = Realm.getDefaultInstance();
        AveriaID = getIdByTable(realm, AveriaDB.class);
        realm.close();

        //initPreferences();
    }

    private void initRealm() {
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    /*
    private void initPreferences() {
        prefs = getSharedPreferences(Constantes.PREFS_LOGIN, Context.MODE_PRIVATE);
    }
    */

    private <T extends RealmObject> AtomicLong getIdByTable(Realm realm, Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicLong(results.max("id").intValue()) : new AtomicLong();
    }
}

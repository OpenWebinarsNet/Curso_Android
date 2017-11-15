package com.miguelcr.mecaround.app;

import android.app.Application;

import com.miguelcr.mecaround.models.AveriaDB;
import com.miguelcr.mecaround.models.TallerDB;

import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by miguelcampos on 29/10/17.
 */

public class MyApp extends Application {
    public static AtomicLong AveriaID = new AtomicLong();
    public static AtomicLong TallerID = new AtomicLong();

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();

        Realm realm = Realm.getDefaultInstance();
        AveriaID = getIdByTable(realm, AveriaDB.class);
        TallerID = getIdByTable(realm, TallerDB.class);
        realm.close();

    }

    private void initRealm() {
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
            Realm.setDefaultConfiguration(realmConfiguration);
    }

    private <T extends RealmObject> AtomicLong getIdByTable(Realm realm, Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicLong(results.max("id").intValue()) : new AtomicLong();
    }
}

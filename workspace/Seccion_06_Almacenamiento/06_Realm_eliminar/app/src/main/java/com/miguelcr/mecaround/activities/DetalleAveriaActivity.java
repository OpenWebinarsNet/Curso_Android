package com.miguelcr.mecaround.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.miguelcr.mecaround.R;
import com.miguelcr.mecaround.models.AveriaDB;

import io.realm.Realm;

public class DetalleAveriaActivity extends AppCompatActivity {
    TextView textViewInfo;
    long idAveria;
    Realm realm;
    AveriaDB averiaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_averia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textViewInfo = (TextView) findViewById(R.id.textoDescripcionAveria);

        Bundle extras = getIntent().getExtras();
        idAveria = extras.getLong(AveriaDB.AVERIADB_ID);

        realm = Realm.getDefaultInstance();

        averiaDB = realm.where(AveriaDB.class).equalTo(AveriaDB.AVERIADB_ID,idAveria).findFirst();

        setTitle(averiaDB.getTitulo());
        textViewInfo.setText(averiaDB.getDescripcion());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}

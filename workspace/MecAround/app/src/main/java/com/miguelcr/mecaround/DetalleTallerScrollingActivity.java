package com.miguelcr.mecaround;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class DetalleTallerScrollingActivity extends AppCompatActivity {
    String nombreTaller, direccion, telefono, latLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_taller_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        nombreTaller = extras.getString("taller");
        direccion = extras.getString("direccion");
        telefono = extras.getString("telefono");
        latLong = extras.getString("latlon");

        FloatingActionButton fab = findViewById(R.id.fabTelefono);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + telefono));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        ImageView imageHeader = findViewById(R.id.imageHeader);
        imageHeader.setImageResource(R.drawable.montar_propio_taller);
    }
}

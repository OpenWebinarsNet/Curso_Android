package com.miguelcr.a07_libreria_picasso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    ImageView logotipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logotipo = (ImageView) findViewById(R.id.imageViewLogotipo);

        Picasso.with(this)
                .load("http://miguelcamposrivera.com/logo_mecaround.png")
                .resize(400, 200)
                .centerCrop()
                .into(logotipo);
    }
}
